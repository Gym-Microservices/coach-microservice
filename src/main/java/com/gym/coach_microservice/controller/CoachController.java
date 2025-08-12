package com.gym.coach_microservice.controller;

import com.gym.coach_microservice.model.Coach;
import com.gym.coach_microservice.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coaches")
@CrossOrigin(origins = "*")
public class CoachController {
    
    @Autowired
    private CoachService coachService;
    
    @PostMapping
    public ResponseEntity<Coach> addCoach(@RequestBody Coach coach) {
        try {
            Coach addedCoach = coachService.addCoach(coach);
            return ResponseEntity.ok(addedCoach);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoaches() {
        List<Coach> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Coach>> getActiveCoaches() {
        List<Coach> activeCoaches = coachService.getActiveCoaches();
        return ResponseEntity.ok(activeCoaches);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        Optional<Coach> coach = coachService.getCoachById(id);
        return coach.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Coach> getCoachByEmail(@PathVariable String email) {
        Optional<Coach> coach = coachService.getCoachByEmail(email);
        return coach.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<Coach>> getCoachesBySpecialty(@PathVariable String specialty) {
        List<Coach> coaches = coachService.getCoachesBySpecialty(specialty);
        return ResponseEntity.ok(coaches);
    }
    
    @GetMapping("/rate/{maxRate}")
    public ResponseEntity<List<Coach>> getCoachesByMaxRate(@PathVariable Double maxRate) {
        List<Coach> coaches = coachService.getCoachesByMaxRate(maxRate);
        return ResponseEntity.ok(coaches);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long id, @RequestBody Coach coachDetails) {
        try {
            Coach updatedCoach = coachService.updateCoach(id, coachDetails);
            return ResponseEntity.ok(updatedCoach);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateCoach(@PathVariable Long id) {
        try {
            coachService.deactivateCoach(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.ok().build();
    }
}
