package com.tokioschool.flightapp.store.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ResourceContentDTO {

    UUID resouceId;
    byte[] content;
    String contentType;
    String filename;
    String description;
    int size;
}
