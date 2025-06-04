package com.airplane.plane;

//몇번 비행기의 좌석을 얼마나 예약한건지에 대한 정보
public class PlaneSeatUpdate
{
	private int id;
	private int count;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count)
	{
		this.count = count;
	}
}
