package com.tokioschool.flightapp.flight.converter;

import com.tokioschool.flightapp.flight.entities.FlightImageEntity;
import com.tokioschool.flightapp.flight.dto.ResourceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration

public class FlightConverterConfiguration {
    private final ModelMapper modelMapper;

    public FlightConverterConfiguration(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureFlightDTOConverter();
    }

    private void configureFlightDTOConverter() {
        modelMapper.map(FlightImageEntity.class, ResourceDTO.class);

    }
}
