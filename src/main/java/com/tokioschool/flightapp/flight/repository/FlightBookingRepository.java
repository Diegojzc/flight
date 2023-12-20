package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.entities.FlightBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBookingEntity, Long> {
}
