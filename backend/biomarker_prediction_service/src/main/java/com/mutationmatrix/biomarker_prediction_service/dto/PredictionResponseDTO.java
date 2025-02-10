package com.mutationmatrix.biomarker_prediction_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionResponseDTO {
    private boolean isBiomarkerPresent;
    private double confidence;
}
