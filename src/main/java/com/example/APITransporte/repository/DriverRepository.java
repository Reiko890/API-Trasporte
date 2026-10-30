package com.example.APITransporte.repository;

import com.example.APITransporte.model.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, UUID> {

    public List<DriverEntity> findByActiveTrue();

    public DriverEntity findByActiveTrueAndId(UUID id);

}
