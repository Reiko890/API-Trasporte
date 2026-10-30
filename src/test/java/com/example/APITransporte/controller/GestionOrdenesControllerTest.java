package com.example.APITransporte.controller;

import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.model.entity.OrderEntity;
import com.example.APITransporte.model.enums.Status;
import com.example.APITransporte.service.imp.OrderServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class GestionOrdenesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderServiceImp orderServiceImp;

    @InjectMocks
    private GestionOrdenesController gestionOrdenesController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gestionOrdenesController).build();
        objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Deberia crear una orden exitosamente y retornar mensaje de exito")
    void creaOrden_Exitoso() throws Exception {

        OrderDTO orderDTO = new OrderDTO(UUID.fromString("3c8a7b04-2b0d-44fb-8cbf-b5553faa750e"),
                Status.CREATED,
                "CDMX",
                "Puebla",
                LocalDateTime.parse("2026-10-29 15:30:00.000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")),
                LocalDateTime.parse("2026-10-29 15:30:00.000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));

        String respuestaEsperada = "Orden creada exitosamente";

        when(orderServiceImp.creaOrden(any(OrderDTO.class))).thenReturn(respuestaEsperada);

        // Act & Assert
        mockMvc.perform(post("/creaOrden")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(respuestaEsperada));
    }

    @Test
    @DisplayName("Deberia cambiar el estado de la orden exitosamente")
    void cambiaEstadoOrden_Exitoso() throws Exception {

        OrderDTO orderDTO = new OrderDTO(UUID.fromString("3c8a7b04-2b0d-44fb-8cbf-b5553faa750e"),
                Status.CREATED,
                "CDMX",
                "Puebla",
                LocalDateTime.parse("2026-10-29 15:30:00.000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")),
                LocalDateTime.parse("2026-10-29 15:30:00.000000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")));
        String respuestaEsperada = "Estado de orden actualizado";

        when(orderServiceImp.actualizaOrden(any(OrderDTO.class))).thenReturn(respuestaEsperada);


        mockMvc.perform(put("/cambiaEstadoOrden")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(respuestaEsperada));
    }

    @Test
    @DisplayName("Deberia consultar y retornar una orden por ID")
    void consultaOrdenId_Exitoso() throws Exception {

        UUID ordenId = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity();

        Map<String, OrderEntity> respuestaEsperada = new HashMap<>();
        respuestaEsperada.put("orden", orderEntity);

        when(orderServiceImp.consultaOrden(ordenId)).thenReturn(respuestaEsperada);


        mockMvc.perform(get("/consultaOrdenId/{id}", ordenId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(respuestaEsperada)));
    }

    @Test
    @DisplayName("Debería filtrar órdenes según los parámetros de búsqueda provistos")
    void filtraOrden_Exitoso() throws Exception {

        Status statusFiltro = Status.CREATED;
        String origin = "CDMX";
        String destination = "Puebla";
        String fechaInicioStr = "2026-10-01 00:00:00";
        String fechaFinStr = "2026-10-31 23:59:59";

        OrderEntity ordenFiltrada = new OrderEntity();
        List<OrderEntity> listaEsperada = Collections.singletonList(ordenFiltrada);

        when(orderServiceImp.filtraOrden(any(), any(), any(), any(), any())).thenReturn(listaEsperada);


        mockMvc.perform(get("/filtraOrden")
                        .param("status", statusFiltro.name())
                        .param("origin", origin)
                        .param("destination", destination)
                        .param("fechaInicio", fechaInicioStr)
                        .param("fechaFin", fechaFinStr)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listaEsperada)));
    }
}