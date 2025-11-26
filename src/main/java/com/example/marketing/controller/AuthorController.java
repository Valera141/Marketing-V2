package com.example.marketing.controller;

import com.example.marketing.dto.AuthorRequestDTO;
import com.example.marketing.dto.AuthorResponseDTO;
import com.example.marketing.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@CrossOrigin(origins = "*")
@Tag(name = "Authors", description = "API for managing authors")
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorService authorService;

	@Operation(summary = "Create an author")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Author created")
	})
	@PostMapping
	public ResponseEntity<AuthorResponseDTO> create(@Valid @RequestBody AuthorRequestDTO request) {
		return new ResponseEntity<>(authorService.create(request), HttpStatus.CREATED);
	}

	@Operation(summary = "Get an author by ID")
	@GetMapping("/{id}")
	public ResponseEntity<AuthorResponseDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(authorService.findById(id));
	}

	@Operation(summary = "Get authors with pagination")
	@GetMapping
	public ResponseEntity<Page<AuthorResponseDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(authorService.findAll(pageable));
	}


	@Operation(summary = "Update an author")
	@PutMapping("/{id}")
	public ResponseEntity<AuthorResponseDTO> update(
			@PathVariable Integer id,
			@Valid @RequestBody AuthorRequestDTO request) {

		return ResponseEntity.ok(authorService.update(id, request));
	}

	@Operation(summary = "Delete an author")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		authorService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

