package com.example.APITransporte.service;

import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.model.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface OrderService {

    public String creaOrden(OrderDTO orderDTO);

    public String actualizaOrden(OrderDTO orderDTO);

    public Map<String, OrderEntity> consultaOrden(UUID id);

    public List<OrderEntity> filtraOrden(Status status, String origin, String destination, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
