package com.tokioschool.flightapp.flight.service.Impl;

import com.tokioschool.flightapp.flight.domain.Airport;
import com.tokioschool.flightapp.flight.domain.Flight;
import com.tokioschool.flightapp.flight.domain.FlightImage;
import com.tokioschool.flightapp.flight.domain.FlightStatus;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import com.tokioschool.flightapp.flight.repository.AirportRepository;
import com.tokioschool.flightapp.flight.repository.FlightRepository;
import com.tokioschool.flightapp.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;
    private final  AirportRepository airportRepository;
    private final FlightImageServiceImpl flightImageService;


    @Override
    public List<FlightDTO> getFlights() {
        List<Flight> flights= flightRepository.findAll();
        return flights.stream().map(flight-> modelMapper.map(flight,FlightDTO.class)).toList();
    }

    @Override
    public FlightDTO getFlights(Long flightId) {
        return flightRepository.findById(flightId)
                .map(flight->modelMapper.map(flight, FlightDTO.class))
                .orElseThrow(()->new IllegalArgumentException("Flight witn id%s not found"
                        .formatted(flightId)));
    }

    @Override
    public FlightDTO createdFlight(FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {
        Flight flight = createdOrEdit(new Flight(), flightMvcDTO, multipartFile);
        return modelMapper.map(flight, FlightDTO.class);
    }

    private Flight createdOrEdit(Flight flight, FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {

        Airport departure = getAirport(flightMvcDTO.getDeparture());
        Airport arrival = getAirport(flightMvcDTO.getArrival());

        FlightImage flightImage = flight.getImage();
        if (!multipartFile.isEmpty()){
            flightImage= flightImageService.saveImage(multipartFile);
            flightImage.setFlight(flight);
        }
        flightImageService.saveImage(multipartFile);

        flight.setCapacity(flightMvcDTO.getCapacity());
        flight.setNumber(flightMvcDTO.getNumber());
        flight.setFlightStatus(FlightStatus.valueOf(flightMvcDTO.getStatus()));
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setDepartureTime(flightMvcDTO.getDayTime());
        flight.setImage(flightImage);
        return flightRepository.save(flight);
    }

    private Airport getAirport(String acronym) {

        return (Airport) airportRepository.findByAcronym(acronym)

                .orElseThrow(()->new IllegalArgumentException("Flight witn id%s not found"
                        .formatted(acronym)));
    }

    @Override
    public FlightDTO editFlight(FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {
        Flight flight = flightRepository.findById(flightMvcDTO.getId())
                .orElseThrow(()->new IllegalArgumentException("Flight witn id%s not found"
                        .formatted(flightMvcDTO.getId())));
        flight= createdOrEdit(flight, flightMvcDTO, multipartFile);
        return modelMapper.map(flight, FlightDTO.class);
    }

    @Override
    public Map<Long, FlightDTO> getFlightById(HashSet<Long> flightIds) {
        return flightRepository.findAllById(flightIds)
                .stream().collect(Collectors.toMap(Flight::getId, flight -> modelMapper
                        .map(flight, FlightDTO.class)));
    }
}
