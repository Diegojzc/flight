package com.tokioschool.flightapp.flight.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FlightMvcController {

    @GetMapping("/flight/flights")
    public ModelAndView getFlight(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("flight/flights/flights-list");
        modelAndView.addObject("flights", List.of());
        return modelAndView;
    }
}
