package com.mutationmatrix.analysis_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutationmatrix.analysis_service.model.AnalysisResult;

public interface AnalysisRepository extends JpaRepository<AnalysisResult, Long> {
    AnalysisResult findSequenceId(String sequenceId);
}