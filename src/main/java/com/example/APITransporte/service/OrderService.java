package com.example.APITransporte.service;

import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.mapper.OrderMapper;
import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.model.enums.Status;
import com.example.APITransporte.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    public String creaOrden(OrderDTO orderDTO){

        OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
        System.err.println("orderDTO:: "+orderDTO.toString());
        System.err.println("orderEntity:: "+orderEntity.toString());

        orderRepository.save(orderEntity);



        return "Orden recibida: " + orderDTO.toString();
    }

    public String actualizaOrden(OrderDTO orderDTO){

        OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
        System.err.println("orderDTO:: "+orderDTO.toString());
        System.err.println("orderEntity:: "+orderEntity.toString());

        Optional<OrderEntity> orderEncontrada = orderRepository.findById(orderEntity.getId());

        if(orderEncontrada.isPresent()){
            System.err.println("orderEncontrada:: "+orderEncontrada);
            Status actualStatus = orderEncontrada.get().getStatus();

            if (!actualStatus.puedeCambiarEstadoA(orderEntity.getStatus())) {
                //throw new IllegalStateException(
                //      "Transición no permitida: No se puede cambiar de " + orderEncontrada.get().getStatus() + " a " + orderEntity.getStatus()
               // );
               return  "Transición no permitida: No se puede cambiar de " + orderEncontrada.get().getStatus() + " a " + orderEntity.getStatus();
            }


        }else{
            return "Orden no encontrada: " + orderDTO;
        }

        OrderEntity orderSave = orderEncontrada.get();
        orderSave.setStatus(orderEntity.getStatus());
        orderRepository.save(orderSave);

        // Si pasa la validación, procedes a guardar el nuevo estado
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


}
