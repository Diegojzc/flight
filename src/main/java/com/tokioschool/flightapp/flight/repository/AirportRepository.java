package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport,String> {


}
