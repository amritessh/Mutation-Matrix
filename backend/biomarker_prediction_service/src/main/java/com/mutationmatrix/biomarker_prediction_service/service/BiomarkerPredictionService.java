package com.mutationmatrix.biomarker_prediction_service.service;

import com.mutationmatrix.biomarker_prediction_service.dto.PredictionRequestDTO;
import com.mutationmatrix.biomarker_prediction_service.dto.PredictionResponseDTO;

public interface BiomarkerPredictionService {
    PredictionResponseDTO predictBiomarker(PredictionRequestDTO request);
}