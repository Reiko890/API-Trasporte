package com.example.APITransporte.controller;

import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.model.enums.Status;
import com.example.APITransporte.service.imp.OrderServiceImp;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RestController
public class GestionOrdenesController {

    @Autowired
    OrderServiceImp orderServiceImp;

    //Crear una nueva orden
    @PostMapping("/creaOrden")
    public String creaOrden(@Valid @RequestBody OrderDTO orderDTO){
        log.info("Orden recibida: " + orderDTO.toString());

        return orderServiceImp.creaOrden(orderDTO);
    }

    //Cambiar el estado de la orden (validar flujo válido entre estados).
    @PutMapping("/cambiaEstadoOrden")
    public String cambiaEstadoOrden(@Valid @RequestBody OrderDTO orderDTO){
        log.info("Orden recibida para actualizar: " + orderDTO.toString());

        return orderServiceImp.actualizaOrden(orderDTO);
    }

    //Consultar orden por ID.
    @GetMapping("/consultaOrdenId/{id}")
    public Map<String, OrderEntity> consultaOrdenId(@PathVariable UUID id){
        log.info("ID Orden: " + id);

        return orderServiceImp.consultaOrden(id);
    }

    //Listar órdenes con filtros: por status, fecha, origen o destino.
    @GetMapping("/filtraOrden")
    public ResponseEntity<List<OrderEntity>> filtraOrden(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaFin
    ) {
        log.info("Filtro de orden" );
        List<OrderEntity> orders = orderServiceImp.filtraOrden(status, origin, destination, fechaInicio, fechaFin);
        return ResponseEntity.ok(orders);
    }

}
