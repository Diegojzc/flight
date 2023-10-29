package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.domain.FlightImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightImageRepository extends CrudRepository<FlightImage,Long> {
}
