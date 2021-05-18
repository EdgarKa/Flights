package com.example.flights.controller;

import com.example.flights.service.FlightOptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flightoptions")
public class FlightOptionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlightOptionsController.class);

    FlightOptionsService flightOptionsService;

    @Autowired
    public FlightOptionsController(FlightOptionsService flightOptionsService) {
        this.flightOptionsService = flightOptionsService;
    }

    public FlightOptionsController() {
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('ROLE_PILOT')")
    @ResponseStatus(HttpStatus.OK)
    public void assignFlight(@RequestBody FlightDto flightDto) {
        LOGGER.info("assigning flight to Pilot");
        flightOptionsService.assignFlight(flightDto.getUsername(), flightDto.getFlightCode());
    }

    @PostMapping("/unassign")
    @PreAuthorize("hasRole('ROLE_PILOT')")
    @ResponseStatus(HttpStatus.OK)
    public void unassign(@RequestBody FlightDto flightDto) {
        LOGGER.info("unassigning flight");
        flightOptionsService.unassignFlight(flightDto.getUsername(), flightDto.getFlightCode());
    }
}
