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
	
	public List<Insurance> selectAllInsurance()
	{
		return insuranceMapper.insuranceAll();
	}
}
