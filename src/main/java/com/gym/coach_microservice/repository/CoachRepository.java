package com.gym.coach_microservice.repository;

import com.gym.coach_microservice.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    
    Optional<Coach> findByEmail(String email);
    
    List<Coach> findByIsActiveTrue();
    
    List<Coach> findBySpecialty(String specialty);
    
    List<Coach> findByHourlyRateLessThanEqual(Double maxRate);
    
    boolean existsByEmail(String email);
}
