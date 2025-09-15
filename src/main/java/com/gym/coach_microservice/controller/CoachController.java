package com.gym.coach_microservice.controller;

import com.gym.coach_microservice.model.Coach;
import com.gym.coach_microservice.service.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coaches")
@CrossOrigin(origins = "*")
@Tag(name = "Coaches", description = "API for gym coach management")
@SecurityRequirement(name = "bearer-key")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @PostMapping
    @Operation(summary = "Add new coach", description = "Registers a new coach in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach added successfully"),
            @ApiResponse(responseCode = "400", description = "Error in input data")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Coach> addCoach(@RequestBody Coach coach) {
        try {
            Coach addedCoach = coachService.addCoach(coach);
            return ResponseEntity.ok(addedCoach);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @Operation(summary = "Get all coaches", description = "Returns a list of all registered coaches")
    @ApiResponse(responseCode = "200", description = "List of coaches retrieved successfully")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH') or hasRole('ROLE_MEMBER')")
    public ResponseEntity<List<Coach>> getAllCoaches() {
        List<Coach> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get coach by ID", description = "Returns a specific coach by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach found"),
            @ApiResponse(responseCode = "404", description = "Coach not found")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH') or hasRole('ROLE_MEMBER')")
    public ResponseEntity<Coach> getCoachById(@Parameter(description = "Coach ID") @PathVariable Long id) {
        Optional<Coach> coach = coachService.getCoachById(id);
        return coach.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get coach by email", description = "Returns a specific coach by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach found"),
            @ApiResponse(responseCode = "404", description = "Coach not found")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH') or hasRole('ROLE_MEMBER')")
    public ResponseEntity<Coach> getCoachByEmail(
            @Parameter(description = "Coach email") @PathVariable String email) {
        Optional<Coach> coach = coachService.getCoachByEmail(email);
        return coach.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialty/{specialty}")
    @Operation(summary = "Get coaches by specialty", description = "Returns all coaches with a specific specialty")
    @ApiResponse(responseCode = "200", description = "List of coaches retrieved successfully")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH') or hasRole('ROLE_MEMBER')")
    public ResponseEntity<List<Coach>> getCoachesBySpecialty(
            @Parameter(description = "Coach specialty") @PathVariable String specialty) {
        List<Coach> coaches = coachService.getCoachesBySpecialty(specialty);
        return ResponseEntity.ok(coaches);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update coach", description = "Updates the data of an existing coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach updated successfully"),
            @ApiResponse(responseCode = "404", description = "Coach not found")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COACH')")
    public ResponseEntity<Coach> updateCoach(@Parameter(description = "Coach ID") @PathVariable Long id,
            @RequestBody Coach coachDetails) {
        try {
            Coach updatedCoach = coachService.updateCoach(id, coachDetails);
            return ResponseEntity.ok(updatedCoach);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete coach", description = "Deletes a coach from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Coach not found")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCoach(@Parameter(description = "Coach ID") @PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.ok().build();
    }
}
