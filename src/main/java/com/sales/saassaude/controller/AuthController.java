package com.sales.saassaude.controller;

import com.sales.saassaude.config.JwtUtil;
import com.sales.saassaude.models.UsuarioLogin;
import com.sales.saassaude.service.UsuarioLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioLoginService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 1. Valida credenciais
        boolean autenticado = usuarioService.autenticar(loginRequest.getUsername(), loginRequest.getPassword());
        
        if (!autenticado) {
            return ResponseEntity.badRequest().body("Credenciais inválidas");
        }

        // 2. Busca usuário completo
        Optional<UsuarioLogin> usuarioOpt = usuarioService.buscarPorUsername(loginRequest.getUsername());
        
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        // 3. Gera token JWT
        String token = jwtUtil.generateToken(loginRequest.getUsername());

        // 4. Retorna token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", loginRequest.getUsername());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioLogin usuario) {
        try {
            UsuarioLogin usuarioSalvo = usuarioService.cadastrarUsuarioLogin(usuario);
            return ResponseEntity.ok("Usuário criado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

// Classe auxiliar para receber dados de login
class LoginRequest {
    private String username;
    private String password;
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}