package com.tokioschool.flightapp.flight.mvc.controller;

import com.tokioschool.flightapp.flight.dto.AirportDTO;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import com.tokioschool.flightapp.service.AirportService;
import com.tokioschool.flightapp.service.FlightService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FlightMvcController {

    private final FlightService flightService;
    private final AirportService airportService;

    @GetMapping("/flight/flights")
    public ModelAndView getFlight(){

        List<FlightDTO> flights= flightService.getFlights();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("flight/flights/flights-list");
        modelAndView.addObject("flights", flights);
        return modelAndView;

    }

    @GetMapping({"/flight/flights-edit","/fligth/flights-edit","/flight/flight-edit/{flightId}"})

        public ModelAndView createdOrEditFlight(@PathVariable(name = "flightId", required = false) Long flightId, Model model){

        Optional<FlightDTO> maybeFlightDTO= Optional.ofNullable(flightId).map(flightService::getFlights);


        FlightMvcDTO flightMvcDTO= maybeFlightDTO
                .map(flightDTO->
                        FlightMvcDTO.builder()
                                .id(flightDTO.getId())
                                .number(flightDTO.getNumber())
                                .capacity(flightDTO.getCapacity())
                                .arrival(flightDTO.getArrivalAcronym())
                                .departure(flightDTO.getDepartureAcromyn())
                                .status(flightDTO.getStatus().name())
                                .dayTime(flightDTO.getDepartureTime())
                                .build())
                .orElseGet(FlightMvcDTO::new);

        ModelAndView modelAndView = populateCreatedOrEditFlightModel(maybeFlightDTO.orElse(null),model);
        modelAndView.setViewName("flight/flights-edit");
        return modelAndView;
        }

    private ModelAndView populateCreatedOrEditFlightModel(@Nullable FlightDTO flightMvcDTO, Model model) {
        List<AirportDTO> airports = airportService.getAirports();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addAllObjects(model.asMap());
        modelAndView.addObject("flight",flightMvcDTO);
        modelAndView.addObject("airport",airports);
        return modelAndView;
    }


}
