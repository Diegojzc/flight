package com.tokioschool.flightapp.flight.mvc.service.impl;

import com.tokioschool.flightapp.flight.dto.FlightBookingDTO;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightBookingSessionDTO;
import com.tokioschool.flightapp.flight.mvc.service.FlightBookingSessionService;
import com.tokioschool.flightapp.flight.service.FlightBookingService;
import com.tokioschool.flightapp.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightBookingSessionServiceImpl implements FlightBookingSessionService {

    private final FlightService flightService;
    private final FlightBookingService flightBookingService;

    @Override
    public void addFlightId(Long flightId, FlightBookingSessionDTO flightBookingSessionDTO) {
        FlightDTO flightDTO = flightService.getFlights(flightId);
        Optional.ofNullable(flightBookingSessionDTO.getCurrentFlightId())
                .ifPresent(discardedFlightId -> flightBookingSessionDTO.getDiscardedFlightIds()
                        .add(discardedFlightId));
        flightBookingSessionDTO.setCurrentFlightId(flightDTO.getId());
        flightBookingSessionDTO.getDiscardedFlightIds().remove(flightDTO.getId());
    }

    @Override
    public FlightBookingDTO confirmFlightBookingSession(FlightBookingSessionDTO flightBookingSessionDTO) {

        return flightBookingService.bookFlight(flightBookingSessionDTO.getCurrentFlightId(), "fsfs");


    }

    @Override
    public Map<Long, FlightDTO> getFlightById(FlightBookingSessionDTO flightBookingSessionDTO) {
        return null;
    }
}
