package com.tokioschool.flightapp.flight.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FlightDTO {

    private Long id;
    private String number;
    private String departureAcronym;
    private String arrivalAcronym;
    private LocalDateTime departureTime;
    private Status status;
    private Integer capacity;
    private Integer occupancy;
    private ResourceDTO image;

    public enum Status{
        SCHEDULED,
        CANCELED
    }
}
