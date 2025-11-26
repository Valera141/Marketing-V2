package com.example.marketing.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DashboardViewRequestDTO(
    @NotNull Integer userId,
    @NotBlank String viewName,
    @NotBlank String configurationJson
) {}