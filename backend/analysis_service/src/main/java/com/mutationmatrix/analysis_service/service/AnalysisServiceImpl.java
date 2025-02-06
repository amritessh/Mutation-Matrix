package com.mutationmatrix.analysis_service.service;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutationmatrix.analysis_service.model.AnalysisResult;
import com.mutationmatrix.analysis_service.repository.AnalysisRepository;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisRepository analysisRepository;

    @Override
    public String startAnalysis(String sequenceId) {
        AnalysisResult result = new AnalysisResult();
        result.setSequenceId(sequenceId);
        result.setStatus("STARTED");
        analysisRepository.save(result);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("sequenceId", sequenceId)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(analysisJob, jobParameters);
        } catch (Exception e) {
            // Handle exception
        }

        return result.getId().toString();
    }

    @Override
    public AnalysisResult getAnalysisStatus(String analysisId) {
        return analysisRepository.findById(Long.parseLong(analysisId)).orElse(null);
    }
}