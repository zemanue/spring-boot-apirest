package com.zemanue.apirest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        // No se necesita try-catch aquí porque GlobalExceptionHandler manejará UsuarioNotFoundException en caso de que la ID indicada no exista
        Usuario usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
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
        // Sin try-catch: GlobalExceptionHandler manejará UsuarioNotFoundException
        Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // DELETE /api/usuarios/{id} - Eliminar un usuario por ID
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        // Sin try-catch: GlobalExceptionHandler manejará UsuarioNotFoundException
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/usuarios/{id}/exists - Verificar si un usuario existe por ID
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        boolean exists = usuarioService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}
