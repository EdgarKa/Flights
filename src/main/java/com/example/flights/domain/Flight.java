package com.example.flights.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "departure")
    private String departure;

    @Column(name = "arrival")
    private String arrival;

    public Flight() {
    }

    public Flight(String code, String departure, String arrival) {
        this.code = code;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
}
