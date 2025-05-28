package com.airplane.plane;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.airport.AirService;
import com.example.airport.AirinfoDto;

import jakarta.validation.Valid;

@Controller
public class AirPortController {
	
	@Autowired
	private AirService airService;
	
	@RequestMapping(value = "Reservation", method = RequestMethod.GET)
	public String showAirportForm(Model model) {
		List<AirinfoDto> airports = airService.info();
		model.addAttribute("airports", airports);
		return "Reservation";
	}
	
	@RequestMapping(value = "Reservation", method = RequestMethod.POST)
	public String submitAirportForm(@Valid
			AirinfoDto airinfoDto, 
			BindingResult bindingResult, 
			Model model,
			 RedirectAttributes redirectAttributes) {
		System.out.println(airinfoDto.getDeparture());
		System.out.println(airinfoDto.getDestination());
		if (airinfoDto.getDeparture().equals(airinfoDto.getDestination())) {
			bindingResult.rejectValue("destination", "error.destination", "출발지와 도착지는 같을 수 없습니다.");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("airports", airService.info());
			model.addAttribute("dto", airinfoDto);
			return "Reservation";
		}
		
		redirectAttributes.addFlashAttribute("dto", airinfoDto); // 리다이렉트 시 dto 전달
		return "redirect:/airplaneList";
	}
}
