package com.nansan.handwrite.domain.handwrite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nansan.handwrite.domain.handwrite.HandwriteRecognitionEntity;

@Repository
public interface HandwriteRecognitionRepository extends JpaRepository<HandwriteRecognitionEntity, Long> {
	List<HandwriteRecognitionEntity> findAllByIsTrainedFalse();

	int countByIsTrainedFalse();
}
