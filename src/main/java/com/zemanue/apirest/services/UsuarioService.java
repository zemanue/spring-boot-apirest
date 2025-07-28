package com.zemanue.apirest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zemanue.apirest.exceptions.UsuarioNotFoundException;
import com.zemanue.apirest.exceptions.InvalidDataException;
import com.zemanue.apirest.exceptions.DuplicateResourceException;
import com.zemanue.apirest.models.Usuario;
import com.zemanue.apirest.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    // Inyectamos el repositorio de Usuario
    private final UsuarioRepository usuarioRepository;

    // Se asigna el valor del repositorio a través del constructor y al ser final,
    // no se puede cambiar después de la inyección.
    // No hace falta @Autowired porque solo hay un constructor y Spring lo inyecta
    // automáticamente
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // A partir de aquí, se agregan los métodos para manejar la lógica de negocio
    // relacionada con los usuarios.
    // OBTENER TODOS LOS USUARIOS
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // OBTENER UN USUARIO POR ID
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    // CREAR UN NUEVO USUARIO
    public Usuario createUsuario(Usuario usuario) {
        validateUsuario(usuario);
        
        // Validar email único
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new DuplicateResourceException("usuario", "email", usuario.getEmail());
        }
        
        return usuarioRepository.save(usuario);
    }

    // ACTUALIZAR UN USUARIO EXISTENTE
    public Usuario updateUsuario(Long id, Usuario usuarioActualizado) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("No se puede actualizar. El usuario con ID " + id + " no existe");
        }
        
        validateUsuario(usuarioActualizado);
        
        // Validar email único (excluyendo el usuario actual)
        usuarioRepository.findByEmail(usuarioActualizado.getEmail())
            .ifPresent(existingUser -> {
                if (!existingUser.getId().equals(id)) {
                    throw new DuplicateResourceException("usuario", "email", usuarioActualizado.getEmail());
                }
            });
        
        usuarioActualizado.setId(id);
        return usuarioRepository.save(usuarioActualizado);
    }

    // ELIMINAR UN USUARIO POR ID
    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("No se puede eliminar. El usuario con ID " + id + " no existe");
        }
        usuarioRepository.deleteById(id);
    }

    // COMPROBAR SI UN USUARIO EXISTE POR ID
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }

    // MÉTODO PRIVADO PARA VALIDAR DATOS DEL USUARIO
    private void validateUsuario(Usuario usuario) {
        List<String> errors = new ArrayList<>();

        // Validar nombre
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            errors.add("Campo 'nombre': es obligatorio");
        } else if (usuario.getNombre().trim().length() < 2) {
            errors.add("Campo 'nombre': debe tener al menos 2 caracteres");
        } else if (usuario.getNombre().trim().length() > 100) {
            errors.add("Campo 'nombre': no puede tener más de 100 caracteres");
        }

        // Validar email
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            errors.add("Campo 'email': es obligatorio");
        } else {
            String email = usuario.getEmail().trim();
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                errors.add("Campo 'email': formato inválido. Debe ser un email válido (ejemplo: usuario@dominio.com)");
            } else if (email.length() > 150) {
                errors.add("Campo 'email': no puede tener más de 150 caracteres");
            }
        }

        // Si hay errores, lanzar excepción
        if (!errors.isEmpty()) {
            throw new InvalidDataException(errors);
        }
    }

}
