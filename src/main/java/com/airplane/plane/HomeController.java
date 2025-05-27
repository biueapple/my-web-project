package com.airplane.plane;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.airport.AirService;
import com.example.airport.AirinfoDto;

import jakarta.validation.Valid;

@Controller
public class HomeController {
	@Autowired
	PlaneService planeService;
	
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
	public String airplaneListGet(Model model)
	{
		List<Plane> plane = planeService.selectAll(LocalDateTime.now());
		model.addAttribute("list", plane);
		return "airplaneList";
	}
	
	@RequestMapping(value = "airplaneList", method = RequestMethod.POST)
	public String airplaneListPOST(@RequestParam("id") int id, Model model)
	{
		PlaneOriginal original = planeService.planeOriginal(id);
		model.addAttribute("original", original);
		return "seat";
	}

	@RequestMapping(value = "reserveSeat", method = RequestMethod.POST)
	public String selectSeat()
	{
		//업데이트
		return "redirect:/";
	}
	

}






