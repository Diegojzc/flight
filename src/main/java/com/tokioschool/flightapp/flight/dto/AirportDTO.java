package com.tokioschool.flightapp.flight.dto;

import lombok.*;
import org.springframework.context.annotation.Scope;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportDTO {
    private String acronym;
    private String name;
    private String country;
}
