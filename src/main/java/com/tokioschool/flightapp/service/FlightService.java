package com.tokioschool.flightapp.service;

import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import io.micrometer.core.lang.NonNull;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlightService {

    List<FlightDTO> getFlights();
    FlightDTO getFlights(Long flightId);

    FlightDTO cretaedFlight(FlightMvcDTO flightMvcDTO, @Nullable MultipartFile multipartFile);
    FlightDTO editFlight(FlightMvcDTO flightMvcDTO, @Nullable MultipartFile multipartFile);

}
