package com.example.marketing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "campaigns")
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Integer campaignId;

    @Column(name = "campaign_name", nullable = false)
    private String campaignName;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    // Relación con el Usuario (Creador)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_user_id")
    private User creatorUser;

    // Relación con Publicaciones (Tu equipo la tenía comentada)
    @OneToMany(mappedBy = "campaign")
    private List<Publication> publications;
}