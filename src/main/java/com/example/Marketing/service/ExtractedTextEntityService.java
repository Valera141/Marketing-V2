package com.example.Marketing.service;

import com.example.Marketing.dto.ExtractedTextEntityRequestDTO;
import com.example.Marketing.dto.ExtractedTextEntityResponseDTO;
import java.util.List;

public interface ExtractedTextEntityService {

    // --- Métodos CRUD Básicos ---
    
    ExtractedTextEntityResponseDTO create(ExtractedTextEntityRequestDTO request);

    ExtractedTextEntityResponseDTO findById(Integer entityId);

    List<ExtractedTextEntityResponseDTO> findAll();

    ExtractedTextEntityResponseDTO update(Integer entityId, ExtractedTextEntityRequestDTO request);

    void delete(Integer entityId);

    // --- Métodos de Búsqueda Específica ---
    
    List<ExtractedTextEntityResponseDTO> findEntitiesByAnalysisId(Integer textAnalysisId);
    
    List<ExtractedTextEntityResponseDTO> findByEntityType(String entityType);
}