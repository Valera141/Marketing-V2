package com.example.marketing.dto;
import jakarta.validation.constraints.NotBlank;

public record NotificationChannelRequestDTO(
    @NotBlank String name,
    @NotBlank String channelType,
    @NotBlank String configuration // JSON como String
) {}