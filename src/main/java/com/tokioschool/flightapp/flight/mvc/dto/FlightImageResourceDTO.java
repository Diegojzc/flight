package com.tokioschool.flightapp.flight.mvc.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FlightImageResourceDTO {

    String contentType;
    int size;
    String filename;
    byte[] content;

}
