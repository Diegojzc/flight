package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
