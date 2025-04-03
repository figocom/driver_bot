package com.figo.driver_bot.repository;

import com.figo.driver_bot.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findByIsCurrentActiveTrue();
}
