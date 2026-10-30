package com.example.APITransporte.service;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.mapper.DriverMapper;
import com.example.APITransporte.model.entity.DriverEntity;
import com.example.APITransporte.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;
import java.util.Map;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DriverMapper driverMapper;

    public String creaDriver(DriverDTO driverDTO){
        System.err.println("driverDTO:: "+driverDTO.toString());
        DriverEntity driverEntity = driverMapper.toEntity(driverDTO);
        System.err.println("driverEntity:: "+driverEntity.toString());
        driverRepository.save(driverEntity);

        return "Orden recibida: " + driverDTO.toString();
    }

    public List<DriverEntity> getListaConductores(){
        System.err.println("Lista de conductores");

        return driverRepository.findByActiveTrue();
    }



}
