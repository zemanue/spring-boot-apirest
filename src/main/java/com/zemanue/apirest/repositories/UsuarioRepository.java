package com.zemanue.apirest.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zemanue.apirest.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /* ¿Hace falta añadir métodos aquí? No, porque JpaRepository ya proporciona los métodos básicos para CRUD y consultas.
    - findAll(): Obtiene todos los usuarios
    - findById(Long id): Obtiene un usuario por su ID
    - save(Usuario usuario): Guarda un usuario (crea o actualiza)
    - delete(Usuario usuario): Elimina un usuario
    - deleteById(Long id): Elimina un usuario por su ID
    - existsById(Long id): Verifica si un usuario existe por su ID
    Estos métodos se usarán en el SERVICIO */

    /* Sin embargo, podemos definir métodos adicionales para consultas específicas si es necesario, y Spring Data JPA generará automáticamente las implementaciones basadas en el nombre del método.
    Para esto, deben seguir el patrón de nomenclatura de Spring Data JPA:
    Por ejemplo: */

    // Método para encontrar un usuario por su nombre
    // Spring Data JPA genera automáticamente una implementación para este método: una consulta que busque por entidades donde el campo "nombre" coincida con el valor proporcionado
    // Se usa Optional para manejar el caso en que no se encuentre el usuario
    Optional<Usuario> findUserByNombre(String nombre);
    
    // Método para encontrar un usuario por su email
    Optional<Usuario> findByEmail(String email);

}
