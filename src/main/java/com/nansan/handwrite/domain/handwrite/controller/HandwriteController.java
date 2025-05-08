package com.nansan.handwrite.domain.handwrite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nansan.handwrite.domain.handwrite.dto.HandwriteRequestDto;
import com.nansan.handwrite.domain.handwrite.dto.HandwriteResponseDto;
import com.nansan.handwrite.domain.handwrite.service.HandwriteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HandwriteController {
	private final HandwriteService handwriteService;

	@PostMapping("/")
	public ResponseEntity<Void> saveHandwrite(@RequestBody HandwriteRequestDto handwriteRequestDto) {
		log.info("Handwrite request: {}", handwriteRequestDto);
		handwriteService.save(handwriteRequestDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/all")
	public ResponseEntity<HandwriteResponseDto> geAllHandwrites() {
		log.info("GET all handwrites");
		return ResponseEntity.ok(handwriteService.findAllHandwrites());
	}

	@GetMapping("/untrained")
	public ResponseEntity<HandwriteResponseDto> getUntrainedHandwrites() {
		return ResponseEntity.ok(handwriteService.findAllUntrainedHandwrites());
	}
}
