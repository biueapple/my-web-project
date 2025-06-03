package com.airplane.plane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.airplane.user.LoginRequestCommand;
import com.airplane.user.User;
import com.airplane.user.UserService;
import com.example.airport.AirService;
import com.example.airport.AirinfoDto;
import com.example.mysite.user.RefundUserDto;
import com.example.mysite.user.RefundUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	PlaneService planeService;
	@Autowired
	UserService userService;
	@Autowired
	RefundUserService refundUserService;
	@Autowired
	AirService airService;
	
	@RequestMapping("/")
	public String home(Model model, Locale locale, HttpSession session) {
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now", now);
		
		List<Plane> recently = planeService.selectRecently();
		List<Integer> integer = new ArrayList<>();
		for(Plane p : recently)
		{
			integer.add(p.getDeparture_id());
			integer.add(p.getDestination_id());
		}
		List<String> strings = airService.IDToSting(integer);
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		
		List<PlaneDto> dtoList = new ArrayList<>();

		for(int i = 0; i < recently.size(); i++)
		{
			PlaneDto dto = new PlaneDto();
		    dto.setId(recently.get(i).getId());
		    dto.setPlaneTime(recently.get(i).getPlane_time());
		    dto.setDepartureName(strings.get(i * 2));
		    dto.setDestinationName(strings.get(i * 2 + 1));
		    dto.setFormattedDate(dto.getPlaneTime().format(dateFormatter));
		    dto.setFormattedTime(dto.getPlaneTime().format(timeFormatter));
		    dtoList.add(dto);
		}
		
		model.addAttribute("Recently", dtoList);
		
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc!=null) {
			User user = userService.search(lrc.getId());
			if(userService.isAdmin(user.getUserId())){
				return "adminHome";
			}else {
				return "home";
			}
		}else {
			return "home";
		}
	}
	
	
	
	
	
	@RequestMapping(value = "planeAdd", method = RequestMethod.GET)
	public String airplaneAddGET()
	{
		return "airplaneAdd";
	}
	
	@RequestMapping(value = "planeAdd", method = RequestMethod.POST)
	public String airplaneAddPOST(@ModelAttribute PlaneOriginal planeOri)
	{
		planeService.insertOriginal(planeOri.getEconomy_seat(), planeOri.getBusiness_seat(), planeOri.getFirst_seat());
		return "airplaneAdd";
	}
	
	@RequestMapping(value = "planeReservation", method = RequestMethod.GET)
	public String airlaneReservationGet()
	{
		return "airplaneReservation";
	}
	
	@RequestMapping(value = "planeReservation", method = RequestMethod.POST)
	public String reservationPlanePost(@ModelAttribute PlaneReservation planeReservation)
	{
		planeService.insertReservation(planeReservation);
		return "airplaneReservation";
	}

	@RequestMapping(value = "airplaneList", method = RequestMethod.GET)
	public String airplaneListGet(@ModelAttribute("dto") AirinfoDto dto,
			Model model, HttpSession session)
	{
		//비행기 정보 리스트number_of_people
		List<AirinfoDto> aid = airService.info();
		int depart_id = 0;
		int destination_id = 0;
		for(AirinfoDto a : aid)
		{
			if(a.getAirportName().equals(dto.getDeparture()))
				depart_id = a.getAirportId();
			if(a.getAirportName().equals(dto.getDestination()))
				destination_id = a.getAirportId();
		}
		List<Plane> plane = planeService.selectAll(dto.getDepartureDate(), depart_id, destination_id);
		List<PlaneListVO> vo = new ArrayList<>();
		for(Plane p : plane)
		{
			vo.add(new PlaneListVO(p.getId(), dto.getDeparture(), dto.getDestination(), p.getPlane_time(), p.getPrice()));
		}
		model.addAttribute("list", vo);
		session.setAttribute("list", plane);
		return "airplaneList";
	}
	
	@RequestMapping(value = "airplaneList", method = RequestMethod.POST)
	public String airplaneListPOST(@RequestParam("id") int reserve_id, Model model, HttpSession session)
	{
		System.out.println("post");
		//비행기id
		PlaneOriginal original = planeService.planeOriginal(reserve_id);
		session.setAttribute("planeId", reserve_id);
		model.addAttribute("original", original);
		
		//모든 예약된 좌석 불러오기
		//reserve_id로 예약된 모든 시트중에서 '정상'인거
		List<String> reservedSeats = refundUserService.seatNameNormal(reserve_id);//Arrays.asList("first_1", "economy_3", "business_4");
		model.addAttribute("reservedSeats", reservedSeats);
		model.addAttribute("number_of_people", session.getAttribute("number_of_people"));
		return "seat";
	}

	@RequestMapping(value = "reserveSeat", method = RequestMethod.POST)
	public String selectSeat(@RequestParam("seatIds") String seatIds,HttpSession session, Model model)
	{
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc == null)
		{	
			return "redirect:/login";
		}
		else
		{
			String[] seatArray = seatIds.split(",");
			System.out.println(seatArray.length);
			
			User user =	userService.search(lrc.getId());
			List<Plane> planes = (List<Plane>) session.getAttribute("list");
			//session.invalidate();
			int planeId = (int) session.getAttribute("planeId");
		
			//어떤 비행기를 선택했는가
			Plane plane = planeService.FindCurrent(planes, planeId);
			
			//어느 공항에서 출발하고 도착하는지
			List<AirinfoDto> aid = airService.info();
			String depart = null;
			String arrive = null;
			for(AirinfoDto a : aid)
			{
				if(a.getAirportId() == plane.getDeparture_id())
					depart = a.getAirportName();
				if(a.getAirportId() == plane.getDestination_id())
					arrive = a.getAirportName();
			}
			
			//예약
			for(int i = 0; i < seatArray.length; i++)
			{
				String seatId = seatArray[i];
				RefundUserDto req = new RefundUserDto();
				req.setUserId(user.getUserId());
				req.setReservation_id(plane.getId());
				req.setGender(user.getGender());
				req.setDepart(depart);
				req.setArrive(arrive);
				req.setSeat(seatId);
				refundUserService.regist(req);
				//업데이트
				planeService.updateSeat(seatId, planeId, 1);
			}
			
			return "redirect:/";
		}
		
	}
	

}






