package com.mutationmatrix.biomarker_prediction_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PredictionRequestDTO {
    private String sequenceData;
}
