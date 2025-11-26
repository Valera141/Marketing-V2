package com.example.marketing.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class DashboardViewResponseDTO {
    @JsonProperty("view_id") private Integer viewId;
    @JsonProperty("view_name") private String viewName;
    @JsonProperty("configuration") private String configurationJson;
    @JsonProperty("user_id") private Integer userId;
}