package com.example.starter.repository;

import com.example.starter.model.Incident;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryRepository implements IncidentRepository{
    private final ConcurrentMap<UUID,Incident> incidentConcurrentMap = new ConcurrentHashMap<>();
    @Override
    public Incident save(Incident incident) {
        incidentConcurrentMap.put(incident.getId(),incident);
        return incident;
    }
}
