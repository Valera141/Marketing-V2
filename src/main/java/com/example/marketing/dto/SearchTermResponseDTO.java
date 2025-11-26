package com.example.marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchTermResponseDTO {
    @JsonProperty("term_id")
    private Integer termId;
    private String term;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("campaign_id")
    private Integer campaignId;
}