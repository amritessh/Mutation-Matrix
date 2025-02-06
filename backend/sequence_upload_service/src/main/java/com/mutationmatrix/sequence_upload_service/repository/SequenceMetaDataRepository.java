package com.mutationmatrix.sequence_upload_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutationmatrix.sequence_upload_service.model.SequenceMetaData;

@Repository
public interface SequenceMetaDataRepository extends JpaRepository<SequenceMetaData, Long> {
    Optional<SequenceMetaData> findBySequenceFileId(String sequenceFileId);

}