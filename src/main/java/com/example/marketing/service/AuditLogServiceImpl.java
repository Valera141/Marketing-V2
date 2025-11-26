package com.example.marketing.service;

import com.example.marketing.dto.AuditLogRequestDTO;
import com.example.marketing.dto.AuditLogResponseDTO;
import com.example.marketing.mapper.AuditLogMapper;
import com.example.marketing.model.AuditLog;
import com.example.marketing.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

	private final AuditLogRepository repository;

	@Override
	public AuditLogResponseDTO create(AuditLogRequestDTO request) {
		AuditLog entity = AuditLogMapper.toEntity(request);
		repository.save(entity);
		return AuditLogMapper.toResponse(entity);
	}

	@Override
	public List<AuditLogResponseDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(AuditLogMapper::toResponse)
				.toList();
	}

	@Override
	public AuditLogResponseDTO findById(Integer id) {
		return repository.findById(id)
				.map(AuditLogMapper::toResponse)
				.orElseThrow(() -> new RuntimeException("Audit log no encontrado"));
	}
}
