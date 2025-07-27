package com.zemanue.apirest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zemanue.apirest.exceptions.UsuarioNotFoundException;
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
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario con ID " + id + " no existe"));
    }
    
    public Usuario getUsuarioByNombre(String nombre) {
        return usuarioRepository.findUserByNombre(nombre).orElseThrow(() -> new UsuarioNotFoundException("El usuario con nombre " + nombre + " no existe"));
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
        throw new UsuarioNotFoundException("No se puede actualizar. El usuario con ID " + id + " no existe");
    }

    // ELIMINAR UN USUARIO POR ID
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("No se puede eliminar. El usuario con ID " + id + " no existe");
        }
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
        return usuarioRepository.findUserByNombre(nombre).isPresent();
    }

}
