package com.example.APITransporte.controller;

import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.model.enums.Status;
import com.example.APITransporte.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RestController
public class GestionOrdenesController {

    @Autowired
    OrderService orderService;

    //Crear una nueva orden
    @PostMapping("/creaOrden")
    public String creaOrden(@Valid @RequestBody OrderDTO orderDTO){
        log.info("Orden recibida: " + orderDTO.toString());

        return orderService.creaOrden(orderDTO);
    }

    //Cambiar el estado de la orden (validar flujo válido entre estados).
    @PutMapping("/cambiaEstadoOrden")
    public String cambiaEstadoOrden(@Valid @RequestBody OrderDTO orderDTO){
        log.info("Orden recibida para actualizar: " + orderDTO.toString());

        return orderService.actualizaOrden(orderDTO);
    }

    //Consultar orden por ID.
    @GetMapping("/consultaOrdenId/{id}")
    public Map<String, OrderEntity> consultaOrdenId(@PathVariable UUID id){
        log.info("ID Orden: " + id);

        return orderService.consultaOrden(id);
    }

    //Listar órdenes con filtros: por status, fecha, origen o destino.
    /*
    @PostMapping("/filtraOrden")
    public String filtraOrden(@RequestBody Order order){
        log.info("Orden recibida: " + order.toString());

        return orderService.creaOrden(order);
    }

    private final OrderService orderService;

    @GetMapping("/filtraOrden")
    public ResponseEntity<List<OrderDTO>> listOrders(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fechaFin
    ) {
        List<OrderDTO> orders = orderService.listOrders(status, origin, destination, fechaInicio, fechaFin);
        return ResponseEntity.ok(orders);
    }

*/
}
