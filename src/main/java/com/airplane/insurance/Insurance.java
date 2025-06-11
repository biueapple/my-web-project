package com.airplane.insurance;

//보험 DB 데이터
public class Insurance
{
	//고유 id
	private int id;
	//보험 이름
	private String insuranceName;
	//보험 가격
	private int price;
	//보험 배상 비율
	private int compensation;
	//보험 내용
	private String content;
	
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getInsuranceName()
	{
		return insuranceName;
	}
	public void setInsuranceName(String name)
	{
		this.insuranceName = name;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public int getCompensation()
	{
		return compensation;
	}
	public void setCompensation(int compensation)
	{
		this.compensation = compensation;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
}
