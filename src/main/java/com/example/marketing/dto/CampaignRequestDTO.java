package com.example.marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CampaignRequestDTO(
    @NotBlank(message = "El nombre de la campaña es obligatorio")
    String campaignName,

    Boolean isActive,

    @NotNull(message = "El ID del usuario creador es obligatorio")
    Integer creatorUserId
) {}