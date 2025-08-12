package com.gym.coach_microservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String specialty;
    
    @Column(unique = true)
    private String email;
    
}
