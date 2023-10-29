package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.domain.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
}
