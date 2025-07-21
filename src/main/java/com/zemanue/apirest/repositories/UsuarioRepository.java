package com.zemanue.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zemanue.apirest.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
