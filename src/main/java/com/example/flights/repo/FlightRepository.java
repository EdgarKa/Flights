package com.example.flights.repo;

import com.example.flights.domain.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight, String> {
}
