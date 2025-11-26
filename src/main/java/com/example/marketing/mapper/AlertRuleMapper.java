package com.example.marketing.mapper;

import com.example.marketing.dto.AlertRuleRequestDTO;
import com.example.marketing.dto.AlertRuleResponseDTO;
import com.example.marketing.model.AlertRule;
import com.example.marketing.model.Campaign;
import com.example.marketing.model.NotificationChannel;

public class AlertRuleMapper {

	// -------------------------
	// Convertir entidad → DTO
	// -------------------------
	public static AlertRuleResponseDTO toResponse(AlertRule entity) {
		if (entity == null) return null;

		return AlertRuleResponseDTO.builder()
				.ruleId(entity.getRuleId())
				.campaign(entity.getCampaign() != null ? entity.getCampaign().getCampaignId() : null)
				.ruleName(entity.getRuleName())
				.description(entity.getDescription())
				.conditionsJson(entity.getConditionsJson())
				.notificationChannel(entity.getNotificationChannel() != null ?
						entity.getNotificationChannel().getChannelId() : null)
				.active(entity.getIsActive())
				.build();
	}


	// -------------------------
	// Convertir DTO → Entidad (nuevo registro)
	// -------------------------
	public static AlertRule toEntity(AlertRuleRequestDTO dto) {
		if (dto == null) return null;

		AlertRule entity = new AlertRule();

		entity.setRuleName(dto.ruleName());
		entity.setDescription(dto.description());
		entity.setConditionsJson(dto.conditionsJson());
		entity.setIsActive(dto.isActive());

		// ---- Campaign (solo seteamos ID) ----
		Campaign campaign = new Campaign();
		campaign.setCampaignId(dto.campaignId());
		entity.setCampaign(campaign);

		// ---- NotificationChannel (solo seteamos ID si viene) ----
		if (dto.notificationChannelId() != null) {
			NotificationChannel channel = new NotificationChannel();
			channel.setChannelId(dto.notificationChannelId());
			entity.setNotificationChannel(channel);
		}

		return entity;
	}


	// ----------------------------------------
	// Actualizar entidad existente (PATCH/PUT)
	// ----------------------------------------
	public static void copyToEntity(AlertRuleRequestDTO dto, AlertRule existing) {
		if (dto == null || existing == null) return;

		existing.setRuleName(dto.ruleName());
		existing.setDescription(dto.description());
		existing.setConditionsJson(dto.conditionsJson());
		existing.setIsActive(dto.isActive());

		// Campaign
		Campaign c = new Campaign();
		c.setCampaignId(dto.campaignId());
		existing.setCampaign(c);

		// Notification channel
		if (dto.notificationChannelId() != null) {
			NotificationChannel nc = new NotificationChannel();
			nc.setChannelId(dto.notificationChannelId());
			existing.setNotificationChannel(nc);
		} else {
			existing.setNotificationChannel(null);
		}
	}
}
