package com.tokioschool.flightapp.flight.mvc.controller;

import com.tokioschool.flightapp.flight.dto.FlightBookingDTO;
import com.tokioschool.flightapp.flight.dto.FlightDTO;
import com.tokioschool.flightapp.flight.mvc.dto.FlightBookingSessionDTO;
import com.tokioschool.flightapp.flight.mvc.service.FlightBookingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@SessionAttributes(names= "FlightBookingSessionDTO")
public class FlightBookingMvcController {

    private final FlightBookingSessionService flightBookingSessionService;

    @ModelAttribute(name="FlightBookingSessionDTO")
    public FlightBookingSessionDTO flightBookingSessionDTO(){
        return new FlightBookingSessionDTO();
    }
    @GetMapping("/flight/blookings-add/{flightId}")
    public RedirectView addBooking(
            @PathVariable("flightId") Long flightId,
            @ModelAttribute(value="FlightBookingSessionDTO")
            FlightBookingSessionDTO flightBookingSessionDTO) {

        flightBookingSessionService.addFlightId(flightId,flightBookingSessionDTO());
        return new RedirectView("/flight/bookings");
    }
    @GetMapping("/flight/bookings")
    public ModelAndView getBookings(
            @ModelAttribute(value="FlightBookingSessionDTO")
            FlightBookingSessionDTO flightBookingSessionDTO){
        Map<Long, FlightDTO> flightMap= flightBookingSessionService.getFlightById(flightBookingSessionDTO);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("flightBookingsession",flightBookingSessionDTO);
        modelAndView.addObject("flightMap",flightMap);
        modelAndView.setViewName("/flight/bookings/bookings-list");

        return  modelAndView;
    }

    @GetMapping("/flight/booking-confirm")
    public RedirectView confirmBooking(
        @ModelAttribute(value="FlightBookingSessionDTO")
        FlightBookingSessionDTO flightBookingSessionDTO,
        SessionStatus sessionStatus){
        FlightBookingDTO flightBookingDTO= flightBookingSessionService
                .confirmFlightBookingSession(flightBookingSessionDTO);

        sessionStatus.setComplete();

        return  new RedirectView("/flight/booking-confirm/%s".formatted(flightBookingDTO.getLocator()));

    }

    @GetMapping("/flight/bookings-confirm/{bookingLocator}")
    public ModelAndView confirmedBooking(@PathVariable("bookingLocator") String bookingLocator){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("locator", bookingLocator);
        modelAndView.setViewName("/flight/bookings/bookings-confirm");
        return modelAndView;
    }
}
