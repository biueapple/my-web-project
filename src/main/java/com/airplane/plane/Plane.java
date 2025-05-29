package com.airplane.plane;

import java.time.LocalDateTime;

public class Plane
{
	private int id;
	private int original_id;
	private LocalDateTime plane_time;
	private int departure_id;//출발지
	private int destination_id;//목적지
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
	public LocalDateTime getPlane_time()
	{
		return plane_time;
	}
	public void setPlane_time(LocalDateTime plane_time)
	{
		this.plane_time = plane_time;
	}
	public int getDeparture_id()
	{
		return departure_id;
	}
	public void setDeparture_id(int departure_id)
	{
		this.departure_id = departure_id;
	}
	public int getDestination_id()
	{
		return destination_id;
	}
	public void setDestination_id(int destination_id)
	{
		this.destination_id = destination_id;
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
