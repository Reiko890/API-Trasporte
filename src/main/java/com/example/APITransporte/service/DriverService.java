package com.example.APITransporte.service;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.model.entity.DriverEntity;

import java.util.List;

public interface DriverService {

    public String creaDriver(DriverDTO driverDTO);

    public List<DriverEntity> getListaConductores();


}
