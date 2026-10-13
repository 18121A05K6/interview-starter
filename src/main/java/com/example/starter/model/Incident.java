package com.example.starter.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Incident {

    UUID id;
    String serviceId;
    String severity;
    String title;
    String description;
    String dedupKey;
    LocalDateTime timestamp;
    String incidentStatus;

//    public Incident(UUID uuid, @NotNull String serviceId, String data, String title,
//                    String description, String dedupKey, LocalDateTime timestamp, String created) {
//    }
}
