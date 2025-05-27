package com.airplane.plane.mapper;

import java.time.LocalDateTime;
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
	List<Plane> selectReservationToTimeLate(@Param("plane_time")LocalDateTime plane_time, @Param("departure")String departure);
	Plane selectPlane(int id);
	int update(PlaneSeatUpdate up);
}
