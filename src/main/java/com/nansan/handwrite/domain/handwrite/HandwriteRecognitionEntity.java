package com.nansan.handwrite.domain.handwrite;

import com.nansan.handwrite.domain.handwrite.dto.HandwriteRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HandwriteRecognitionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private int answer;

	@Column(columnDefinition = "json", nullable = false)
	private String ink;

	@Column(nullable = false)
	private int candidateDigit1;

	@Column(nullable = false)
	private int candidateDigit2;

	@Column(nullable = false)
	private int candidateDigit3;

	@Column(nullable = false)
	private double candidateSimilarity1;

	@Column(nullable = false)
	private double candidateSimilarity2;

	@Column(nullable = false)
	private double candidateSimilarity3;

	@Column(nullable = false)
	@Setter
	private boolean isTrained = false;

	@Builder
	public HandwriteRecognitionEntity(HandwriteRequestDto dto) {
		this.userId = dto.getUserId();
		this.answer = dto.getAnswer();
		this.ink = dto.getInk();
		this.candidateDigit1 = dto.getCandidateDigit1();
		this.candidateDigit2 = dto.getCandidateDigit2();
		this.candidateDigit3 = dto.getCandidateDigit3();
		this.candidateSimilarity1 = dto.getCandidateSimilarity1();
		this.candidateSimilarity2 = dto.getCandidateSimilarity2();
		this.candidateSimilarity3 = dto.getCandidateSimilarity3();
		this.isTrained = false;
	}
}
