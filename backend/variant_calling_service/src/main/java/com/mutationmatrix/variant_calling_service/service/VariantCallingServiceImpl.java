package com.mutationmatrix.variant_calling_service.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutationmatrix.variant_calling_service.dto.SequenceRequestDTO;
import com.mutationmatrix.variant_calling_service.dto.VariantResponseDTO;
import com.mutationmatrix.variant_calling_service.entity.Variant;
import com.mutationmatrix.variant_calling_service.repository.SequenceRepository;
import com.mutationmatrix.variant_calling_service.repository.VariantRepository;

@Service
public class VariantCallingServiceImpl implements VariantCallingService {

    @Autowired
    private SequenceRepository sequenceRepository;

    @Autowired
    private VariantRepository variantRepository;

    // @Override
    // public VariantResponseDTO callVariants(SequenceRequestDTO request) {
    // // Implement variant calling logic here
    // // This is a placeholder implementation
    // VariantResponseDTO response = new VariantResponseDTO();
    // // Add logic to populate response
    // return response;
    // }

    @Override
    public VariantResponseDTO callVariants(SequenceRequestDTO request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'callVariants'");
    }
}