package com.example.flights.service;

import com.example.flights.domain.Flight;
import com.example.flights.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    private FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        flightRepository.findAll().forEach(flights::add);
        return flights;
    }

    public Flight getFlight(String code) {
        return flightRepository.findById(code).get();
    }

    public void updateFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public void deleteFlight(String code) {
        flightRepository.deleteById(code);
    }
}
