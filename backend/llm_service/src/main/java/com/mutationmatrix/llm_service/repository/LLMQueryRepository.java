package com.mutationmatrix.llm_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutationmatrix.llm_service.model.LLMQuery;

public interface LLMQueryRepository extends JpaRepository<LLMQuery, Long> {

}
