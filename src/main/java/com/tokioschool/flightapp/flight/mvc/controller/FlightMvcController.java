package com.tokioschool.flightapp.flight.mvc.controller;

import com.tokioschool.flightapp.flight.dto.AirportDTO;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightMvcDTO;
import com.tokioschool.flightapp.flight.mvc.validator.FlightMvcDTOValidator;
import com.tokioschool.flightapp.flight.service.AirportService;
import com.tokioschool.flightapp.flight.service.FlightService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class FlightMvcController {
    private final FlightService flightService;
    private final AirportService airportService;
    private final FlightMvcDTOValidator flightMvcDTOValidator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(flightMvcDTOValidator);
    }
    @GetMapping("/flight/flights")
    public ModelAndView getFlight(){
        List<FlightDTO> flights = flightService.getFlights();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("flight/flights/flights-list");
        modelAndView.addObject("flights", flights);
        return modelAndView;
    }
    @GetMapping
    public ModelAndView createOrEditFlight(@PathVariable(name="flightId", required = false) Long flightId, Model model) {
        Optional<FlightDTO> maybeFlightDto = Optional.ofNullable(flightId).map(flightService::getFlight);

        FlightMvcDTO flightMvcDTO =
                maybeFlightDto
                .map(flightDTO ->
                        FlightMvcDTO.builder()
                                .id(flightDTO.getId())
                                .number(flightDTO.getNumber())
                                .capacity(flightDTO.getCapacity())
                                .arrival(flightDTO.getArrivalAcronym())
                                .departure(flightDTO.getDepartureAcronym())
                                .status(flightDTO.getStatus().name())
                                .dayTime(flightDTO.getDepartureTime())
                                .build())
                        .orElseGet(FlightMvcDTO::new);

        ModelAndView modelAndView= populateCreationOrEditFlightModel(flightMvcDTO, maybeFlightDto.orElse(null), model);
        modelAndView.setViewName("flight/flights/flights-edit");
        return modelAndView;

    }

   @PostMapping({"/flight/flights-edit ","/flight/flights/edit","/flight/flights/edit/{flightId}"})
   public ModelAndView createOrEditFlight(
           @ModelAttribute("flight")  @Valid FlightMvcDTO flightMvcDTO,
           BindingResult bindingResult,
           @RequestParam("image")MultipartFile multiparFile,
           @PathVariable(name= "flightId" , required = false)Long flightId,
           Model model){

        if(bindingResult.hasErrors()){

            Optional<FlightDTO> maybeFlightDTO = Optional.ofNullable(flightId).map(flightService::getFlight);
            ModelAndView modelAndView = populateCreationOrEditFlightModel(flightMvcDTO, maybeFlightDTO.orElse(null), model);
            modelAndView.setViewName("flight/flights/flights-edit");
            return modelAndView;
        }
        if (flightId== null){
            flightService.createFlight(flightMvcDTO,multiparFile);
        }else {
            flightService.editFlight(flightMvcDTO, multiparFile);
        }
        return new ModelAndView("redirect:/flight/flights");
   }


    private ModelAndView populateCreationOrEditFlightModel(FlightMvcDTO flightMvcDTO, @NonNull FlightDTO flightDTO, Model model) {
        List<AirportDTO>airports=airportService.getAirports();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addAllObjects(model.asMap());
        modelAndView.addObject("flight",flightMvcDTO);
        modelAndView.addObject("airport",airports);
        modelAndView.addObject("flightImageId",
                Optional.of(flightDTO)
                        .map(f->flightDTO.getImage())
                        .map(i->getResourceId())
                        .orElse(null));
        return null;
    }

    private Object getResourceId() {
        return  null;
    }


}
