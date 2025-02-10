package com.mutationmatrix.biomarker_prediction_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutationmatrix.biomarker_prediction_service.model.BiomarkerPrediction;

public interface BiomarkerPredictionRepository extends JpaRepository<BiomarkerPrediction, Long> {

}
