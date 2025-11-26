package com.example.marketing.controller;

import com.example.marketing.dto.DashboardViewRequestDTO;
import com.example.marketing.dto.DashboardViewResponseDTO;
import com.example.marketing.model.SavedDashboardView;
import com.example.marketing.model.User;
import com.example.marketing.repository.SavedDashboardViewRepository;
import com.example.marketing.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard-views")
@RequiredArgsConstructor
public class DashboardViewController {

    private final SavedDashboardViewRepository viewRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<DashboardViewResponseDTO> create(@Valid @RequestBody DashboardViewRequestDTO req) {
        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        SavedDashboardView entity = SavedDashboardView.builder()
                .viewName(req.viewName())
                .configurationJson(req.configurationJson())
                .user(user)
                .build();

        return new ResponseEntity<>(mapToDTO(viewRepository.save(entity)), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DashboardViewResponseDTO>> getByUser(@PathVariable Integer userId) {
        List<DashboardViewResponseDTO> views = viewRepository.findByUser_UserId(userId)
                .stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(views);
    }

    private DashboardViewResponseDTO mapToDTO(SavedDashboardView entity) {
        return DashboardViewResponseDTO.builder()
                .viewId(entity.getViewId())
                .viewName(entity.getViewName())
                .configurationJson(entity.getConfigurationJson())
                .userId(entity.getUser().getUserId())
                .build();
    }
}