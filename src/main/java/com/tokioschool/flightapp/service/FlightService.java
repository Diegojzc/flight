package com.tokioschool.flightapp.service;

import com.tokioschool.flightapp.flight.dto.FlightDTO;

import java.util.List;

public interface FlightService {

    List<FlightDTO> getFlights();
}
