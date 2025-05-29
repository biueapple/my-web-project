package com.airplane.plane;

import java.time.LocalDateTime;
import java.util.List;

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
	public String home(Model model) {
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now", now);
		return "home";
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
	public String airplaneListGet(@ModelAttribute("dto") AirinfoDto dto, Model model, HttpSession session)
	{
		//비행기 정보 리스트
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
		model.addAttribute("list", plane);
		session.setAttribute("list", plane);
		return "airplaneList";
	}
	
	@RequestMapping(value = "airplaneList", method = RequestMethod.POST)
	public String airplaneListPOST(@RequestParam("id") int id, Model model, HttpSession session)
	{
		System.out.println("post");
		//비행기id
		PlaneOriginal original = planeService.planeOriginal(id);
		session.setAttribute("planeId", id);
		model.addAttribute("original", original);
		return "seat";
	}

	@RequestMapping(value = "reserveSeat", method = RequestMethod.POST)
	public String selectSeat(@RequestParam("seatId") String seatId,HttpSession session, Model model) {
		//업데이트
		LoginRequestCommand lrc = (LoginRequestCommand)session.getAttribute("loginUser");
		if(lrc==null) {
			return "redirect:/login";
		}else {
			User user =	userService.search(lrc.getId());
			List<Plane> plane = (List<Plane>) session.getAttribute("list");
			int id = (int) session.getAttribute("planeId");
			System.out.println(user.getAge());
			System.out.println(plane.size());
			System.out.println(id);
			Plane e =null ;
			for(Plane p : plane ) 
			{
				if(p.getId()==id)
				{
					e = p; 
					break;
				}
			}
			List<AirinfoDto> aid = airService.info();
			String depart = null;
			String arrive = null;
			for(AirinfoDto a : aid)
			{
				if(a.getAirportId() == e.getDeparture_id())
					depart = a.getAirportName();
				if(a.getAirportId() == e.getDestination_id())
					arrive = a.getAirportName();
			}
			RefundUserDto req = new RefundUserDto();
			req.setUserId(user.getUserId());
			req.setGender(user.getGender());
			req.setDepart(depart);
			req.setArrive(arrive);
			req.setSeat(seatId);
			refundUserService.regist(req);
			//여기서 비행기 정보 가져오기
			return "redirect:/";
		}
		
	}
	

}






