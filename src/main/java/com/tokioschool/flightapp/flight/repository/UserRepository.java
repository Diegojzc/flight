package com.tokioschool.flightapp.flight.repository;

import com.tokioschool.flightapp.flight.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,String> {

}
