package com.figo.driver_bot.repository;

import com.figo.driver_bot.domain.Drivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriversRepository extends JpaRepository<Drivers, Integer> {
}
