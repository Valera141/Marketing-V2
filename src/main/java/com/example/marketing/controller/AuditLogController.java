package com.example.marketing.controller;

import com.example.marketing.dto.AuditLogRequestDTO;
import com.example.marketing.dto.AuditLogResponseDTO;
import com.example.marketing.service.AuditLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit-logs")
@CrossOrigin(origins = "*")
@Tag(name = "Audit Logs", description = "API for storing system audit logs")
@RequiredArgsConstructor
public class AuditLogController {

	private final AuditLogService auditLogService;

	@Operation(summary = "Create audit log")
	@PostMapping
	public ResponseEntity<AuditLogResponseDTO> create(
			@Valid @RequestBody AuditLogRequestDTO request) {

		return new ResponseEntity<>(auditLogService.create(request), HttpStatus.CREATED);
	}

	@Operation(summary = "List all audit logs")
	@GetMapping
	public ResponseEntity<List<AuditLogResponseDTO>> findAll() {
		return ResponseEntity.ok(auditLogService.findAll());
	}

	@Operation(summary = "Get audit log by ID")
	@GetMapping("/{id}")
	public ResponseEntity<AuditLogResponseDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(auditLogService.findById(id));
	}
}
