package com.airplane.plane;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.plane.mapper.PlaneMapper;

@Service
public class PlaneService
{
	@Autowired
	private PlaneMapper planeMapper;
	
	public List<Plane> selectAll(LocalDate time, int departure_id, int destination_id)
	{
		List<Plane> plane = planeMapper.selectReservationToTimeLate(time, departure_id, destination_id);
		return plane;
	}
	
	public void insertOriginal(int economy_seat, int business_seat, int first_seat)
	{
		PlaneOriginal ori = new PlaneOriginal();
		ori.setEconomy_seat(economy_seat);
		ori.setBusiness_seat(business_seat);
		ori.setFirst_seat(first_seat);
		planeMapper.insertOriginal(ori);
	}
	
	public void insertReservation(PlaneReservation planeReservation)
	{
		System.out.println(planeReservation.getPlane_time());
		planeMapper.insertReservation(planeReservation);
	}
	
	public PlaneOriginal planeOriginal(int id)
	{
		//id로 예약 비행기중에 원본 비행기 값 받아와서 원본 비행기값을 활용해 좌석 배치후 유저의 예약을 이용해 체크
		//원본 받아오기
		Plane plane = planeMapper.selectPlane(id);
		PlaneOriginal original = planeMapper.selectOriginal(plane.getOriginal_id());
		
		return original;
	}
	
	public void updateSeat(String str, int id)
	{
		PlaneSeatUpdate seatUpdate = new PlaneSeatUpdate();
		seatUpdate.setId(id);
		seatUpdate.setCount(1);
		if (str == null || !str.contains("_")) {
	        throw new IllegalArgumentException("잘못된 좌석 형식입니다: " + str);
	    }

	    String[] parts = str.split("_");
	    String seatClass = parts[0]; // "first", "business", "economy"
	    int seatNumber;

	    try {
	        seatNumber = Integer.parseInt(parts[1]); // 1, 2, 3...
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("좌석 번호가 숫자가 아닙니다: " + parts[1]);
	    }

	    // 여기서 클래스별 분기 처리
	    switch (seatClass.toLowerCase()) {
	        case "first":
	            System.out.println("퍼스트 클래스 좌석 번호: " + seatNumber);
	            // 예: DB에서 퍼스트 클래스 좌석 1번 예약 처리
	            
	            break;
	        case "business":
	            System.out.println("비즈니스 클래스 좌석 번호: " + seatNumber);
	            // 예: business_seat 업데이트
	            break;
	        case "economy":
	            System.out.println("이코노미 클래스 좌석 번호: " + seatNumber);
	            // 예: economy_seat 업데이트
	            planeMapper.planeEconomySeatUpdate(seatUpdate);
	            break;
	        default:
	            throw new IllegalArgumentException("알 수 없는 좌석 클래스입니다: " + seatClass);
	    }
	}
}
