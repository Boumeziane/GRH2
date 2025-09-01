package com.rh.grh.controller;

import com.rh.grh.dto.ReportDTO;
import com.rh.grh.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // ✅ Générer les rapports des employés
    @GetMapping("/employees")
    public ResponseEntity<List<ReportDTO>> generateEmployeeReports() {
        List<ReportDTO> reports = reportService.generateEmployeeReports();
        return ResponseEntity.ok(reports);
    }
}
