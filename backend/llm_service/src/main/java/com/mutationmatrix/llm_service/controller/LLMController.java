package com.mutationmatrix.llm_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutationmatrix.llm_service.dto.QueryRequestDTO;
import com.mutationmatrix.llm_service.dto.QueryResponseDTO;
import com.mutationmatrix.llm_service.service.LLMService;

@RestController
@RequestMapping("/api/llm")
public class LLMController {

    @Autowired
    private LLMService llmService;

    @PostMapping("/query")
    public ResponseEntity<QueryResponseDTO> processQuery(@RequestBody QueryRequestDTO request) {
        QueryResponseDTO response = llmService.processQuery(request);
        return ResponseEntity.ok(response);
    }
}