package com.airplane.plane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//DB 에서 reservation select 할때 사용하는 클래스
public class Plane
{
	//고유 id
	private int id;
	//원본 비행기의 id
	private int original_id;
	//출발 시간
	private LocalDateTime plane_time;
	//출발지
	private int departure_id;
	//목적지
	private int destination_id;
	//좌석 수
	private int economy_seat;
	private int business_seat;
	private int first_seat;
	//가격
	private int price;
	
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
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	public int getSeat()
	{
		return economy_seat + business_seat + first_seat;
	}
	public String getFormattedRegistDate()
	{
		if (plane_time == null)
			return "";
		LocalDate registLocalDate = plane_time.toLocalDate();
		LocalDate today = LocalDate.now();

		if (registLocalDate.equals(today))
		{
			return plane_time.format(DateTimeFormatter.ofPattern("HH:mm"));
		} else
		{
			return plane_time.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		}
	}

	public String getFormattedRegistDateOne()
	{
		if (plane_time == null)
			return "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		return plane_time.format(formatter);
	}
}
