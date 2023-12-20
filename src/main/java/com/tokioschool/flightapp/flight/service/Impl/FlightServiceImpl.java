package com.tokioschool.flightapp.flight.service.Impl;

import com.tokioschool.flightapp.flight.entities.AirportEntity;
import com.tokioschool.flightapp.flight.entities.FlightEntity;
import com.tokioschool.flightapp.flight.entities.FlightImageEntity;
import com.tokioschool.flightapp.flight.entities.FlightStatusEntity;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import com.tokioschool.flightapp.flight.repository.AirportRepository;
import com.tokioschool.flightapp.flight.repository.FlightRepository;
import com.tokioschool.flightapp.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        List<FlightEntity> flights= flightRepository.findAll();
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
    public FlightDTO createFlight(FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {
        FlightEntity flight = createOrEdit(new FlightEntity(), flightMvcDTO, multipartFile);
        return modelMapper.map(flight, FlightDTO.class);
    }
    @Override
    public FlightDTO editFlight(FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {
        FlightEntity flight = flightRepository.findById(flightMvcDTO.getId())
                .orElseThrow(()->new IllegalArgumentException("Flight witn id%s not found"
                        .formatted(flightMvcDTO.getId())));
        flight= createOrEdit(flight, flightMvcDTO, multipartFile);
        return modelMapper.map(flight, FlightDTO.class);
    }

    @Transactional
    protected FlightEntity createOrEdit(FlightEntity flight, FlightMvcDTO flightMvcDTO, MultipartFile multipartFile) {

        AirportEntity departure = getAirport(flightMvcDTO.getDeparture());
        AirportEntity arrival = getAirport(flightMvcDTO.getArrival());

        FlightImageEntity flightImage = flight.getImage();
        if (!multipartFile.isEmpty()){
            flightImage= flightImageService.saveImage(multipartFile);
            flightImage.setFlight(flight);
        }
        flightImageService.saveImage(multipartFile);

        flight.setCapacity(flightMvcDTO.getCapacity());
        flight.setNumber(flightMvcDTO.getNumber());
        flight.setFlightStatus(FlightStatusEntity.valueOf(flightMvcDTO.getStatus()));
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setDepartureTime(flightMvcDTO.getDayTime());
        flight.setImage(flightImage);
        return flightRepository.save(flight);
    }

    private AirportEntity getAirport(String acronym) {

        return (AirportEntity) airportRepository.findByAcronym(acronym)

                .orElseThrow(()->new IllegalArgumentException("Flight witn id%s not found"
                        .formatted(acronym)));
    }



    @Override
    public Map<Long, FlightDTO> getFlightById(HashSet<Long> flightIds) {
        return flightRepository.findAllById(flightIds)
                .stream().collect(Collectors.toMap(FlightEntity::getId, flight -> modelMapper
                        .map(flight, FlightDTO.class)));
    }

    @Override
    public FlightDTO getFlight(Long aLong) {
        return null;
    }
}
