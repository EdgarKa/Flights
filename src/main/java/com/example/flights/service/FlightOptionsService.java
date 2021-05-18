package com.example.flights.service;

import com.example.flights.domain.Flight;
import com.example.flights.repo.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FlightOptionsService {
    private FlightRepository flightRepository;

    public FlightOptionsService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void assignFlight(String username, String flightCode) {
        Flight flight = findFlightByCode(flightCode);
        action(flight, username);
    }

    public void unassignFlight(String username, String flightCode) {
        Flight flight = findFlightByCode(flightCode);
        if (flight.getAssignedTo().equals(username))
            action(flight, null);
    }

    private void action (Flight flight, String username) {

        flight.setAssignedTo(username);
        flightRepository.save(flight);
    }

    private Flight findFlightByCode(String code) {
        return flightRepository.findById(code).orElseThrow(() ->
                new NoSuchElementException("Flight does not exist " + code));
    }


}
