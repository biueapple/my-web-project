package com.airplane.plane;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.PlaneMapper;

@Service
public class PlaneService
{
	@Autowired
	private PlaneMapper planeMapper;
	
	//선택한 시작점과 목표자점 선택한 날과 시간부터 그날 자정까지 리턴
	public List<Plane> selectAll(LocalDate time, int departure_id, int destination_id)
	{
		List<Plane> plane = planeMapper.selectReservationToTimeLate(time, departure_id, destination_id);
		return plane;
	}
	
	//비행기 원본 입력
	public void insertOriginal(int economy_seat, int business_seat, int first_seat)
	{
		PlaneOriginal ori = new PlaneOriginal();
		ori.setEconomy_seat(economy_seat);
		ori.setBusiness_seat(business_seat);
		ori.setFirst_seat(first_seat);
		planeMapper.insertOriginal(ori);
	}
	
	//비행기의 출발정보 입력
	public void insertReservation(PlaneReservation planeReservation)
	{
		planeMapper.insertReservation(planeReservation);
	}
	
	public Plane selectReservationToId(int reservation_id)
	{
		return planeMapper.selectPlaneToReservationId(reservation_id);
	}
	
	//원본 비행기 값을 받아오기
	public PlaneOriginal planeOriginal(int reservation_id)
	{
		//id로 예약 비행기중에 원본 비행기 값 받아와서 원본 비행기값을 활용해 좌석 배치후 유저의 예약을 이용해 체크
		//원본 받아오기
		Plane plane = selectReservationToId(reservation_id);
		PlaneOriginal original = planeMapper.selectOriginalToReservationId(plane.getOriginal_id());
		
		return original;
	}
	
	//좌석이 얼마나 차있는지 업데이트
	public void updateSeat(String str, int id, int count)
	{
		//비행기좌석을 update 하기 위한 클래스
		PlaneSeatUpdate seatUpdate = new PlaneSeatUpdate();
		
		//어떤 비행기를 선택했는지
		seatUpdate.setId(id);
		//몇좌석 선택했는지
		seatUpdate.setCount(count);
		
		//잘못된 값
		if (str == null || !str.contains("_")) {
	        throw new IllegalArgumentException("잘못된 좌석 형식입니다: " + str);
	    }

		//들어온 값을 등급과 위치로 나누기
	    String[] parts = str.split("_");
	    
	    //등급 정보
	    String seatClass = parts[0]; // "first", "business", "economy"
	    int seatNumber;

	    //위치가 int값으로 변환되지 않는 값이면 잘못된 값
	    try {
	        seatNumber = Integer.parseInt(parts[1]); // 1, 2, 3...
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("좌석 번호가 숫자가 아닙니다: " + parts[1]);
	    }

	    // 여기서 클래스별 분기 처리
	    switch (seatClass.toLowerCase()) {
        	// DB에서 퍼스트 클래스 좌석 1번 예약 처리
	        case "first":
	            System.out.println("퍼스트 클래스 좌석 번호: " + seatNumber);
	            planeMapper.planeFirstSeatUpdate(seatUpdate);
	            break;
	            
	            // business_seat 업데이트
	        case "business":
	            System.out.println("비즈니스 클래스 좌석 번호: " + seatNumber);
	            planeMapper.planeBusinessSeatUpdate(seatUpdate);
	            break;
	            
	            // economy_seat 업데이트
	        case "economy":
	            System.out.println("이코노미 클래스 좌석 번호: " + seatNumber);
	            planeMapper.planeEconomySeatUpdate(seatUpdate);
	            break;
	            
	            // 없는 등급이 들어옴
	        default:
	            throw new IllegalArgumentException("알 수 없는 좌석 클래스입니다: " + seatClass);
	    }
	}
	
	//비행기 현재 시점 이후로 출발하는 순서로 리턴
	public List<Plane> selectRecently()
	{
		return planeMapper.selectReservationToTimeLateNow();
	}
	
	//선택한 시작점과 목표자점 선택한 날과 시간부터 그날 자정까지 리턴 좌석이 충분한 비행기만
	public List<Plane> selectEnough(LocalDate time, int departure_id, int destination_id, int count)
	{
		List<Plane> planeList = null;
		
		planeList = planeMapper.selectReservationToTimeLate(time, departure_id, destination_id);
		PlaneOriginal original;
		for(int i = planeList.size() - 1; i >= 0; i--)
		{
			original = planeMapper.selectOriginalToReservationId(planeList.get(i).getOriginal_id());
			if(planeList.get(i).getSeat() + count > original.getSeat())
				planeList.remove(i);
		}
		
		return planeList;
	}
	
	//id 값에 해당하는 비행기를 찾아서 리턴
	public Plane FindCurrent(List<Plane> list, int id)
	{
		for(Plane plane : list) 
		{
			if(plane.getId() == id)
			{
				return plane;
			}
		}
		return null;
	}
}
