package com.airplane.plane;

import java.time.LocalDateTime;

public class PlaneReservation
{
	private int original_id;
	private int airport_id;
	private LocalDateTime plane_time;
	private String departure;
	private String destination;

	
	public int getOriginal_id()
	{
		return original_id;
	}
	public void setOriginal_id(int original_id)
	{
		this.original_id = original_id;
	}
	public int getAirport_id()
	{
		return airport_id;
	}
	public void setAirport_id(int airport_id)
	{
		this.airport_id = airport_id;
	}
	public LocalDateTime getPlane_time()
	{
		return plane_time;
	}
	public void setPlane_time(LocalDateTime plane_time)
	{
		this.plane_time = plane_time;
	}
	public String getDeparture()
	{
		return departure;
	}
	public void setDeparture(String departure)
	{
		this.departure = departure;
	}
	public String getDestination()
	{
		return destination;
	}
	public void setDestination(String destination)
	{
		this.destination = destination;
	}
}
