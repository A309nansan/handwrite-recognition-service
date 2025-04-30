package com.nansan.handwrite.domain.handwrite.dto;

import lombok.Data;

@Data
public class HandwriteRequestDto {
	private Long userId;
	private int answer;
	private String ink;

	private int candidateDigit1;
	private int candidateDigit2;
	private int candidateDigit3;

	private double candidateSimilarity1;
	private double candidateSimilarity2;
	private double candidateSimilarity3;

}
