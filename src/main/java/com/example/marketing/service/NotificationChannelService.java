package com.example.marketing.service;

import com.example.marketing.dto.NotificationChannelRequestDTO;
import com.example.marketing.dto.NotificationChannelResponseDTO;

import java.util.List;

public interface NotificationChannelService {

    NotificationChannelResponseDTO create(NotificationChannelRequestDTO request);

    NotificationChannelResponseDTO findById(Integer channelId);

    List<NotificationChannelResponseDTO> findAll(); // Usamos List para pocos canales

    NotificationChannelResponseDTO update(Integer channelId, NotificationChannelRequestDTO request);

    void delete(Integer channelId);
}