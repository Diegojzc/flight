package com.tokioschool.flightapp.flight.service.Impl;

import com.tokioschool.flightapp.flight.domain.Flight;
import com.tokioschool.flightapp.flight.domain.FlightBooking;
import com.tokioschool.flightapp.flight.domain.User;
import com.tokioschool.flightapp.flight.dto.FlightBookingDTO;
import com.tokioschool.flightapp.flight.repository.FlightRepository;
import com.tokioschool.flightapp.flight.repository.UserRepository;
import com.tokioschool.flightapp.flight.service.FlightBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlyBookingServiceImpl implements FlightBookingService {

    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public FlightBookingDTO  bookFlight(Long flightId, String userId) {

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("flight with id:%s not found".formatted(flightId)));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user with id:%s not found".formatted(userId)));

        if (flight.getOcuppancy() >= flight.getOcuppancy()) {
            throw new IllegalStateException("Flight with id%s without free places".formatted(flightId));
        }
            FlightBooking flightBooking = FlightBooking.builder()
                    .user(user).flight(flight).locator(UUID.randomUUID()).build();
            flight.getBooking().add(flightBooking);
            flight.setOcuppancy(flight.getOcuppancy() + 1);
            flightRepository.save(flight);
            return modelMapper.map(flightBooking, FlightBookingDTO.class);

    }

}
