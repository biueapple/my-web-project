package com.airplane.plane;

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

import com.airplane.board.BoardIdDto;
import com.airplane.board.BoardService;
import com.airplane.board.NoticeBoardService;
import com.airplane.insurance.Insurance;
import com.airplane.insurance.InsuranceService;
import com.airplane.user.LoginRequestCommand;
import com.airplane.user.User;
import com.airplane.user.UserService;
import com.example.airport.AirService;
import com.example.airport.AirinfoDto;
import com.example.mysite.user.AirplaneAlarm;
import com.example.mysite.user.AlarmService;
import com.example.mysite.user.PresentTime;
import com.example.mysite.user.RefundUserDto;
import com.example.mysite.user.RefundUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController
{
	@Autowired
	PlaneService planeService;
	@Autowired
	UserService userService;
	@Autowired
	RefundUserService refundUserService;
	@Autowired
	AirService airService;
	@Autowired
	BoardService boardService;
	@Autowired
	NoticeBoardService noticeBoardService;
	@Autowired
	InsuranceService insuranceService;
	@Autowired
	AlarmService alarmService;
	
	// 홈 페이지
	@RequestMapping("/")
	public String home(Model model, Locale locale, HttpSession session)
	{
//		alarmService.myMap.put("time", new PresentTime());
//		AirplaneAlarm alarm = new AirplaneAlarm(alarmService.alarmMapper);
//		alarmService.myMap.put("user", alarm);
//		alarmService.myMap2.put("user2", () -> alarm.function());

		//비행기가 출발하는 순서로 받아오기
		List<Plane> recently = planeService.selectRecently();
		
		//비행기 정보에 있는 id 값을 이름으로 변환하기 위한 리스트
		List<Integer> integer = new ArrayList<>();
		
		//id 값 저장
		for (Plane p : recently)
		{
			integer.add(p.getDeparture_id());
			integer.add(p.getDestination_id());
		}
		
		//모든 공항의 이름을 받아오기
		List<String> strings = airService.IDToSting(integer);

		//비행기정보에서 출발지 id 와 도착지 id 대신 이름으로 가지고 있는 클래스 리스트
		List<PlaneListVO> dtoList = new ArrayList<>();

		//비행기 정보를 토대로 변환중
		for (int i = 0; i < recently.size(); i++)
		{
			PlaneListVO dto = new PlaneListVO(
					recently.get(i).getId(),
					strings.get(i * 2),
					strings.get(i * 2 + 1),
					recently.get(i).getPlane_time(),
					0);
			dtoList.add(dto);
		}

		//그렇게 변환된 비행기 출발 정보를 전달
		model.addAttribute("Recently", dtoList);

		//현재 삭제상태가 아니고(정상인) 중요도가 3 이상인 공지를 모두 리턴받음
		List<BoardIdDto> noticeBoard = noticeBoardService.noticeSelectIdAllNormalImportance(3);
		//공지 전달
		model.addAttribute("noticeBoard", noticeBoard);

		//로그인 세션을 받아서
		LoginRequestCommand lrc = (LoginRequestCommand) session.getAttribute("loginUser");
		//로그인 상태라면
		if (lrc != null)
		{
			//유저 정보 받아오기
			User user = userService.search(lrc.getId());
			//유저가 관리자라면
			if (userService.isAdmin(user.getUserId()))
				return "adminHome";	//관리자 페이지로
			else	//관리자가 아니라면
				return "home";	//홈페이지로
		} 
		else	//로그인 상태가 아니라면
			return "home";	//홈페이지로
	}

	//비행기 원본을 추가하는 페이지
	@RequestMapping(value = "planeAdd", method = RequestMethod.GET)
	public String airplaneAddGET(HttpSession session)
	{
		//링크 기본은 홈페이지로
		String link = "home";
		
		// 유저가 관리자라면 
		if (Admin(session))
			link = "airplaneAdd";	//링크를 비행기 원본 추가 페이지로
		
		//링크로
		return link;
	}

	//비행기 원본 추가하기 위한 정보를 받을 메소드
	@RequestMapping(value = "planeAdd", method = RequestMethod.POST)
	public String airplaneAddPOST(@ModelAttribute PlaneOriginal planeOri, HttpSession session)
	{
		//링크 기본은 홈페이지로
		String link = "home";
		
		// 유저가 관리자라면
		if(Admin(session))
		{
			//비행기 원본 추가
			planeService.insertOriginal(planeOri.getEconomy_seat(), planeOri.getBusiness_seat(), planeOri.getFirst_seat());
			//링크를 비행기 원본 추가 페이지로
			link = "airplaneAdd";	
		}
		
		//링크로
		return link;	
	}

	//비행기 출발정보 입력 사이트로
	@RequestMapping(value = "planeReservation", method = RequestMethod.GET)
	public String airlaneReservationGet(HttpSession session)
	{
		//링크 기본은 홈페이지로
		String link = "home";
		
		// 유저가 관리자라면
		if(Admin(session))
			link = "airplaneReservation"; // 링크를 비행기 출발정보 추가 페이지로
		
		//링크로
		return link;
	}

	//비행기 출발 정보 저장하는 메소드
	@RequestMapping(value = "planeReservation", method = RequestMethod.POST)
	public String reservationPlanePost(@ModelAttribute PlaneReservation planeReservation, HttpSession session)
	{
		//링크 기본은 홈페이지로
		String link = "home";
		
		// 유저가 관리자라면
		if(Admin(session))
		{
			//비행기 출발 정보를 입력
			planeService.insertReservation(planeReservation);
			//링크를 비행기 출발 정보 입력 페이지로
			link = "airplaneReservation";
		}
		
		//링크로
		return link;
	}

	//어느 지역에서 출발해 어느 지역으로 가능 비행기들의 리스트를 보여주는 사이트로 보내주는 메소드
	@RequestMapping(value = "airplaneList", method = RequestMethod.GET)
	public String airplaneListGet(Model model, HttpSession session)
	{
		// 출발지와 도착지를 airPortController 에서 넣은 session 에서 꺼내기
		AirinfoDto dto = (AirinfoDto) model.getAttribute("dto");
		
		//모든 공항에 대한 정보를 꺼내오기
		List<AirinfoDto> aid = airService.info();
		
		//출발지와 도착지 이름을 id 와 대조하는 과정
		int depart_id = 0;
		int destination_id = 0;
		for (AirinfoDto a : aid)
		{
			if (a.getAirportName().equals(dto.getDeparture()))
				depart_id = a.getAirportId();
			if (a.getAirportName().equals(dto.getDestination()))
				destination_id = a.getAirportId();
		}
		
		//나온 출발지 도착지 id 값을 이용해서 출발지 도착지 선택한 시간값으로 출발할 예정인 비행기 리스트를 받아오기
		//List<Plane> plane = planeService.selectAll(dto.getDepartureDate(), depart_id, destination_id);
		System.out.println(dto.getDepartureDate());
		List<Plane> plane = planeService.selectEnough(dto.getDepartureDate(), depart_id, destination_id, dto.getPassenger_number());
		
		//받아온 plane 리스트는 출발지와 도착지가 id 값이니 문자열로 변환하여 전달하기 위한 리스트 생성
		List<PlaneListVO> vo = new ArrayList<>();
		for (Plane p : plane)
		{
			//출발지와 도착지만 문자열이고 나머지는 기존 plane 값과 동일하게 변환중
			vo.add(new PlaneListVO(p.getId(), dto.getDeparture(), dto.getDestination(), p.getPlane_time(),
					p.getPrice()));
		}
		
		//출발지와 도착지가 문자열로 변환된 리스트를 페이지에 넘기기
		model.addAttribute("list", vo);
		
		//비행기 리스트 사이트로 이동
		return "airplaneList";
	}

	//비행기 리스트중에서 하나를 선택한 후 오는 메소드
	@RequestMapping(value = "airplaneList", method = RequestMethod.POST)
	public String airplaneListPOST(@RequestParam("id") int reserve_id, Model model, HttpSession session)
	{
		// 선택한 비행기의 원본 비행기를 받아오기
		PlaneOriginal original = planeService.planeOriginal(reserve_id);
		
		//session 에 출발할 비행기의 id 값을 저장
		session.setAttribute("reserve_id", reserve_id);
		
		//비행기 원본 데이터 페이지에 넘기기 (좌석의 최대치 값을 넘기기 위해)
		model.addAttribute("original", original);

		// 모든 예약된 좌석 불러오기
		// reserve_id로 예약된 모든 시트중에서 '정상'인거
		List<String> reservedSeats = refundUserService.seatNameNormal(reserve_id);
		
		//예약된 좌석 정보 페이지에 넘기기
		model.addAttribute("reservedSeats", reservedSeats);
		
		//airPortController 에서 session 에 넣은 선택한 인원수 값도 페이지에 넘기기
		model.addAttribute("number_of_people", session.getAttribute("number_of_people"));
		//좌석 선택 페이지로
		return "seat";
	}

	//좌석 선택 후에 오는 메소드
	@RequestMapping(value = "reserveSeat", method = RequestMethod.POST)
	public String selectSeat(@RequestParam("seatIds") String seatIds, HttpSession session, Model model)
	{
		//로그인 정보 받아오기
		LoginRequestCommand lrc = (LoginRequestCommand) session.getAttribute("loginUser");

		//로그인 상태가 아니라면
		if (lrc == null)
			return "redirect:/login";

		//선택한 좌석들의 정보를 나누기 ( [f1,f2] -> [f1] [f2])
		String[] seatArray = seatIds.split(",");
		
		//유저의 아이디로 유저의 정보를 받아오기
		User user = userService.search(lrc.getId());

		//출발할 비행기의 id 받아오기
		int planeId = (int) session.getAttribute("reserve_id");

		// 조건에 맞는 비행기중에서 선택한 id 에 해당하는 비행기 정보 받아오기
		Plane plane = planeService.selectReservationToId(planeId);

		// 모든 공항의 정보를 받아오기
		List<AirinfoDto> aid = airService.info();
		
		//출발지와 도착지를 id 에서 문자열로 변환하는 과정
		String depart = null;
		String arrive = null;
		for (AirinfoDto a : aid)
		{
			if (a.getAirportId() == plane.getDeparture_id())
				depart = a.getAirportName();
				
			if (a.getAirportId() == plane.getDestination_id())
				arrive = a.getAirportName();
		}

		// 나눠진 예약 정보를 하나하나 적용시키기
		for (int i = 0; i < seatArray.length; i++)
		{
			//어느 좌석을 선택했는지
			String seatId = seatArray[i];
			RefundUserDto req = new RefundUserDto();
			req.setUserId(user.getUserId());
			req.setReservation_id(plane.getId());
			req.setGender(user.getGender());
			req.setDepart(depart);
			req.setArrive(arrive);
			req.setSeat(seatId);
			// 예약 정보 넣기
			refundUserService.regist(req);
			// 좌석 채우기
			planeService.updateSeat(seatId, planeId, 1);
		}
		
		//홈 페이지로
		return "redirect:/";
	}

	@RequestMapping(value = "insurance", method = RequestMethod.POST)
	public String testInsuranceLink(
			@RequestParam("id") int id,
			Model model)
	{
		
		List<Insurance> insuranceList = insuranceService.selectAllInsurance(); // 예시
	    model.addAttribute("insuranceList", insuranceList);
	    model.addAttribute("id", id);
	    return "insuranceList"; // /WEB-INF/views/insuranceList.jsp
	}
	
	@RequestMapping(value = "/insuranceSubmit", method = RequestMethod.POST)
	public String testInsurancePOST(
			@RequestParam("id") int id,
			@RequestParam("insuranceId") int insuranceId,
			Model model)
	{
		System.out.println(id);
		//선택한 보험을 적용
		refundUserService.updateInsurance(id, insuranceId);
	    return "redirect:/user/regist"; // /WEB-INF/views/insuranceList.jsp
	}
	
	private boolean Admin(HttpSession session)
	{
		//로그인 유저 정보 받아오기
		User user = Login(session);
		// 로그인 상태라면
		if (user == null)
			return false; // 링크를 비행기 출발정보 추가 페이지로	
		
		//유저가 관리자라면
		if (userService.isAdmin(user.getUserId()))
			return true;
		
		//유저는 맞는데 관리자는 아님
		return false;
	}
	
	private User Login(HttpSession session)
	{
		LoginRequestCommand lrc = (LoginRequestCommand) session.getAttribute("loginUser");
		// 로그인 상태라면
		if (lrc != null)
		{
			// 유저 정보 받아오기
			User user = userService.search(lrc.getId());
			return user;
		}
		return null;
	}
}
