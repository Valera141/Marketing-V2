package com.example.marketing.controller;

import com.example.marketing.dto.NotificationChannelRequestDTO;
import com.example.marketing.dto.NotificationChannelResponseDTO;
import com.example.marketing.model.NotificationChannel;
import com.example.marketing.repository.NotificationChannelRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notification-channels")
@RequiredArgsConstructor
public class NotificationChannelController {

    private final NotificationChannelRepository repository;

    @PostMapping
    public ResponseEntity<NotificationChannelResponseDTO> create(@Valid @RequestBody NotificationChannelRequestDTO req) {
        NotificationChannel entity = NotificationChannel.builder()
                .name(req.name())
                .channelType(req.channelType())
                .configuration(req.configuration())
                .build();
        NotificationChannel saved = repository.save(entity);
        return new ResponseEntity<>(mapToDTO(saved), HttpStatus.CREATED);
    }

    @GetMapping
    public List<NotificationChannelResponseDTO> getAll() {
        return repository.findAll().stream().map(this::mapToDTO).toList();
    }

    private NotificationChannelResponseDTO mapToDTO(NotificationChannel entity) {
        return NotificationChannelResponseDTO.builder()
                .channelId(entity.getChannelId())
                .name(entity.getName())
                .channelType(entity.getChannelType())
                .configuration(entity.getConfiguration())
                .build();
    }
}