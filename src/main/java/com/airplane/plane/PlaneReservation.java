package com.airplane.plane;

import java.time.LocalDateTime;

//reservation insert
public class PlaneReservation
{
	private int original_id;
	private LocalDateTime plane_time;
	private int departure_id;
	private int destination_id;
	private int price;

	
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
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
}
