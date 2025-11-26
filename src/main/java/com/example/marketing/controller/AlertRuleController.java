package com.example.marketing.controller;

import com.example.marketing.dto.AlertRuleRequestDTO;
import com.example.marketing.dto.AlertRuleResponseDTO;
import com.example.marketing.service.AlertRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alert-rules")
@CrossOrigin(origins = "*")
@Tag(name = "Alert Rules", description = "API for managing rules that generate alerts")
@RequiredArgsConstructor
public class AlertRuleController {

	private final AlertRuleService alertRuleService;

	@Operation(summary = "Create an alert rule")
	@PostMapping
	public ResponseEntity<AlertRuleResponseDTO> create(@Valid @RequestBody AlertRuleRequestDTO request) {
		return new ResponseEntity<>(alertRuleService.create(request), HttpStatus.CREATED);
	}

	@Operation(summary = "Get alert rule by ID")
	@GetMapping("/{id}")
	public ResponseEntity<AlertRuleResponseDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(alertRuleService.findById(id));
	}

	@Operation(summary = "List all alert rules")
	@GetMapping
	public ResponseEntity<List<AlertRuleResponseDTO>> findAll() {
		return ResponseEntity.ok(alertRuleService.findAll());
	}

	@Operation(summary = "Update an alert rule")
	@PutMapping("/{id}")
	public ResponseEntity<AlertRuleResponseDTO> update(
			@PathVariable Integer id,
			@Valid @RequestBody AlertRuleRequestDTO request) {

		return ResponseEntity.ok(alertRuleService.update(id, request));
	}

	@Operation(summary = "Delete an alert rule")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		alertRuleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
