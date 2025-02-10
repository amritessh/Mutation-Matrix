package com.mutationmatrix.variant_calling_service.service;

import com.mutationmatrix.variant_calling_service.dto.SequenceRequestDTO;
import com.mutationmatrix.variant_calling_service.dto.VariantResponseDTO;

public interface VariantCallingService {
    VariantResponseDTO callVariants(SequenceRequestDTO request);
}