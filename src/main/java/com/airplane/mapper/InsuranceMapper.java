package com.airplane.mapper;

import java.util.List;

import com.airplane.insurance.Insurance;

public interface InsuranceMapper
{
	//모든 보험을 리턴
	public List<Insurance> insuranceAll();
}
