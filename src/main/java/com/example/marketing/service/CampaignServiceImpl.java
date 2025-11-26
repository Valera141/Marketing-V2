package com.example.marketing.service;

import com.example.marketing.dto.CampaignRequestDTO;
import com.example.marketing.dto.CampaignResponseDTO;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.User;
import com.example.marketing.repository.CampaignRepository;
import com.example.marketing.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;

    @Override
    public CampaignResponseDTO create(CampaignRequestDTO request) {
        User creator = userRepository.findById(request.creatorUserId())
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Campaign campaign = Campaign.builder()
            .campaignName(request.campaignName())
            .isActive(request.isActive() != null ? request.isActive() : true)
            .creationDate(OffsetDateTime.now())
            .creatorUser(creator)
            .build();

        return toResponse(campaignRepository.save(campaign));
    }

    @Override
    @Transactional(readOnly = true)
    public CampaignResponseDTO findById(Integer id) {
        return campaignRepository.findById(id)
            .map(this::toResponse)
            .orElseThrow(() -> new EntityNotFoundException("Campaña no encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CampaignResponseDTO> findAll(Pageable pageable) {
        return campaignRepository.findAll(pageable).map(this::toResponse);
    }

    @Override
    public CampaignResponseDTO update(Integer id, CampaignRequestDTO request) {
        Campaign campaign = campaignRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Campaña no encontrada"));
        
        campaign.setCampaignName(request.campaignName());
        if(request.isActive() != null) campaign.setIsActive(request.isActive());
        
        return toResponse(campaignRepository.save(campaign));
    }

    @Override
    public void delete(Integer id) {
        if (!campaignRepository.existsById(id)) {
            throw new EntityNotFoundException("Campaña no encontrada");
        }
        campaignRepository.deleteById(id);
    }

    // Mapper manual simple
    private CampaignResponseDTO toResponse(Campaign c) {
        return CampaignResponseDTO.builder()
            .campaignId(c.getCampaignId())
            .campaignName(c.getCampaignName())
            .isActive(c.getIsActive())
            .creationDate(c.getCreationDate())
            .creatorUserName(c.getCreatorUser() != null ? c.getCreatorUser().getFullName() : "Desconocido")
            .build();
    }
}