package com.example.marketing.service;
import com.example.marketing.dto.AlertRuleRequestDTO;
import com.example.marketing.dto.AlertRuleResponseDTO;
import com.example.marketing.mapper.AlertRuleMapper;
import com.example.marketing.model.AlertRule;
import com.example.marketing.repository.AlertRuleRepository;
import com.example.marketing.service.AlertRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertRuleServiceImpl implements AlertRuleService {

	private final AlertRuleRepository repository;

	@Override
	public AlertRuleResponseDTO create(AlertRuleRequestDTO request) {
		AlertRule entity = AlertRuleMapper.toEntity(request);
		repository.save(entity);
		return AlertRuleMapper.toResponse(entity);
	}

	@Override
	public AlertRuleResponseDTO update(Integer id, AlertRuleRequestDTO request) {
		AlertRule entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("AlertRule no encontrada"));
		AlertRuleMapper.copyToEntity(request, entity);
		repository.save(entity);
		return AlertRuleMapper.toResponse(entity);
	}

	@Override
	public AlertRuleResponseDTO findById(Integer id) {
		return repository.findById(id)
				.map(AlertRuleMapper::toResponse)
				.orElseThrow(() -> new RuntimeException("AlertRule no encontrada"));
	}

	@Override
	public List<AlertRuleResponseDTO> findAll() {
		return repository.findAll()
				.stream().map(AlertRuleMapper::toResponse).toList();
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
