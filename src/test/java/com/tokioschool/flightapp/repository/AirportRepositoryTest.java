package com.tokioschool.flightapp.repository;

import com.tokioschool.flightapp.flight.domain.Airport;
import com.tokioschool.flightapp.flight.repository.AirportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class AirportRepositoryTest {
    @Autowired
    private AirportRepository airportRepository;

    @BeforeEach
    void beforeEach(){
        Airport bcn = Airport.builder().acronym("BCN").name("Barcelona airport").country("Spain").build();
        Airport gla = Airport.builder().acronym("GLA").name("Glasgow").country("United Kingdom").build();

        airportRepository.save(bcn);
        airportRepository.save(gla);

    }

    @AfterEach
    void afterEach(){
        airportRepository.deleteAll();

    }
    @Test
    void givenAirport_whenFindAllAirport_thenReturnOk(){
        List<Airport> airport = airportRepository.findAll();
        Assertions.assertEquals(2,airport.size());
    }

}
