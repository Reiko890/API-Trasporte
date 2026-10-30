package com.example.APITransporte.repository;

import com.example.APITransporte.model.entity.AsignacionOrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AsignacionOrderRepository extends JpaRepository<AsignacionOrdenEntity,Integer> {

    public AsignacionOrdenEntity findByIdDriverAndIdOrder(UUID IdDriver,UUID IdOrder);

}
