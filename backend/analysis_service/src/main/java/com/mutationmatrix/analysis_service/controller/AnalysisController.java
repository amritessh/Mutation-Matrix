package com.mutationmatrix.analysis_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @PostMapping("/start")
    public ResponseEntity<String> startAnalysis(@RequestParam String sequenceId) {
        // TODO: process POST request
        String analysisId = analysisService.startAnalysis(sequenceId);
        return ResponseEntity.ok(analysisId);
        // return entity;
    }

    @GetMapping("/status/{analysisId}")
    public ResponseEntity<AnalysisResult> getAnalysisStatus(@PathVariable String analysisId) {
        AnalysisResult result = analysisService.getAnalysisStatus(analysisId);
        return ResponseEntity.ok(result);
    }

}