package com.mutationmatrix.variant_calling_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutationmatrix.variant_calling_service.dto.SequenceRequestDTO;
import com.mutationmatrix.variant_calling_service.dto.VariantResponseDTO;
import com.mutationmatrix.variant_calling_service.service.VariantCallingService;

@RestController
@RequestMapping("/api/variants")
public class VariantCallingController {

    @Autowired
    private VariantCallingService variantCallingService;

    @PostMapping("/call")
    public VariantResponseDTO callVariants(@RequestBody SequenceRequestDTO request) {
        return variantCallingService.callVariants(request);
    }
}