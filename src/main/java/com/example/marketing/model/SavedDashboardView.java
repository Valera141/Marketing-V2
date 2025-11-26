package com.example.marketing.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "saved_dashboard_views")
public class SavedDashboardView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id")
    private Integer viewId;

    @Column(name = "view_name", nullable = false, length = 100)
    private String viewName;

    @Column(name = "configuration_json", nullable = false, columnDefinition = "JSONB")
    private String configurationJson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}