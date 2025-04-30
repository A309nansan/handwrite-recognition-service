package com.nansan.handwrite.domain.handwrite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nansan.handwrite.domain.handwrite.dto.HandwriteRequestDto;
import com.nansan.handwrite.domain.handwrite.dto.HandwriteResponseDto;
import com.nansan.handwrite.domain.handwrite.service.HandwriteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HandwriteController {
	private HandwriteService handwriteService;

	@PostMapping
	public ResponseEntity<Void> saveHandwrite(HandwriteRequestDto handwriteRequestDto) {
		handwriteService.save(handwriteRequestDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<HandwriteResponseDto> geAllHandwrites() {
		return ResponseEntity.ok(handwriteService.findAllHandwrites());
	}

	@GetMapping("/untrained")
	public ResponseEntity<HandwriteResponseDto> getUntrainedHandwrites() {
		return ResponseEntity.ok(handwriteService.findAllUntrainedHandwrites());
	}
}
