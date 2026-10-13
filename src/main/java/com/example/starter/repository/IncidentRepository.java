package com.example.starter.repository;

import com.example.starter.model.Incident;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository {

    public Incident save(Incident incident);
}
