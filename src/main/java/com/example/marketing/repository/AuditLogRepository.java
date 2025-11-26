package com.example.marketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.marketing.model.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
}
