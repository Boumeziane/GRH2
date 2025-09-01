package com.rh.grh.dto;

import com.rh.grh.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String username;
    private Role role;
    private String token; // si tu utilises JWT, sinon tu peux ignorer

    public AuthResponse(String token) {
        this.token = token;
    }
}
