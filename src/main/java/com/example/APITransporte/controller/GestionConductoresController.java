package com.example.APITransporte.controller;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.model.entity.DriverEntity;
import com.example.APITransporte.service.DriverService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class GestionConductoresController {

    @Autowired
    DriverService driverService;

    //Crear un conductor
    @PostMapping("/creaConductor")
    public String creaConductor(@Valid @RequestBody DriverDTO driverDTO){
        log.info("Driver recibido: " + driverDTO.toString());

        return driverService.creaDriver(driverDTO);
    }


    //Listar todos los conductores activos.
    @GetMapping("/listaDeConductores")
    public List<DriverEntity> listaDeConductores(){
        log.info("Lista De Conductores ");

        return driverService.getListaConductores();
    }


}
