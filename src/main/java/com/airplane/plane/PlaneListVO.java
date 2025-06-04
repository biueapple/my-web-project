package com.airplane.plane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//plane 정보는 출발지와 도착지가 id 값으로 저장되어 보기 힘드니 문자열로 변환하여 저장할때 사용하는 클래스
public class PlaneListVO
{
	private int id;
	private String departure;
	private String destination;
	private LocalDateTime plane_time;
	private int price;
	
	public PlaneListVO(int id, String departure, String destination, LocalDateTime plane_time, int price)
	{
		this.id = id;
		this.departure = departure;
		this.destination = destination;
		this.plane_time = plane_time;
		this.price = price;
	}
	
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
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
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
