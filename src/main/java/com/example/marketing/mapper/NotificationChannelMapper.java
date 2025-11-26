package com.example.marketing.mapper;

import com.example.marketing.dto.NotificationChannelRequestDTO;
import com.example.marketing.dto.NotificationChannelResponseDTO;
import com.example.marketing.model.NotificationChannel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;

public class NotificationChannelMapper {

    private NotificationChannelMapper() {
        // Clase estática de utilidad
    }

    public static NotificationChannel toEntity(NotificationChannelRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        
        try {
            jsonString = objectMapper.writeValueAsString(dto.configuration());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error al serializar el campo 'configuration' a String JSON.", e);
        }

        return NotificationChannel.builder()
                .name(dto.name())
                .channelType(dto.channelType())
                .configuration(jsonString) // 👈 ASIGNACIÓN CORREGIDA: String ahora es compatible con la Entidad
                .build();
    }

    public static NotificationChannelResponseDTO toResponseDTO(NotificationChannel entity) {
        if (entity == null) {
            return null;
        }

        return NotificationChannelResponseDTO.builder()
                .channelId(entity.getChannelId())
                .name(entity.getName())
                .channelType(entity.getChannelType())
                .configuration(entity.getConfiguration())
                .build();
    }
    
    public static void copyToEntity(NotificationChannelRequestDTO dto, NotificationChannel entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        entity.setName(dto.name());
        entity.setChannelType(dto.channelType());
        
        try {
            String jsonString = objectMapper.writeValueAsString(dto.configuration());
            entity.setConfiguration(jsonString);
        } catch (JsonProcessingException e) {
             throw new IllegalArgumentException("Error al serializar el campo 'configuration' durante la actualización.", e);
        }
    }
}