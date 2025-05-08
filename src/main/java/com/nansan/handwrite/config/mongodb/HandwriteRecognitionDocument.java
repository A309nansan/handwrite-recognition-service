package com.nansan.handwrite.config.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nansan.handwrite.domain.handwrite.dto.HandwriteRequestDto;
import com.nansan.handwrite.model.ink.InkWrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "handwrites")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HandwriteRecognitionDocument {

	@Id
	private String id;

	private Long userId;
	private int answer;
	private InkWrapper ink;

	private int candidateDigit1;
	private int candidateDigit2;
	private int candidateDigit3;

	private double candidateSimilarity1;
	private double candidateSimilarity2;
	private double candidateSimilarity3;

	@Setter
	private boolean isTrained;

	public static HandwriteRecognitionDocument fromDto(HandwriteRequestDto dto) {
		return HandwriteRecognitionDocument.builder()
			.userId(dto.getUserId())
			.answer(dto.getAnswer())
			.ink(dto.getInk()) // 구조화된 InkWrapper 사용
			.candidateDigit1(dto.getCandidateDigit1())
			.candidateDigit2(dto.getCandidateDigit2())
			.candidateDigit3(dto.getCandidateDigit3())
			.candidateSimilarity1(dto.getCandidateSimilarity1())
			.candidateSimilarity2(dto.getCandidateSimilarity2())
			.candidateSimilarity3(dto.getCandidateSimilarity3())
			.isTrained(false)
			.build();
	}
}
