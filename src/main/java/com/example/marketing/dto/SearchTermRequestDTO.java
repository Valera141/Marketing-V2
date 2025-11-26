package com.example.marketing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SearchTermRequestDTO(
    @NotNull(message = "El ID de la campaña es obligatorio")
    Integer campaignId,

    @NotBlank(message = "El término es obligatorio")
    String term,

    Boolean isActive
) {}