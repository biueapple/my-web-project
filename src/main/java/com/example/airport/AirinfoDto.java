package com.example.airport;

import jakarta.validation.constraints.NotBlank;

public class AirinfoDto {

	private int airportId;
    private String airportName;
	@NotBlank(message = "{NotBlank.departure}")
	 private String departure;
	@NotBlank(message = "{NotBlank.destination}")
	private String destination;
    @NotBlank(message = "{NotBlank.departureDate}")
    private String departureDate;
    
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
    
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
