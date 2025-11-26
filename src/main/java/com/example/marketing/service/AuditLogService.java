package com.example.marketing.service;

import java.util.List;
import com.example.marketing.dto.AuditLogRequestDTO;
import com.example.marketing.dto.AuditLogResponseDTO;

public interface AuditLogService {

	AuditLogResponseDTO create(AuditLogRequestDTO request);

	List<AuditLogResponseDTO> findAll();

	AuditLogResponseDTO findById(Integer id);
}
