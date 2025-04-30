package com.nansan.handwrite.domain.handwrite.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nansan.handwrite.config.RabbitMqPublisher;
import com.nansan.handwrite.domain.handwrite.HandwriteRecognitionEntity;
import com.nansan.handwrite.domain.handwrite.dto.HandwriteRequestDto;
import com.nansan.handwrite.domain.handwrite.dto.HandwriteResponseDto;
import com.nansan.handwrite.domain.handwrite.repository.HandwriteRecognitionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandwriteService {
	private final static int TRAIN_AMOUNT = 10;
	private HandwriteRecognitionRepository handwriteRepository;
	private RabbitMqPublisher rabbitMqTemplate;

	@Transactional
	public void save(HandwriteRequestDto handwriteRequestDto) {
		try {
			HandwriteRecognitionEntity handwriteRecognition = HandwriteRecognitionEntity.builder()
				.dto(handwriteRequestDto)
				.build();
			handwriteRepository.save(handwriteRecognition);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Transactional
	public HandwriteResponseDto findAllHandwrites() {
		return HandwriteResponseDto.fromEntities(handwriteRepository.findAll());
	}

	@Transactional
	public HandwriteResponseDto findAllUntrainedHandwrites() {
		List<HandwriteRecognitionEntity> handwrites = handwriteRepository.findAllByIsTrainedFalse();

		for (HandwriteRecognitionEntity handwrite : handwrites) {
			handwrite.setTrained(true);
		}
		return HandwriteResponseDto.fromEntities(handwrites);
	}

	@Transactional
	public int getUnTrainedHandwriteCount() {
		return handwriteRepository.countByIsTrainedFalse();
	}

	@Transactional
	@Scheduled(cron = "0 */30 * * * *")
	public void publishBatchEvent() {
		int untrainedCount = handwriteRepository.countByIsTrainedFalse();
		if (untrainedCount > TRAIN_AMOUNT) {
			rabbitMqTemplate.sendToPersonal("work!");
		}
	}
}
