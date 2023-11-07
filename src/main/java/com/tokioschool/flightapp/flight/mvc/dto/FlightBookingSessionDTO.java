package com.tokioschool.flightapp.flight.mvc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FlightBookingSessionDTO {
    private Long currentFlightId;
    private Set<Long> discardedFlightIds = new HashSet<>();
}
