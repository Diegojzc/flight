package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,String> {

}
