package com.example.starter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertRequest {
//       "serviceId": "svc-payments",
//               "severity": "CRITICAL", // INFO | WARNING | CRITICAL
//               "title": "Payment gateway timeout",
//               "description": "p99 latency > 5s for 3 minutes",
//               "dedupKey": "payments-latency-001",---considering idempotency retry mech can be added
//               "timestamp": "2026-06-09T10:15:30Z"
    @NotNull
    String serviceId;
    @NotNull
    AlertSeverity severity;
    String title;
    String description;
    String dedupKey;
    LocalDateTime timestamp;
}
