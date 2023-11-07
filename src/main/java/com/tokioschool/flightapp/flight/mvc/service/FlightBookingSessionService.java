package com.tokioschool.flightapp.flight.mvc.service;

import com.tokioschool.flightapp.flight.dto.FlightBookingDTO;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightBookingSessionDTO;

import java.util.Map;

public interface FlightBookingSessionService {

    void  addFlightId(Long flightId, FlightBookingSessionDTO flightBookingSessionDTO);

    FlightBookingDTO confirmFlightBookingSession(FlightBookingSessionDTO flightBookingSessionDTO);

    Map<Long, FlightDTO> getFlightById(FlightBookingSessionDTO flightBookingSessionDTO);
}
