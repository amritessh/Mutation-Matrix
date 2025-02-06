package com.mutationmatrix.analysis_service.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.mutationmatrix.analysis_service.service.AnalysisService;

@Component
public class SequenceUploadedEventListener {

    @Autowired
    private AnalysisService analysisService;

    @KafkaListener(topics = "sequence-uploaded", groupId = "analysis-service")
    public void handleSequenceUploadedEvent(String sequenceId) {
        analysisService.startAnalysis(sequenceId);
    }
}