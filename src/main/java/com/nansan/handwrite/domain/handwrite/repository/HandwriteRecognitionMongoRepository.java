package com.nansan.handwrite.domain.handwrite.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nansan.handwrite.config.mongodb.HandwriteRecognitionDocument;

@Repository
public interface HandwriteRecognitionMongoRepository
	extends MongoRepository<HandwriteRecognitionDocument, String> {

	List<HandwriteRecognitionDocument> findAllByIsTrainedFalse();

	int countByIsTrainedFalse();
}
