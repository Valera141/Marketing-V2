package com.example.marketing.dto;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationChannelRequestDTO(
    @NotBlank String name,
    @NotBlank String channelType,
    @NotNull(message = "La configuración es obligatoria y debe ser un objeto JSON válido.")
    JsonNode configuration // JSON como String
) {}