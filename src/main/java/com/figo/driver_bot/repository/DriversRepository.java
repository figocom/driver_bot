package com.figo.driver_bot.repository;

import com.figo.driver_bot.domain.Drivers;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriversRepository extends JpaRepository<Drivers, Integer> {
    Optional<Drivers> findByDriverCardNumber(@NotBlank String driverCardNumber);
}
