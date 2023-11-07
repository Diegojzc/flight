package com.tokioschool.flightapp.flight.service;

import com.tokioschool.flightapp.flight.dto.FlightBookingDTO;
import org.springframework.transaction.annotation.Transactional;

public interface FlightBookingService {
    @Transactional
    FlightBookingDTO bookFlight(Long flightId, String userId);
}
