package com.tokioschool.flightapp.flight.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/flight")
public class HomeMvcController {

    @GetMapping
    public String getIndex(){
        return "flight/index";
    }

    @GetMapping("/home")
    public String home(){
        return "flight/home";
    }
    @GetMapping("/my-error")
    public String getMyError(){
        throw new RuntimeException("This is an error");
    }
}
