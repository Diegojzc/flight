package com.tokioschool.flightapp.flight.service;

import com.tokioschool.flightapp.flight.entities.FlightImageEntity;
import com.tokioschool.flightapp.flight.mvc.dto.FlightImageResourceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FlightImageService {
    FlightImageEntity saveImage(MultipartFile multipartFile);

     FlightImageResourceDTO getImage(UUID resourceId);

    void delete(UUID resourceId);
}
