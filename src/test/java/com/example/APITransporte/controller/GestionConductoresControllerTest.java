package com.example.APITransporte.controller;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.model.entity.DriverEntity;
import com.example.APITransporte.service.imp.DriverServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GestionConductoresControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DriverServiceImp driverServiceImp;

    @InjectMocks
    private GestionConductoresController gestionConductoresController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gestionConductoresController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Debería crear un conductor exitosamente y retornar mensaje de éxito")
    void creaConductor_Exitoso() throws Exception {

        DriverDTO driverDTO = new DriverDTO(UUID.fromString("717c7b56-4f6d-414e-909d-e056a07bc648"),"Pedro","F35CS",true);
        // Configura aquí las propiedades de tu DTO según tus campos, ej: driverDTO.setName("John");

        String respuestaEsperada = "Conductor creado exitosamente";
        when(driverServiceImp.creaDriver(any(DriverDTO.class))).thenReturn(respuestaEsperada);


        mockMvc.perform(post("/creaConductor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(driverDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(respuestaEsperada));
    }

    @Test
    @DisplayName("Debería retornar la lista de conductores activos")
    void listaDeConductores_Exitoso() throws Exception {

        DriverEntity conductor1 = new DriverEntity(UUID.fromString("7c888745-1f2e-4c46-a24b-3d8cd26a996b"),"Pedro","F35CS",true);
        DriverEntity conductor2 = new DriverEntity(UUID.fromString("df716ee2-e236-4570-a71b-6cfb09d9c9f2"),"Juan","JU67DZ",true);
        List<DriverEntity> listaEsperada = Arrays.asList(conductor1, conductor2);

        when(driverServiceImp.getListaConductores()).thenReturn(listaEsperada);


        mockMvc.perform(get("/listaDeConductores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listaEsperada)));
    }
}
