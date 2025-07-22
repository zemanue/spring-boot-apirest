package com.zemanue.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data // Proporciona getters, setters, toString, etc. gracias a Lombok
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    
    private String nombre;
    private String email;
}
