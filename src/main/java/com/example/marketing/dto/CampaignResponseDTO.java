package com.example.marketing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignResponseDTO {
    
    @JsonProperty("campaign_id")
    private Integer campaignId;

    @JsonProperty("campaign_name")
    private String campaignName;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("creation_date")
    private OffsetDateTime creationDate;

    @JsonProperty("creator_user")
    private String creatorUserName;
}