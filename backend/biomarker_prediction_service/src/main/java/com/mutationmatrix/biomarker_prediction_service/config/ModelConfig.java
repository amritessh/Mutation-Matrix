package com.mutationmatrix.biomarker_prediction_service.config;

import java.io.InputStream;

import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
// import org.springframework.core.io.Resource;

@Configuration
public class ModelConfig {

    @Bean
    public Evaluator biomarkerModel() throws Exception {
        ClassPathResource resource = new ClassPathResource("ml-model/biomarker_model.pmml");
        InputStream inputStream = resource.getInputStream();
        return new LoadingModelEvaluatorBuilder()
                .load(inputStream)
                .build();
    }
}