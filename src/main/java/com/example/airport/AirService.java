package com.example.airport;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airplane.plane.mapper.AirMapper;
@Service
public class AirService {
	@Autowired
	private AirMapper AirMapper;
	
	public List<AirinfoDto> info(){
		return AirMapper.select();
	}
}
