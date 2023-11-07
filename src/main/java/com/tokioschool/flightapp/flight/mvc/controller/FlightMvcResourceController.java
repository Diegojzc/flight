package com.tokioschool.flightapp.flight.mvc.controller;

import com.tokioschool.flightapp.flight.mvc.dto.FlightImageResourceDTO;
import com.tokioschool.flightapp.flight.service.FlightImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FlightMvcResourceController {

    private final FlightImageService flightImageService;

    @GetMapping("/flight/resources/{resourceId}")
    public ResponseEntity<byte[]> getFlightResources(@PathVariable(name="resourceId")UUID resourceId){
        FlightImageResourceDTO flightImageResourceDTO = flightImageService.getImage(resourceId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(flightImageResourceDTO.getContentType()))
                .contentLength(flightImageResourceDTO.getSize())
                .body(flightImageResourceDTO.getContent());
    }

    @GetMapping("/flight/downloads/{resourceId}")
    public ResponseEntity<byte[]> downloadFlightResource(
            @PathVariable(value ="resourceId") UUID resouerceId){
        FlightImageResourceDTO flightImageResourceDTO = flightImageService.getImage(resouerceId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename= " + flightImageResourceDTO.getFilename());
                httpHeaders.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(flightImageResourceDTO.getSize()));
                httpHeaders.add(HttpHeaders.CONTENT_TYPE,flightImageResourceDTO.getContentType());

                return ResponseEntity.ok().headers(httpHeaders).body(flightImageResourceDTO.getContent());
    }

}
