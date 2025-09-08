package com.gym.coach_microservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "coaches")
@Schema(description = "Entity that represents a gym coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the coach", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Full name of the coach", example = "John Smith", required = true)
    private String name;

    @Column(nullable = false)
    @Schema(description = "Coach specialty", example = "Yoga", required = true)
    private String specialty;

    @Column(unique = true)
    @Schema(description = "Coach email", example = "john.smith@gym.com")
    private String email;
}
