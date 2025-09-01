package com.rh.grh.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok génère automatiquement getters, setters, constructeurs
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private boolean success; // true si l'opération a réussi, false sinon
    private String message;  // message descriptif
    private Object data;     // optionnel, contient les données retournées

    // Constructeur pratique sans data
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
