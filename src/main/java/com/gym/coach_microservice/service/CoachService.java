package com.gym.coach_microservice.service;

import com.gym.coach_microservice.model.Coach;
import com.gym.coach_microservice.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    
    @Autowired
    private CoachRepository coachRepository;
    
    public Coach addCoach(Coach coach) {
        if (coachRepository.existsByEmail(coach.getEmail())) {
            throw new RuntimeException("Coach with email " + coach.getEmail() + " already exists");
        }
        
        return coachRepository.save(coach);
    }
    
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }
    
    public List<Coach> getActiveCoaches() {
        return coachRepository.findByIsActiveTrue();
    }
    
    public Optional<Coach> getCoachById(Long id) {
        return coachRepository.findById(id);
    }
    
    public Optional<Coach> getCoachByEmail(String email) {
        return coachRepository.findByEmail(email);
    }
    
    public List<Coach> getCoachesBySpecialty(String specialty) {
        return coachRepository.findBySpecialty(specialty);
    }
    
    public List<Coach> getCoachesByMaxRate(Double maxRate) {
        return coachRepository.findByHourlyRateLessThanEqual(maxRate);
    }
    
    public Coach updateCoach(Long id, Coach coachDetails) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coach not found with id: " + id));
        
        coach.setName(coachDetails.getName());
        coach.setSpecialty(coachDetails.getSpecialty());
        coach.setEmail(coachDetails.getEmail());
        
        return coachRepository.save(coach);
    }
    
    public void deactivateCoach(Long id) {
        Coach coach = coachRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coach not found with id: " + id));
        
        coachRepository.save(coach);
    }
    
    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }
}
