package com.mutationmatrix.analysis_service.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private SequenceAlignmentStep sequenceAlignmentStep;

    @Autowired
    private VariantCallingStep variantCallingStep;

    @Autowired
    private BiomarkerPredictionStep bioMarkerPredicitonStep;

    @Bean
    public Job analysisJob() {
        return jobBuilderFactory.get("analysisJob")
                .start(sequenceAlignmentStep.sequenceAlignmentStep())
                .next(variantCallingStep())
                .next(bioMarkerPredicitonStep.bioMarkerPredicitonStep())
                .build();
    }

}
