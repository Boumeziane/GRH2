package com.rh.grh.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp; // Date et heure de l'erreur
    private int status;              // Code HTTP (ex : 400, 404, 500)
    private String error;            // Message d'erreur (ex : "Ressource non trouvée")
    private String path;             // Chemin de la requête qui a causé l'erreur

    // Constructeur pratique sans path
    public ErrorResponse(int status, String error) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
    }
}
