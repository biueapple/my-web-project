package com.airplane.plane;

import java.time.LocalDateTime;

public class PlaneListVO
{
	private int id;
	private String departure;
	private String destination;
	private LocalDateTime plane_time;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
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
	public LocalDateTime getPlane_time()
	{
		return plane_time;
	}
	public void setPlane_time(LocalDateTime plane_time)
	{
		this.plane_time = plane_time;
	}
	public PlaneListVO(int id, String departure, String destination, LocalDateTime plane_time)
	{
		this.id = id;
		this.departure = departure;
		this.destination = destination;
		this.plane_time = plane_time;
	}
}
