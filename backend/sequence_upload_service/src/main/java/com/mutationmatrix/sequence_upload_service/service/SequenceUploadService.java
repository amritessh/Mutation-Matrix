package com.mutationmatrix.sequence_upload_service.service;

import org.springframework.http.codec.multipart.FilePart;

import com.mutationmatrix.sequence_upload_service.model.SequenceMetaData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SequenceUploadService {

    Mono<String> uploadSequence(Flux<FilePart> file, Mono<SequenceMetaData> metadata);
}