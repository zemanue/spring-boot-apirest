package com.zemanue.apirest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zemanue.apirest.exceptions.UsuarioNotFoundException;
import com.zemanue.apirest.models.Usuario;
import com.zemanue.apirest.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    // Constructor injection (no @Autowired needed)
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /api/usuarios - Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
        // Equivalente: return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // GET /api/usuarios/{id} - Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/usuarios - Crear un nuevo usuario
    @PostMapping 
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        // Equivalente: return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // PUT /api/usuarios/{id} - Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
            // Equivalente: return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/usuarios/{id} - Eliminar un usuario por ID
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
            // Equivalente: return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/usuarios/{id}/exists - Verificar si un usuario existe por ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        boolean exists = usuarioService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}
