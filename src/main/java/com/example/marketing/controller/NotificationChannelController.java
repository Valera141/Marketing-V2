package com.example.marketing.controller;

import com.example.marketing.dto.NotificationChannelRequestDTO;
import com.example.marketing.dto.NotificationChannelResponseDTO;
import com.example.marketing.service.NotificationChannelService; // 👈 Importar el Servicio
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

    // 1. Inyectar el Servicio en lugar del Repositorio
    private final NotificationChannelService notificationChannelService;

    // --- Endpoints CRUD ---

    @PostMapping
    public ResponseEntity<NotificationChannelResponseDTO> create(@Valid @RequestBody NotificationChannelRequestDTO req) {
        // 2. Delegar la lógica de creación y mapeo al Servicio.
        // El servicio se encarga de:
        // a) Serializar el JsonNode a String.
        // b) Construir la Entidad.
        // c) Guardar en el Repositorio.
        // d) Mapear a DTO de respuesta.
        NotificationChannelResponseDTO createdChannel = notificationChannelService.create(req);
        
        return new ResponseEntity<>(createdChannel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NotificationChannelResponseDTO>> getAll() {
        // 3. Delegar la lógica de búsqueda y mapeo al Servicio.
        List<NotificationChannelResponseDTO> channels = notificationChannelService.findAll();
        return ResponseEntity.ok(channels);
    }

    // 4. Se eliminan los métodos mapToDTO y la lógica de Entidad, ya que el Controller 
    //    solo debe llamar al Servicio.

    // 5. Agregaremos otros métodos CRUD por buenas prácticas:

    @GetMapping("/{id}")
    public ResponseEntity<NotificationChannelResponseDTO> getById(@PathVariable Integer id) {
        NotificationChannelResponseDTO channel = notificationChannelService.findById(id);
        return ResponseEntity.ok(channel);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NotificationChannelResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody NotificationChannelRequestDTO req) {
        NotificationChannelResponseDTO updatedChannel = notificationChannelService.update(id, req);
        return ResponseEntity.ok(updatedChannel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        notificationChannelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}