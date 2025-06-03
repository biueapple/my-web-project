package com.example.airport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.mapper.AirMapper;
@Service
public class AirService {
	@Autowired
	private AirMapper AirMapper;
	
	public List<AirinfoDto> info(){
		return AirMapper.select();
	}
	
	public List<String> IDToSting(List<Integer> list)
	{
		List<String> result = new ArrayList<>();
		List<AirinfoDto> air = info();
		for(int i : list)
		{
			for(AirinfoDto dto : air)
			{
				if(dto.getAirportId() == i)
					result.add(dto.getAirportName());
			}
		}
		return result;
	}
}
