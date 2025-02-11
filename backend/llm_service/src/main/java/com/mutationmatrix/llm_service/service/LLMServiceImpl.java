package com.mutationmatrix.llm_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutationmatrix.llm_service.dto.QueryRequestDTO;
import com.mutationmatrix.llm_service.dto.QueryResponseDTO;
import com.mutationmatrix.llm_service.model.LLMQuery;
import com.mutationmatrix.llm_service.repository.LLMQueryRepository;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.djl.inference.Predictor;

@Service
public class LLMServiceImpl implements LLMService {
            
    @Autowired
    private LLMQueryRepository llmQueryRepository;

    @Autowired
    private Predictor<String, String> llmPredictor;

    @Autowired
    private HuggingFaceTokenizer tokenizer;

    @Override
    public QueryResponseDTO processQuery(QueryRequestDTO request) {
        try {
            String query = request.getQuery();
            String response = llmPredictor.predict(query);

            LLMQuery llmQuery = new LLMQuery();
            llmQuery.setQuery(query);
            llmQuery.setResponse(response);
            llmQuery.setTimestamp(LocalDateTime.now());
            llmQueryRepository.save(llmQuery);

            QueryResponseDTO responseDTO = new QueryResponseDTO();
            responseDTO.setResponse(response);
            return responseDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error processing LLM query", e);
        }
    }
}
