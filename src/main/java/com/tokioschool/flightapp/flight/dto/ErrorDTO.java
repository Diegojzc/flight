package com.tokioschool.flightapp.flight.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ErrorDTO {

    private String url;
    private String exception;
}
