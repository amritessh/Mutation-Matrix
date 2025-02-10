package com.mutationmatrix.reporting_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutationmatrix.reporting_service.dto.ReportRequestDTO;
import com.mutationmatrix.reporting_service.dto.ReportResponseDTO;
import com.mutationmatrix.reporting_service.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/generate")
    public ResponseEntity<ReportResponseDTO> generateReport(@RequestBody ReportRequestDTO request) {
        ReportResponseDTO response = reportService.generateReport(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> getReport(@PathVariable Long id) {
        ReportResponseDTO response = reportService.getReport(id);
        return ResponseEntity.ok(response);
    }
}
