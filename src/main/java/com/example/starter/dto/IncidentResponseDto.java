package com.example.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class IncidentResponseDto {
    UUID id;
    String serviceId;
    String severity;
    String title;
    String description;
    String dedupKey;
    LocalDateTime timestamp;
    String incidentStatus;
}
