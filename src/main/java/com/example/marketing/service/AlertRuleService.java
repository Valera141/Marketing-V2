package com.example.marketing.service;

import java.util.List;
import com.example.marketing.dto.AlertRuleRequestDTO;
import com.example.marketing.dto.AlertRuleResponseDTO;

public interface AlertRuleService {

	AlertRuleResponseDTO create(AlertRuleRequestDTO request);

	AlertRuleResponseDTO update(Integer id, AlertRuleRequestDTO request);

	AlertRuleResponseDTO findById(Integer id);

	List<AlertRuleResponseDTO> findAll();

	void delete(Integer id);
}
