package com.mutationmatrix.variant_calling_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutationmatrix.variant_calling_service.entity.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long> {

}
