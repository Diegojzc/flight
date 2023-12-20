package com.tokioschool.flightapp.flight.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight_images")
public class FlightImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID  resourceId;
    private String filename;
    private String contentType;
    private int size;

    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="flight_id")
    private FlightEntity flight;
}
