package com.example.marketing.controller;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.service.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
@Tag(name = "Campaigns", description = "API for managing Marketing Campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @PostMapping
    @Operation(summary = "Create a new campaign")
    public ResponseEntity<CampaignResponseDTO> create(@Valid @RequestBody CampaignRequestDTO request) {
        return new ResponseEntity<>(campaignService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List all campaigns")
    public ResponseEntity<Page<CampaignResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(campaignService.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get campaign by ID")
    public ResponseEntity<CampaignResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(campaignService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a campaign")
    public ResponseEntity<CampaignResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody CampaignRequestDTO request) {
        return ResponseEntity.ok(campaignService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a campaign")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        campaignService.delete(id);
        return ResponseEntity.noContent().build();
    }
}