package com.example.marketing.mapper;

import com.example.marketing.dto.SearchTermRequestDTO;
import com.example.marketing.dto.SearchTermResponseDTO;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.SearchTerm;

public class SearchTermMapper {

    public static SearchTerm toEntity(SearchTermRequestDTO dto, Campaign campaign) {
        return SearchTerm.builder()
                .term(dto.term())
                .isActive(dto.isActive() != null ? dto.isActive() : true)
                .campaign(campaign)
                .build();
    }

    public static SearchTermResponseDTO toResponse(SearchTerm entity) {
        return SearchTermResponseDTO.builder()
                .termId(entity.getTermId())
                .term(entity.getTerm())
                .isActive(entity.getIsActive())
                .campaignId(entity.getCampaign().getCampaignId())
                .build();
    }
}