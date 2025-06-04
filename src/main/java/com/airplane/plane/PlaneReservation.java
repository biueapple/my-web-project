package com.airplane.plane;

import java.time.LocalDateTime;

//reservation insert 할때 사용하는 클래스
public class PlaneReservation
{
	//원본 비행기 id
	private int original_id;
	//출발 시간
	private LocalDateTime plane_time;
	//출발지 id
	private int departure_id;
	//목적지 id
	private int destination_id;
	//가격
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
