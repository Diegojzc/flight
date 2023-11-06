package com.tokioschool.flightapp.service.Impl;

import com.tokioschool.flightapp.flight.domain.Airport;
import com.tokioschool.flightapp.flight.domain.Flight;
import com.tokioschool.flightapp.flight.domain.FlightStatus;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import com.tokioschool.flightapp.flight.repository.AirportRepository;
import com.tokioschool.flightapp.flight.repository.FlightRepository;
import com.tokioschool.flightapp.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;
    private AirportRepository airportRepository;


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
    public FlightDTO cretaedFlight(FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {
        Flight flight = createdOrEdit(new Flight(), flightMvcDTO, multipartFile);
        return modelMapper.map(flight, FlightDTO.class);
    }

    private Flight createdOrEdit(Flight flight, FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {

        Airport departure = getAirport(flightMvcDTO.getDeparture());
        Airport arrival = getAirport(flightMvcDTO.getArrival());

        flight.setCapacity(flightMvcDTO.getCapacity());
        flight.setNumber(flightMvcDTO.getNumber());
        flight.setFlightStatus(FlightStatus.valueOf(flightMvcDTO.getStatus()));
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setDepartureTime(flightMvcDTO.getDayTime());
        flight.setImage(null);
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
}
