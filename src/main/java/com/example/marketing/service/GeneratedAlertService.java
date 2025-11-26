package com.example.marketing.service;

import java.util.List;
import com.example.marketing.dto.GeneratedAlertRequestDTO;
import com.example.marketing.dto.GeneratedAlertResponseDTO;

public interface GeneratedAlertService {

	GeneratedAlertResponseDTO create(GeneratedAlertRequestDTO request);

	List<GeneratedAlertResponseDTO> findAll();

	GeneratedAlertResponseDTO findById(Integer id);
}
