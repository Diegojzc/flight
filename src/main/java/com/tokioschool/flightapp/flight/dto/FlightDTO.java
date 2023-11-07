package com.tokioschool.flightapp.flight.dto;

import ch.qos.logback.core.status.Status;
import com.tokioschool.flightapp.flight.domain.Airport;
import com.tokioschool.flightapp.flight.domain.FlightBooking;
import com.tokioschool.flightapp.flight.domain.FlightImage;
import com.tokioschool.flightapp.flight.domain.FlightStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

import static com.tokioschool.flightapp.flight.domain.FlightStatus.CANCELED;
import static com.tokioschool.flightapp.flight.domain.FlightStatus.SCHEDULED;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FlightDTO {
    private Long id;
    private String number;
    private String departureAcromyn;
    private String arrivalAcronym;
    private LocalDateTime departureTime;
    private Status status;
    private Integer capacity;
    private Integer ocuppancy;
    private ResourceDTO image;

    public enum Status{
        SCHEDULED,
        CANCELED
    }
}
