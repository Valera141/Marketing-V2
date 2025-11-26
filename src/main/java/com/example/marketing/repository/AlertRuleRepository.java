package com.example.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.marketing.model.AlertRule;

public interface AlertRuleRepository extends JpaRepository<AlertRule, Integer> {
}
