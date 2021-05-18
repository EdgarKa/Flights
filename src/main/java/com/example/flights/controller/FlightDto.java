package com.example.flights.controller;

public class FlightDto {

    private String username;

    private String flightCode;

    public FlightDto(String username, String flightCode) {
        this.username = username;
        this.flightCode = flightCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }
}
