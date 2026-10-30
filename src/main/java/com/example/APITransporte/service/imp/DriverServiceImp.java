package com.example.APITransporte.service.imp;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.mapper.DriverMapper;
import com.example.APITransporte.model.entity.DriverEntity;
import com.example.APITransporte.repository.DriverRepository;
import com.example.APITransporte.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImp implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DriverMapper driverMapper;

    public String creaDriver(DriverDTO driverDTO){
        DriverEntity driverEntity = driverMapper.toEntity(driverDTO);
        driverRepository.save(driverEntity);

        return "Orden recibida: " + driverDTO.toString();
    }

    public List<DriverEntity> getListaConductores(){

        return driverRepository.findByActiveTrue();
    }



}
