package com.tokioschool.flightapp.flight.converter;

import com.tokioschool.flightapp.flight.domain.FlightImage;
import com.tokioschool.flightapp.flight.dto.ResourceDTO;
import lombok.RequiredArgsConstructor;
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
        modelMapper.map(FlightImage.class, ResourceDTO.class);
    }
}
