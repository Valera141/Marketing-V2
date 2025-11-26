package com.example.marketing.mapper;

import com.example.marketing.dto.AuditLogRequestDTO;
import com.example.marketing.dto.AuditLogResponseDTO;
import com.example.marketing.model.AuditLog;
import com.example.marketing.model.User;

import java.time.OffsetDateTime;

public class AuditLogMapper {

	// ------------------------------------
	// Entidad → DTO
	// ------------------------------------
	public static AuditLogResponseDTO toResponse(AuditLog entity) {
		if (entity == null) return null;

		return AuditLogResponseDTO.builder()
				.logId(entity.getLogId())
				.userId(entity.getUser() != null ? entity.getUser().getUserId() : null)
				.action(entity.getAction())
				.detailsJson(entity.getDetails())
				.logDate(entity.getLogDate())
				.build();
	}


	// ------------------------------------
	// DTO → Entidad (crear)
	// ------------------------------------
	public static AuditLog toEntity(AuditLogRequestDTO dto) {
		if (dto == null) return null;

		AuditLog entity = new AuditLog();

		entity.setAction(dto.action());
		entity.setDetails(dto.detailsJson());
		entity.setLogDate(OffsetDateTime.now()); // Se registra automáticamente

		// Asignar usuario si viene el ID
		if (dto.userId() != null) {
			User u = new User();
			u.setUserId(dto.userId());
			entity.setUser(u);
		}

		return entity;
	}


	// ------------------------------------
	// DTO → Entidad (actualizar)
	// ------------------------------------
	public static void copyToEntity(AuditLogRequestDTO dto, AuditLog existing) {
		if (dto == null || existing == null) return;

		existing.setAction(dto.action());
		existing.setDetails(dto.detailsJson());

		// Mantener fecha original o actualizar, según tu lógica
		// existing.setLogDate(OffsetDateTime.now());

		if (dto.userId() != null) {
			User u = new User();
			u.setUserId(dto.userId());
			existing.setUser(u);
		} else {
			existing.setUser(null);
		}
	}
}
