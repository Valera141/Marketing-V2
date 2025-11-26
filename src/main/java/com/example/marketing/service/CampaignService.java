package com.example.marketing.service;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignService {
    CampaignResponseDTO create(CampaignRequestDTO request);
    CampaignResponseDTO findById(Integer id);
    Page<CampaignResponseDTO> findAll(Pageable pageable);
    CampaignResponseDTO update(Integer id, CampaignRequestDTO request);
    void delete(Integer id);
}