package com.example.APITransporte.service.imp;

import com.example.APITransporte.dto.AsignacionOrdenDTO;
import com.example.APITransporte.mapper.AsignacionOrdenmapper;
import com.example.APITransporte.model.entity.AsignacionOrdenEntity;
import com.example.APITransporte.model.entity.DriverEntity;
import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.repository.AsignacionOrderRepository;
import com.example.APITransporte.repository.DriverRepository;
import com.example.APITransporte.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AsignacionOrderServiceImp implements AsignacionOrderService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    AsignacionOrderRepository asignacionOrderRepository;

    @Autowired
    AsignacionOrdenmapper asignacionOrdenmapper;

    public AsignacionOrdenEntity creaAsignacionOrderService(AsignacionOrdenDTO asignacionOrdenDTO){
        log.info("Asignacion recibida: "+asignacionOrdenDTO);
        AsignacionOrdenEntity asignacionOrdenEntity = asignacionOrdenmapper.toEntity(asignacionOrdenDTO);


        DriverEntity driverEntity= driverRepository.findByActiveTrueAndId(asignacionOrdenDTO.getIdDriver());
        log.info("driverEntity: "+driverEntity);

        if(driverEntity != null){
            java.util.Optional<OrderEntity> orderEntity = orderRepository.findById(asignacionOrdenDTO.getIdOrder());
            log.info("orderEntity: "+orderEntity);
            if(orderEntity.isPresent()){

                asignacionOrderRepository.save(asignacionOrdenEntity);
            }

        }

        return asignacionOrdenEntity;
    }

}
