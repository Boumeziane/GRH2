/*package com.rh.grh.controller;

import com.rh.grh.dto.AuthRequest;
import com.rh.grh.dto.AuthResponse;
import com.rh.grh.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 🔑 Connexion
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // 📝 Inscription
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    // ⚡ Vérifier si username existe déjà (utile pour frontend)
    @GetMapping("/exists/{username}")
    public ResponseEntity<Boolean> usernameExists(@PathVariable String username) {
        return ResponseEntity.ok(authService.usernameExists(username));
    }
}
*/