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
		List<Plane> plane = planeService.selectAll(dto.getDepartureDate(), dto.getDeparture());
		System.out.println(plane.size());
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
			RefundUserDto req = new RefundUserDto();
			req.setName(user.getName());
			req.setGender(user.getGender());
			req.setDepart(e.getDeparture());
			req.setArrive(e.getDestination());
			req.setSeat(seatId);
			refundUserService.regist(req);
			//여기서 비행기 정보 가져오기
			return "redirect:/";
		}
		
	}
	

}






