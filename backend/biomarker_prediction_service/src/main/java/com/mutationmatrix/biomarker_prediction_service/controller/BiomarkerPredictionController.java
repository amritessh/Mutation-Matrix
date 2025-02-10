package com.mutationmatrix.biomarker_prediction_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutationmatrix.biomarker_prediction_service.dto.PredictionRequestDTO;
import com.mutationmatrix.biomarker_prediction_service.dto.PredictionResponseDTO;
import com.mutationmatrix.biomarker_prediction_service.service.BiomarkerPredictionService;

@RestController
@RequestMapping("/api/biomarker")
public class BiomarkerPredictionController {

    @Autowired
    private BiomarkerPredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<PredictionResponseDTO> predictBiomarker(@RequestBody PredictionRequestDTO request) {
        PredictionResponseDTO response = predictionService.predictBiomarker(request);
        return ResponseEntity.ok(response);
    }
}
