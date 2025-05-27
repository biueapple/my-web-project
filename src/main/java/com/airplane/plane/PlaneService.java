package com.airplane.plane;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.plane.mapper.PlaneMapper;

@Service
public class PlaneService
{
	@Autowired
	private PlaneMapper planeMapper;
	
	public List<Plane> selectAll(LocalDateTime time)
	{
		List<Plane> plane = planeMapper.selectReservationToTimeLate(time, "서울");
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
	
//	public void insertReservation(int original_id, int airport_id, LocalDateTime plane_time, String departure, String destination)
//	{
//		PlaneReservation reservation = new PlaneReservation();
//		reservation.setOriginal_id(original_id);
//		reservation.setAirport_id(airport_id);
//		reservation.setPlane_time(plane_time);
//		reservation.setDeparture(departure);
//		reservation.setDestination(destination);
//		planeMapper.insertReservation(reservation);
//	}
	
	public void insertReservation(PlaneReservation planeReservation)
	{
		System.out.println(planeReservation.getPlane_time());
		planeMapper.insertReservation(planeReservation);
		
		//PlaneSeatUpdate up = new PlaneSeatUpdate();
		//up.setId(id);
		//up.setCount(count);
		//planeMapper.update(up);
	}
	
	public PlaneOriginal planeOriginal(int id)
	{
		//id로 예약 비행기중에 원본 비행기 값 받아와서 원본 비행기값을 활용해 좌석 배치후 유저의 예약을 이용해 체크
		//원본 받아오기
		Plane plane = planeMapper.selectPlane(id);
		PlaneOriginal original = planeMapper.selectOriginal(plane.getOriginal_id());
		
		return original;
	}
}
