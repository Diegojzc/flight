package com.tokioschool.flightapp.service;

import com.tokioschool.flightapp.flight.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    List<AirportDTO> getAirports();
}
