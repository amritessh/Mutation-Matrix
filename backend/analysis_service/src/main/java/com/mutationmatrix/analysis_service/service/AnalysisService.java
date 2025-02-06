package com.mutationmatrix.analysis_service.service;

import com.mutationmatrix.analysis_service.model.AnalysisResult;

public interface AnalysisService {
    String startAnalysis(String sequenceId);

    AnalysisResult getAnalysisStatus(String analysisId);
}