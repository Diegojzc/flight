package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.entities.FlightImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightImageRepository extends CrudRepository<FlightImageEntity,Long> {
}
