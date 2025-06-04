package com.airplane.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airplane.plane.Plane;
import com.airplane.plane.PlaneOriginal;
import com.airplane.plane.PlaneReservation;
import com.airplane.plane.PlaneSeatUpdate;

public interface PlaneMapper {
	//비행기 원본 입력
	int insertOriginal(PlaneOriginal orignal);
	
	//어떤 비행기가 언제 어디서 어디로 가는지 입력
	int insertReservation(PlaneReservation planeReservation);
	
	//reservation id 로 original 검색
	PlaneOriginal selectOriginalToReservationId(int reservation_id);
	
	//선택한 시작점과 목표자점 선택한 날과 시간부터 그날 자정까지
	List<Plane> selectReservationToTimeLate
	(@Param("plane_time")LocalDate plane_time, @Param("departure_id")int departure_id,@Param("destination_id") int destination_id);
	
	//reservation id 로 reservation 받기
	Plane selectPlane(int id);
	
	//reservation first update
	int planeFirstSeatUpdate(PlaneSeatUpdate up);
	
	//reservation business update
	int planeBusinessSeatUpdate(PlaneSeatUpdate up);
	
	//reservation economy update
	int planeEconomySeatUpdate(PlaneSeatUpdate up);
	
	//reservation 지금부터 빠른 순서대로 리스트로 리턴
	List<Plane> selectReservationToTimeLateNow();
}
