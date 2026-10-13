package com.example.starter.service;

import com.example.starter.dto.AlertRequest;
import com.example.starter.dto.AlertSeverity;
import com.example.starter.dto.IncidentResponseDto;
import com.example.starter.model.Incident;
import com.example.starter.repository.InMemoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class IncidentServiceImpl {
    private final InMemoryRepository inMemoryRepository;

    public IncidentServiceImpl(InMemoryRepository inMemoryRepository) {
        this.inMemoryRepository = inMemoryRepository;
    }

    public IncidentResponseDto createIncident(AlertRequest alert){
        String notificationStatus="";
        if(alert.getSeverity().getData()=="CRITICAL"){
            notificationStatus="PAGED";
            log.info("call Exotel to page on-call person");
        }
        else if(alert.getSeverity().getData()=="WARNING"){
            notificationStatus="SKIPPED";
        }
        else if(alert.getSeverity().getData()=="INFO"){
            notificationStatus="RECORDED";
        }
        else{
            log.info("Not a valid severity");
            return null;
        }
        Incident incident=inMemoryRepository.save(mapAlertToIncident(alert,notificationStatus));
        return mapIncidentToIncidentResponseDto(incident);
    }

    public Incident mapAlertToIncident(AlertRequest alertRequest,String notificationStatus){
        Incident incident=new Incident(UUID.randomUUID(),alertRequest.getServiceId(),
                alertRequest.getSeverity().getData(),alertRequest.getTitle(),alertRequest.getDescription(),
                alertRequest.getDedupKey(),alertRequest.getTimestamp(),notificationStatus);
        return incident;
    }

    private IncidentResponseDto mapIncidentToIncidentResponseDto(Incident alertRequest){
        IncidentResponseDto incidentResponse=new IncidentResponseDto(UUID.randomUUID(),alertRequest.getServiceId(),
                alertRequest.getSeverity(),alertRequest.getTitle(),alertRequest.getDescription(),
                alertRequest.getDedupKey(),alertRequest.getTimestamp(),alertRequest.getIncidentStatus());
        return incidentResponse;
    }

}
