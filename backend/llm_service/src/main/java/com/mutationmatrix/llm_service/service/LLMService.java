package com.mutationmatrix.llm_service.service;

import com.mutationmatrix.llm_service.dto.QueryRequestDTO;
import com.mutationmatrix.llm_service.dto.QueryResponseDTO;

public interface LLMService {
    QueryResponseDTO processQuery(QueryRequestDTO request);
}