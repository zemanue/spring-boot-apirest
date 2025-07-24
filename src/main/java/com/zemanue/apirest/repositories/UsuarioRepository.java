package com.zemanue.apirest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zemanue.apirest.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para encontrar un usuario por su nombre
    // Se usa Optional para manejar el caso en que no se encuentre el usuario
    Optional<Usuario> findUserByNombre(String nombre);

}
