package com.airplane.plane;

import java.time.LocalDateTime;

public class Plane
{
	private int id;
	private int original_id;
	private int airport_id;
	private LocalDateTime plane_time;
	private String departure;//출발지
	private String destination;//목적지
	private int economy_seat;
	private int business_seat;
	private int first_seat;
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
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
	public int getEconomy_seat()
	{
		return economy_seat;
	}
	public void setEconomy_seat(int economy_seat)
	{
		this.economy_seat = economy_seat;
	}
	public int getBusiness_seat()
	{
		return business_seat;
	}
	public void setBusiness_seat(int business_seat)
	{
		this.business_seat = business_seat;
	}
	public int getFirst_seat()
	{
		return first_seat;
	}
	public void setFirst_seat(int first_seat)
	{
		this.first_seat = first_seat;
	}
}
