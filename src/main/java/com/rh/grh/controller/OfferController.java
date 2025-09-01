package com.rh.grh.controller;

import com.rh.grh.dto.OfferDTO;
import com.rh.grh.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    // 🔹 Créer une offre
    @PostMapping
    public ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO dto) {
        return ResponseEntity.ok(offerService.createOffer(dto));
    }

    // 🔹 Mettre à jour une offre
    @PutMapping("/{id}")
    public ResponseEntity<OfferDTO> updateOffer(@PathVariable Long id, @RequestBody OfferDTO dto) {
        return ResponseEntity.ok(offerService.updateOffer(id, dto));
    }

    // 🔹 Supprimer une offre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Récupérer une offre par ID
    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    // 🔹 Récupérer toutes les offres
    @GetMapping
    public ResponseEntity<List<OfferDTO>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    // 🔹 Récupérer par titre (mot-clé)
    @GetMapping("/search")
    public ResponseEntity<List<OfferDTO>> getOffersByTitle(@RequestParam String titre) {
        return ResponseEntity.ok(offerService.getOffersByTitle(titre));
    }

    // 🔹 Récupérer par date précise
    @GetMapping("/date")
    public ResponseEntity<List<OfferDTO>> getOffersByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(offerService.getOffersByDate(date));
    }

    // 🔹 Récupérer par plage de dates
    @GetMapping("/date-range")
    public ResponseEntity<List<OfferDTO>> getOffersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(offerService.getOffersByDateRange(startDate, endDate));
    }
}
