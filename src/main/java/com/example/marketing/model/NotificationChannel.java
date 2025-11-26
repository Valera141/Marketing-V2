package com.example.marketing.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notification_channels")
public class NotificationChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Integer channelId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "channel_type", nullable = false, length = 50)
    private String channelType; // Ej: EMAIL, SLACK

    @Column(name = "configuration", nullable = false, columnDefinition = "JSONB")
    private String configuration; // Guardamos el JSON como String
}