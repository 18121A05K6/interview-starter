package com.example.starter.controller;


import com.example.starter.dto.AlertRequest;
import com.example.starter.dto.IncidentResponseDto;
import com.example.starter.service.IncidentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentServiceImpl incidentService;

    public IncidentController(IncidentServiceImpl incidentService) {
        this.incidentService = incidentService;
    }

    @PostMapping
    public ResponseEntity<IncidentResponseDto> createIncident(@Valid AlertRequest alertRequest){
        IncidentResponseDto incidentResponseDto=incidentService.createIncident(alertRequest);
        return ResponseEntity.ok(incidentResponseDto);
    }
}
