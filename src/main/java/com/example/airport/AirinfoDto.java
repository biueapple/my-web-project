package com.example.airport;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AirinfoDto {

	private int airportId;
    private String airportName;
	@NotBlank(message = "{NotBlank.departure}")
	private String departure;
	@NotBlank(message = "{NotBlank.destination}")
	private String destination;
    @NotNull(message = "{NotBlank.departureDate}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;
    @NotNull(message = "{NotNull.passenger_number}")
    private Integer passenger_number;
    
    public Integer getPassenger_number() {
		return passenger_number;
	}

	public void setPassenger_number(Integer passenger_number) {
		this.passenger_number = passenger_number;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
    
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}
