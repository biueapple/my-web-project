package com.airplane.insurance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.InsuranceMapper;

@Service
public class InsuranceService
{
	@Autowired
	private InsuranceMapper insuranceMapper;
	
	//모든 보험을 리턴해줌
	public List<Insurance> selectAllInsurance()
	{
		return insuranceMapper.insuranceAll();
	}
}
