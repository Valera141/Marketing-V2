package com.example.marketing.service;


import com.example.marketing.dto.GeneratedAlertRequestDTO;
import com.example.marketing.dto.GeneratedAlertResponseDTO;
import com.example.marketing.mapper.GeneratedAlertMapper;
import com.example.marketing.model.GeneratedAlert;
import com.example.marketing.repository.GeneratedAlertRepository;
import com.example.marketing.service.GeneratedAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneratedAlertServiceImpl implements GeneratedAlertService {

	private final GeneratedAlertRepository repository;

	@Override
	public GeneratedAlertResponseDTO create(GeneratedAlertRequestDTO request) {
		GeneratedAlert entity = GeneratedAlertMapper.toEntity(request);
		repository.save(entity);
		return GeneratedAlertMapper.toResponse(entity);
	}

	@Override
	public List<GeneratedAlertResponseDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(GeneratedAlertMapper::toResponse).toList();
	}

	@Override
	public GeneratedAlertResponseDTO findById(Integer id) {
		return repository.findById(id)
				.map(GeneratedAlertMapper::toResponse)
				.orElseThrow(() -> new RuntimeException("Generated Alert no encontrado"));
	}
}
