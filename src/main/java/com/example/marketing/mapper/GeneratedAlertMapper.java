package com.example.marketing.mapper;

import com.example.marketing.dto.GeneratedAlertRequestDTO;
import com.example.marketing.dto.GeneratedAlertResponseDTO;
import com.example.marketing.model.AlertRule;
import com.example.marketing.model.GeneratedAlert;
import com.example.marketing.model.Publication;

import java.time.OffsetDateTime;

public class GeneratedAlertMapper {

	// -------------------------------------------------
	// ENTITY → RESPONSE DTO
	// -------------------------------------------------
	public static GeneratedAlertResponseDTO toResponse(GeneratedAlert entity) {
		if (entity == null) return null;

		return GeneratedAlertResponseDTO.builder()
				.alertId(entity.getAlertId())
				.ruleId(entity.getAlertRule() != null ? entity.getAlertRule().getRuleId() : null)
				.publicationApiId(entity.getPublication() != null ? entity.getPublication().getPublicationApiId() : null)
				.status(entity.getStatus())
				.generationDate(entity.getGenerationDate())
				.build();
	}


	// -------------------------------------------------
	// REQUEST DTO → ENTITY (CREATE)
	// -------------------------------------------------
	public static GeneratedAlert toEntity(GeneratedAlertRequestDTO dto) {
		if (dto == null) return null;

		GeneratedAlert entity = new GeneratedAlert();

		entity.setGenerationDate(OffsetDateTime.now());
		entity.setStatus(dto.status());

		// Set rule by ID only
		AlertRule rule = new AlertRule();
		rule.setRuleId(dto.ruleId());
		entity.setAlertRule(rule);

		// Set publication by ID only
		Publication pub = new Publication();
		pub.setPublicationApiId(dto.publicationApiId());
		entity.setPublication(pub);

		return entity;
	}


	// -------------------------------------------------
	// REQUEST DTO → ENTITY (UPDATE)
	// -------------------------------------------------
	public static void copyToEntity(GeneratedAlertRequestDTO dto, GeneratedAlert existing) {
		if (dto == null || existing == null) return;

		existing.setStatus(dto.status());
		// OJO: La fecha original se mantiene (no se regenera)
		// existing.setGenerationDate(OffsetDateTime.now()); // Solo si lo deseas

		// Update rule
		if (dto.ruleId() != null) {
			AlertRule rule = new AlertRule();
			rule.setRuleId(dto.ruleId());
			existing.setAlertRule(rule);
		}

		// Update publication
		if (dto.publicationApiId() != null) {
			Publication pub = new Publication();
			pub.setPublicationApiId(dto.publicationApiId());
			existing.setPublication(pub);
		}
	}
}
