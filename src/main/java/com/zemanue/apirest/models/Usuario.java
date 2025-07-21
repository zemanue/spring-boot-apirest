package com.zemanue.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Proporciona getters, setters, toString, etc. gracias a Lombok
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
}
