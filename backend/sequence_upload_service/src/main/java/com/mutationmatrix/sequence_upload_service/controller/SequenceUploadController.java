package com.mutationmatrix.sequence_upload_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.mutationmatrix.sequence_upload_service.service.SequenceUploadService;
import com.mutationmatrix.sequence_upload_service.model.SequenceMetaData;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/upload")
public class SequenceUploadController {
    @Autowired
    private SequenceUploadService uploadService;

    @PostMapping("/sequence")
    public Mono<ResponseEntity<String>> uploadSequence(
            @RequestPart("file") Flux<FilePart> file,
            @RequestPart("metadata") Mono<SequenceMetaData> metadata) {
        return uploadService.uploadSequence(file, metadata)
                .map(result -> ResponseEntity.ok("Upload successful: " + result))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body("Upload failed: " + e.getMessage())));
    }

    @GetMapping("/api/work")
    public String getMethodName(@RequestParam String param) {
        return new String("this is working");
    }

}
