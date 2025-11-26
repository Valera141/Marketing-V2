package com.example.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.marketing.model.GeneratedAlert;

public interface GeneratedAlertRepository extends JpaRepository<GeneratedAlert, Integer> {
}
