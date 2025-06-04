package com.airplane.plane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//비행기가 어디서 어디로
public class PlaneDto
{
	private int id;
	private LocalDateTime planeTime;
	private String departureName;
	private String destinationName;
	private String formattedDate;
	private String formattedTime;

	public String getFormattedDate()
	{
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate)
	{
		this.formattedDate = formattedDate;
	}

	public String getFormattedTime()
	{
		return formattedTime;
	}

	public void setFormattedTime(String formattedTime)
	{
		this.formattedTime = formattedTime;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public LocalDateTime getPlaneTime()
	{
		return planeTime;
	}

	public void setPlaneTime(LocalDateTime planeTime)
	{
		this.planeTime = planeTime;
	}

	public String getDepartureName()
	{
		return departureName;
	}

	public void setDepartureName(String departureName)
	{
		this.departureName = departureName;
	}

	public String getDestinationName()
	{
		return destinationName;
	}

	public void setDestinationName(String destinationName)
	{
		this.destinationName = destinationName;
	}

	public String getFormattedRegistDate()
	{
		if (planeTime == null)
			return "";
		LocalDate registLocalDate = planeTime.toLocalDate();
		LocalDate today = LocalDate.now();

		if (registLocalDate.equals(today))
		{
			return planeTime.format(DateTimeFormatter.ofPattern("HH:mm"));
		} else
		{
			return planeTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		}
	}

	public String getFormattedRegistDateOne()
	{
		if (planeTime == null)
			return "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
		return planeTime.format(formatter);
	}
}
