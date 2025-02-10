package com.mutationmatrix.variant_calling_service.grpc;

import org.springframework.beans.factory.annotation.Autowired;

import com.mutationmatrix.variant_calling_service.service.VariantCallingService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class VariantCallingGrpcService extends VariantCallingServiceGrpc.VariantCallingServiceImplBase {

    @Autowired
    private VariantCallingService variantCallingService;

    @Override
    public void callVariants(VariantCallingRequest request, StreamObserver<VariantCallingResponse> responseObserver) {
        // Implement gRPC variant calling logic here
        // This is a placeholder implementation
        VariantCallingResponse response = VariantCallingResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
