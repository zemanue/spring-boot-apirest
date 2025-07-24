package com.zemanue.apirest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zemanue.apirest.models.Usuario;
import com.zemanue.apirest.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    // Inyectamos el repositorio de Usuario
    private final UsuarioRepository usuarioRepository;

    // Se asigna el valor del repositorio a través del constructor y al ser final, no se puede cambiar después de la inyección.
    // No hace falta @Autowired porque solo hay un constructor y Spring lo inyecta automáticamente
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // A partir de aquí, se agregan los métodos para manejar la lógica de negocio relacionada con los usuarios.
    // OBTENER TODOS LOS USUARIOS
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // OBTENER UN USUARIO POR ID
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // CREAR UN NUEVO USUARIO
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ACTUALIZAR UN USUARIO EXISTENTE
    public Usuario updateUsuario(Long id, Usuario usuarioActualizado) {
        if (usuarioRepository.existsById(id)) {
            usuarioActualizado.setId(id);
            return usuarioRepository.save(usuarioActualizado);
        }
        return null; // O lanzar una excepción si el usuario no existe
    }

    // ELIMINAR UN USUARIO POR ID
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    // ELIMINAR POR NOMBRE
    public void deleteUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    // COMPROBAR SI UN USUARIO EXISTE POR ID
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    // COMPROBAR SI UN USUARIO EXISTE POR NOMBRE
    public boolean existsByNombre(String nombre) {
        return usuarioRepository.findUserByNombre(nombre) != null;
        // return usuarioRepository.findAll().stream()
        //         .anyMatch(usuario -> usuario.getNombre().equalsIgnoreCase(nombre));
    }

}
