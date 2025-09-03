package com.rh.grh.dto;

import com.rh.grh.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private Long id;
    private String username;
    private Role role;
    private String token; // si tu utilises JWT, sinon tu peux ignorer

    public AuthResponse(String token) {
        this.token = token;
    }
}
