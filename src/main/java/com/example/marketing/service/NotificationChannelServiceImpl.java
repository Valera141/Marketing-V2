package com.example.marketing.service;

import com.example.marketing.dto.NotificationChannelRequestDTO;
import com.example.marketing.dto.NotificationChannelResponseDTO;
import com.example.marketing.mapper.NotificationChannelMapper;
import com.example.marketing.model.NotificationChannel;
import com.example.marketing.repository.NotificationChannelRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationChannelServiceImpl implements NotificationChannelService {

    private final NotificationChannelRepository repository;
    private final ObjectMapper objectMapper; // Inyectar para serialización JSON

    // Asumimos el Mapper para la conversión Entity <-> DTO

    @Override
    public NotificationChannelResponseDTO create(NotificationChannelRequestDTO request) {
        
        // Lógica de Serialización JSON (para JSONB)
        String jsonConfiguration;
        try {
            jsonConfiguration = objectMapper.writeValueAsString(request.configuration());
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error al procesar la configuración JSON para el canal.", e);
        }

        // Usar el Mapper para construir la entidad, pasando el String serializado
        NotificationChannel newChannel = NotificationChannelMapper.toEntity(request);
        newChannel.setConfiguration(jsonConfiguration); // Sobrescribir la configuración ya que el Mapper no tiene ObjectMapper

        NotificationChannel saved = repository.save(newChannel);
        return NotificationChannelMapper.toResponseDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationChannelResponseDTO findById(Integer channelId) {
        NotificationChannel entity = repository.findById(channelId)
                .orElseThrow(() -> new EntityNotFoundException("Canal de Notificación no encontrado con ID: " + channelId));
        return NotificationChannelMapper.toResponseDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationChannelResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(NotificationChannelMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationChannelResponseDTO update(Integer channelId, NotificationChannelRequestDTO request) {
        NotificationChannel existing = repository.findById(channelId)
                .orElseThrow(() -> new EntityNotFoundException("Canal de Notificación no encontrado con ID: " + channelId));
        
        // Lógica de Serialización JSON
        try {
            String jsonConfiguration = objectMapper.writeValueAsString(request.configuration());
            existing.setConfiguration(jsonConfiguration);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error al procesar la configuración JSON para la actualización.", e);
        }

        NotificationChannelMapper.copyToEntity(request, existing);
        
        NotificationChannel updated = repository.save(existing);
        return NotificationChannelMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(Integer channelId) {
        if (!repository.existsById(channelId)) {
            throw new EntityNotFoundException("No se puede eliminar. Canal no encontrado con ID: " + channelId);
        }
        // Se recomienda verificar si está siendo usado por alguna alert_rule antes de eliminar.
        repository.deleteById(channelId);
    }
}