package com.airplane.plane.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airplane.plane.Plane;
import com.airplane.plane.PlaneOriginal;
import com.airplane.plane.PlaneReservation;
import com.airplane.plane.PlaneSeatUpdate;

public interface PlaneMapper {
	int insertOriginal(PlaneOriginal orignal);
	int insertReservation(PlaneReservation planeReservation);
	PlaneOriginal selectOriginal(int id);
	List<Plane> selectReservationToTimeLate
	(@Param("plane_time")LocalDate plane_time, @Param("departure_id")int departure_id,@Param("destination_id") int destination_id);
	Plane selectPlane(int id);
	int planeFirstSeatUpdate(PlaneSeatUpdate up);
	int planeBusinessSeatUpdate(PlaneSeatUpdate up);
	int planeEconomySeatUpdate(PlaneSeatUpdate up);
	List<Plane> selectReservationToTimeLateNow();
}
