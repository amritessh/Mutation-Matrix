package com.mutationmatrix.llm_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Bean
    public Predictor<String, String> llmPredictor() throws Exception {
        Criteria<String, String> criteria = Criteria.builder()
                .setTypes(String.class, String.class)
                .optEngine("PyTorch")
                .optModelPath("/path/to/your/model")
                .build();

        ZooModel<String, String> model = criteria.loadModel();
        return model.newPredictor();
    }

    @Bean
    public HuggingFaceTokenizer tokenizer() {
        return HuggingFaceTokenizer.newInstance("gpt2");
    }
}
