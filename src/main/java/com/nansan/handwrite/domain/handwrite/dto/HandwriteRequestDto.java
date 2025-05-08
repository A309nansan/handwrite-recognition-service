package com.nansan.handwrite.domain.handwrite.dto;

import com.nansan.handwrite.model.ink.InkWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandwriteRequestDto {
	private Long userId;
	private int answer;
	private InkWrapper ink;

	private int candidateDigit1;
	private int candidateDigit2;
	private int candidateDigit3;

	private double candidateSimilarity1;
	private double candidateSimilarity2;
	private double candidateSimilarity3;
}
