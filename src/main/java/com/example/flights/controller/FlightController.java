package com.example.flights.controller;

import com.example.flights.domain.Flight;
import com.example.flights.repo.FlightRepository;
import com.example.flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/flights")
public class FlightController {
    private FlightRepository flightRepository;

    private FlightService flightService;

    @Autowired
    public FlightController(FlightRepository flightRepository, FlightService flightService) {
        this.flightRepository = flightRepository;
        this.flightService = flightService;
    }

    public FlightController() {
    }

    @PostMapping("/addFlight")
    @ResponseStatus(HttpStatus.CREATED)
    public String addFlight(@RequestBody Flight flight) {
        flightService.saveFlight(flight);
        return flight.getCode();
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/flight/{flightcode}")
    public Flight getFlight(@PathVariable("flightcode")String code) {
        findFlightByCode(code);
        return flightService.getFlight(code);
    }

    @PutMapping("/flight/{flightcode}")
    public void updateFlight(@PathVariable("flightcode")String code,
                               @RequestBody Flight flight) {
        findFlightByCode(code);
        flightService.updateFlight(flight);
    }

    @DeleteMapping("/flight/{flightcode}")
    public void deleteFlight(@PathVariable("flightcode")String code) {
        findFlightByCode(code);
        flightService.deleteFlight(code);
    }

    private Flight findFlightByCode(String code) {
        return flightRepository.findById(code).orElseThrow(() ->
                new NoSuchElementException("Flight does not exist " + code));
    }
}
