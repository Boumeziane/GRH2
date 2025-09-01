package com.rh.grh.controller;

import com.rh.grh.dto.CandidatureDTO;
import com.rh.grh.service.CandidatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatures")
@RequiredArgsConstructor
public class CandidatureController {

    private final CandidatureService candidatureService;

    // ➕ Ajouter une candidature
    @PostMapping
    public ResponseEntity<CandidatureDTO> create(@RequestBody CandidatureDTO dto) {
        return ResponseEntity.ok(candidatureService.createCandidature(dto));
    }

    // ✏️ Modifier une candidature
    @PutMapping("/{id}")
    public ResponseEntity<CandidatureDTO> update(@PathVariable Long id, @RequestBody CandidatureDTO dto) {
        return ResponseEntity.ok(candidatureService.updateCandidature(id, dto));
    }

    // 🔍 Récupérer une candidature par ID
    @GetMapping("/{id}")
    public ResponseEntity<CandidatureDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(candidatureService.getCandidatureById(id));
    }

    // 📋 Récupérer toutes les candidatures
    @GetMapping
    public ResponseEntity<List<CandidatureDTO>> getAll() {
        return ResponseEntity.ok(candidatureService.getAllCandidatures());
    }

    // 📋 Récupérer toutes les candidatures par offre
    @GetMapping("/offer/{offerId}")
    public ResponseEntity<List<CandidatureDTO>> getByOfferId(@PathVariable Long offerId) {
        return ResponseEntity.ok(candidatureService.getCandidaturesByOfferId(offerId));
    }

    // ❌ Supprimer une candidature
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        candidatureService.deleteCandidature(id);
        return ResponseEntity.noContent().build();
    }
}
