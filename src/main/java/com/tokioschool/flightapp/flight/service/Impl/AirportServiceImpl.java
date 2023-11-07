package com.tokioschool.flightapp.flight.service.Impl;

import com.tokioschool.flightapp.flight.domain.Airport;
import com.tokioschool.flightapp.flight.dto.AirportDTO;
import com.tokioschool.flightapp.flight.repository.AirportRepository;
import com.tokioschool.flightapp.flight.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AirportDTO> getAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream().map(airport -> modelMapper.map(airport, AirportDTO.class)).toList();
    }
}
