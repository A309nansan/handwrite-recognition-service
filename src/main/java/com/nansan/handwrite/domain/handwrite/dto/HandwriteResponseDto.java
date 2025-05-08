package com.nansan.handwrite.domain.handwrite.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.nansan.handwrite.config.mongodb.HandwriteRecognitionDocument;
import com.nansan.handwrite.model.ink.InkWrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandwriteResponseDto {

	private List<HandwriteRecord> handwriteRecords;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class HandwriteRecord {
		private Long userId;
		private int answer;
		private InkWrapper ink;
	}

	public static HandwriteResponseDto fromDocuments(List<HandwriteRecognitionDocument> documents) {
		List<HandwriteRecord> records = documents.stream()
			.map(doc -> new HandwriteRecord(doc.getUserId(), doc.getAnswer(), doc.getInk()))
			.collect(Collectors.toList());

		return new HandwriteResponseDto(records);
	}
}

