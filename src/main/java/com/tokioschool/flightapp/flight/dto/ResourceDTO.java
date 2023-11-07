package com.tokioschool.flightapp.flight.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDTO {
    private UUID resourceId;
    private String filename;
    private String contentType;
    private int size;

}
