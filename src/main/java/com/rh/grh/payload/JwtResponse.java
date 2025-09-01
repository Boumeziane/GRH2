package com.rh.grh.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;           // Le token JWT
    private String type = "Bearer"; // Type de token, par défaut "Bearer"
    private Long id;                // ID de l'utilisateur
    private String username;        // Nom d'utilisateur ou email
    private String email;           // Email
    private List<String> roles;     // Liste des rôles de l'utilisateur

    // Constructeur pratique sans type
    public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
