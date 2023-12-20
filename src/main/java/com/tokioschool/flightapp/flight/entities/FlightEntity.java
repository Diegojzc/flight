package com.tokioschool.flightapp.flight.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime created;
    private String number;

    @ManyToOne
    @JoinColumn(name ="airport_departures_id", nullable = false)
    private AirportEntity departure;

    @ManyToOne
    @JoinColumn(name ="airport_arrival_id", nullable = false)
    private AirportEntity arrival;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Enumerated(EnumType.STRING)
    private FlightStatusEntity flightStatus;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int occupancy;

    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<FlightBookingEntity> booking;

    @OneToOne(mappedBy = "flight",cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="flight_image_id", referencedColumnName = "id")
    private FlightImageEntity image;


}
