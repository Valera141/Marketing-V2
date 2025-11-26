package com.example.marketing.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class NotificationChannelResponseDTO {
    @JsonProperty("channel_id") private Integer channelId;
    private String name;
    @JsonProperty("channel_type") private String channelType;
    private String configuration;
}