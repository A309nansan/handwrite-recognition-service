package com.nansan.handwrite.domain.handwrite.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.nansan.handwrite.config.mongodb.HandwriteRecognitionDocument;
import com.nansan.handwrite.config.rabbitmq.RabbitMqPublisher;
import com.nansan.handwrite.domain.handwrite.dto.HandwriteRequestDto;
import com.nansan.handwrite.domain.handwrite.dto.HandwriteResponseDto;
import com.nansan.handwrite.domain.handwrite.repository.HandwriteRecognitionMongoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HandwriteService {

	private final static int TRAIN_AMOUNT = 1;
	private final HandwriteRecognitionMongoRepository handwriteRepository;
	private final RabbitMqPublisher rabbitMqTemplate;

	public void save(HandwriteRequestDto dto) {
		try {
			HandwriteRecognitionDocument document = HandwriteRecognitionDocument.fromDto(dto);
			handwriteRepository.save(document);
		} catch (Exception e) {
			log.error("Failed to save handwrite: {}", e.getMessage());
		}
	}

	public HandwriteResponseDto findAllHandwrites() {
		return HandwriteResponseDto.fromDocuments(handwriteRepository.findAll());
	}

	public HandwriteResponseDto findAllUntrainedHandwrites() {
		List<HandwriteRecognitionDocument> untrained = handwriteRepository.findAllByIsTrainedFalse();
		for (HandwriteRecognitionDocument doc : untrained) {
			doc.setTrained(true);
		}
		handwriteRepository.saveAll(untrained);
		return HandwriteResponseDto.fromDocuments(untrained);
	}

	public int getUnTrainedHandwriteCount() {
		return handwriteRepository.countByIsTrainedFalse();
	}

	@Scheduled(cron = "0 */1 * * * *")
	public void publishBatchEvent() {
		log.info("⏰ batch event");
		int untrainedCount = getUnTrainedHandwriteCount();
		if (untrainedCount >= TRAIN_AMOUNT) {
			rabbitMqTemplate.sendToPersonal("handwrite training");
			log.info("✅ handwrite training finished");
		}
	}
}