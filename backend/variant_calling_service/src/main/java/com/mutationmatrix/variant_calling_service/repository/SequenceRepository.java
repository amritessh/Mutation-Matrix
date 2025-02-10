package com.mutationmatrix.variant_calling_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutationmatrix.variant_calling_service.entity.Sequence;

public interface SequenceRepository extends JpaRepository<Sequence, Long> {

}
