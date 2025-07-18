package com.airplane.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.airplane.insurance.Insurance;

public interface InsuranceMapper
{
	//모든 보험을 리턴
	public List<Insurance> insuranceAll();
	
	//id 로 리턴
	public Insurance getInsurace(@Param("id") int id);
}
