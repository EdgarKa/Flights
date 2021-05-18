package com.example.flights.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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

    @Column(name = "dep_date")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureDate;

    @Column(name = "dep_time")
    private Time departureTime;

    @Column(name = "arr_date")
    private Date arrivalDate;

    @Column(name = "arr_time")
    private Time arrivalTime;

    @Column(name = "assigned_to")
    private String assignedTo;

    public Flight() {
    }

    public Flight(String code, String departure, String arrival, Date departureDate, Time departureTime, Date arrivalDate, Time arrivalTime, String assignedTo) {
        this.code = code;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.assignedTo = assignedTo;
    }

    public Flight(String code, String departure, String arrival, Date departureDate, Date arrivalDate,
                  String assignedTo) {
        this.code = code;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.assignedTo = assignedTo;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
