package com.tokioschool.flightapp.flight.service;

import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface FlightService {

    List<FlightDTO> getFlights();

    FlightDTO getFlights(Long flightId);

    FlightDTO createdFlight(FlightMvcDTO flightMvcDTO, @Nullable MultipartFile multipartFile);

    FlightDTO editFlight(FlightMvcDTO flightMvcDTO, @Nullable MultipartFile multipartFile);

    Map<Long, FlightDTO> getFlightById(HashSet<Long> flightIds);

}
