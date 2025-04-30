package com.nansan.handwrite.domain.handwrite.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.nansan.handwrite.domain.handwrite.HandwriteRecognitionEntity;

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
		private String ink;
	}

	public static HandwriteResponseDto fromEntities(List<HandwriteRecognitionEntity> entities) {
		List<HandwriteRecord> records = entities.stream()
			.map(entity -> new HandwriteRecord(
				entity.getUserId(),
				entity.getAnswer(),
				entity.getInk()
			))
			.collect(Collectors.toList());

		return new HandwriteResponseDto(records);
	}
}
