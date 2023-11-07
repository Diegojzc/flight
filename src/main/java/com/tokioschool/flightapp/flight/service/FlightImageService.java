package com.tokioschool.flightapp.flight.service;

import com.tokioschool.flightapp.flight.domain.FlightImage;
import com.tokioschool.flightapp.flight.mvc.dto.FlightImageResourceDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FlightImageService {
    FlightImage saveImage(MultipartFile multipartFile);

     FlightImageResourceDTO getImage(UUID resourceId);

    void delete(UUID resourceId);
}
