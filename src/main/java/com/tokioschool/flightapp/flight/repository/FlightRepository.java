package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
