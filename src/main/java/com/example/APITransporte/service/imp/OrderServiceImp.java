package com.example.APITransporte.service.imp;

import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.mapper.OrderMapper;
import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.model.enums.Status;
import com.example.APITransporte.repository.OrderRepository;
import com.example.APITransporte.repository.OrderSpecifications;
import com.example.APITransporte.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    public String creaOrden(OrderDTO orderDTO){

        OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
        orderRepository.save(orderEntity);

        return "Orden recibida: " + orderDTO.toString();
    }

    public String actualizaOrden(OrderDTO orderDTO){

        OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
        Optional<OrderEntity> orderEncontrada = orderRepository.findById(orderEntity.getId());

        if(orderEncontrada.isPresent()){
            Status actualStatus = orderEncontrada.get().getStatus();

            if (!actualStatus.puedeCambiarEstadoA(orderEntity.getStatus())) {
               return  "Transición no permitida: No se puede cambiar de " + orderEncontrada.get().getStatus() + " a " + orderEntity.getStatus();
            }


        }else{
            return "Orden no encontrada: " + orderDTO;
        }

        OrderEntity orderSave = orderEncontrada.get();
        orderSave.setStatus(orderEntity.getStatus());
        orderRepository.save(orderSave);

        return "Estado actualizado con éxito a: " + orderEntity.getStatus();
    }


    public Map<String,OrderEntity> consultaOrden(UUID id){
        Map<String,OrderEntity> mapResponse = new HashMap<>();
        orderRepository.findById(id).ifPresentOrElse(
                order -> mapResponse.put("Orden encontrada", order),
                () -> mapResponse.put("Orden no encontrada", null)
        );

        return mapResponse;
    }


    public List<OrderEntity> filtraOrden(Status status, String origin, String destination, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        boolean tieneStatus = (status != null);
        boolean tieneOrigin = (origin != null && !origin.isBlank());
        boolean tieneDestination = (destination != null && !destination.isBlank());
        boolean tieneFechaInicio = (fechaInicio != null);
        boolean tieneFechaFin = (fechaFin != null);

        if (!tieneStatus && !tieneOrigin && !tieneDestination && !tieneFechaInicio && !tieneFechaFin) {
            throw new IllegalArgumentException("Al menos un filtro de búsqueda debe ser proporcionado.");
        }

        if (tieneFechaInicio && tieneFechaFin && fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin.");
        }

        Specification<OrderEntity> spec = Specification
                .where(OrderSpecifications.conStatus(status))
                .and(OrderSpecifications.conOrigin(origin))
                .and(OrderSpecifications.conDestination(destination))
                .and(OrderSpecifications.entreFechas(fechaInicio, fechaFin));

        return orderRepository.findAll(spec);
    }

}
