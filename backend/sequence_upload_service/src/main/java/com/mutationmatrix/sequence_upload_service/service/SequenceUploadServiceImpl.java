package com.mutationmatrix.sequence_upload_service.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import com.mutationmatrix.sequence_upload_service.model.SequenceMetaData;
import com.mutationmatrix.sequence_upload_service.repository.SequenceMetaDataRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class SequenceUploadServiceImpl implements SequenceUploadService {

    private final SequenceMetaDataRepository metadataRepository;
    private final Path fileStoragePath;

    // @Autowired
    public SequenceUploadServiceImpl(
            SequenceMetaDataRepository metadataRepository,
            @Value("${app.file-storage-path:uploads}") String fileStoragePath) {
        this.metadataRepository = metadataRepository;
        this.fileStoragePath = Paths.get(fileStoragePath);
        createStorageDirectory();
    }

    private void createStorageDirectory() {
        try {
            Files.createDirectories(fileStoragePath);
        } catch (Exception e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    @Override
    public Mono<String> uploadSequence(Flux<FilePart> file, Mono<SequenceMetaData> metadata) {
        return file
                .next()
                .flatMap(filePart -> {
                    // Generate a unique filename to prevent collisions
                    String originalFilename = filePart.filename();
                    String uniqueFilename = generateUniqueFilename(originalFilename);
                    Path destinationPath = fileStoragePath.resolve(uniqueFilename);

                    // Store the file
                    return storeFile(filePart, destinationPath)
                            .thenReturn(uniqueFilename);
                })
                .zipWith(metadata)
                .flatMap(tuple -> {
                    String filename = tuple.getT1();
                    SequenceMetaData meta = tuple.getT2();
                    meta.setSequenceFileId(filename);

                    // Save metadata using JPA repository
                    return Mono.fromCallable(() -> metadataRepository.save(meta))
                            .subscribeOn(Schedulers.boundedElastic())
                            .map(savedMeta -> savedMeta.getId().toString());
                })
                .onErrorResume(e -> {
                    // Log the error and return a Mono.error
                    return Mono.error(new RuntimeException("Failed to process upload: " + e.getMessage(), e));
                });
    }

    private String generateUniqueFilename(String originalFilename) {
        String extension = "";
        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            extension = originalFilename.substring(lastDotIndex);
            originalFilename = originalFilename.substring(0, lastDotIndex);
        }
        return originalFilename + "_" + UUID.randomUUID().toString() + extension;
    }

    private Mono<Void> storeFile(FilePart filePart, Path destinationPath) {
        return Mono.fromCallable(() -> {
            // Ensure parent directories exist
            Files.createDirectories(destinationPath.getParent());

            // Create the destination file
            File destinationFile = destinationPath.toFile();

            // Transfer the file content
            return filePart.transferTo(destinationFile)
                    .then();
        })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(mono -> mono);
    }

    public Mono<byte[]> retrieveFile(String filename) {
        return Mono.fromCallable(() -> {
            Path filePath = fileStoragePath.resolve(filename);
            return Files.readAllBytes(filePath);
        })
                .subscribeOn(Schedulers.boundedElastic());
    }
}