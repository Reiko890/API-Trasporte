package com.example.APITransporte.controller;


import com.example.APITransporte.dto.AsignacionOrdenDTO;
import com.example.APITransporte.model.entity.AsignacionOrdenEntity;
import com.example.APITransporte.service.imp.AlmacenamientoService;
import com.example.APITransporte.service.imp.AsignacionOrderServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AsignacionOrdenesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AsignacionOrderServiceImp asignacionOrderServiceImp;

    @Mock
    private AlmacenamientoService almacenamientoService;

    @InjectMocks
    private AsignacionOrdenesController asignacionOrdenesController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(asignacionOrdenesController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Deberia asignar un conductor a una orden de forma exitosa")
    void asigConductorOrden_Exitoso() throws Exception {

        AsignacionOrdenDTO dto = new AsignacionOrdenDTO(1,
                UUID.fromString("3c8a7b04-2b0d-44fb-8cbf-b5553faa750e"),
                UUID.fromString("3c8a7b04-2b0d-44fb-8cbf-b5553faa750e"),
                "","");
        AsignacionOrdenEntity entidadEsperada = new AsignacionOrdenEntity(1,
                UUID.fromString("3c8a7b04-2b0d-44fb-8cbf-b5553faa750e"),
                UUID.fromString("3c8a7b04-2b0d-44fb-8cbf-b5553faa750e"),
                "","");

        when(asignacionOrderServiceImp.creaAsignacionOrderService(any(AsignacionOrdenDTO.class)))
                .thenReturn(entidadEsperada);


        mockMvc.perform(post("/asignacion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(entidadEsperada)));
    }

    @Test
    @DisplayName("Deberia subir y guardar un archivo PDF exitosamente")
    void subirArchivoPdf_Exitoso() throws Exception {

        UUID idDriver = UUID.randomUUID();
        UUID idOrder = UUID.randomUUID();


        MockMultipartFile pdfMock = new MockMultipartFile(
                "pdf",
                "documento.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                "contenido de prueba pdf".getBytes()
        );


        mockMvc.perform(multipart("/archivoPdf")
                        .file(pdfMock)
                        .param("idDriver", idDriver.toString())
                        .param("idOrder", idOrder.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("PDF guardado correctamente."));
    }

    @Test
    @DisplayName("Deberia retornar error 500 cuando falle el almacenamiento del archivo PDF")
    void subirArchivoPdf_ErrorAlmacenamiento() throws Exception {

        UUID idDriver = UUID.randomUUID();
        UUID idOrder = UUID.randomUUID();
        MockMultipartFile pdfMock = new MockMultipartFile(
                "pdf", "documento.pdf", MediaType.APPLICATION_PDF_VALUE, "datos".getBytes()
        );


        doThrow(new IOException("Disco lleno"))
                .when(almacenamientoService).guardarArchivo(any(), eq(idDriver), eq(idOrder), eq("pdf"));


        mockMvc.perform(multipart("/archivoPdf")
                        .file(pdfMock)
                        .param("idDriver", idDriver.toString())
                        .param("idOrder", idOrder.toString()))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error al guardar el pdf: Disco lleno"));
    }

    @Test
    @DisplayName("Deberia subir y guardar una imagen exitosamente")
    void subirArchivoImagen_Exitoso() throws Exception {

        UUID idDriver = UUID.randomUUID();
        UUID idOrder = UUID.randomUUID();

        MockMultipartFile imagenMock = new MockMultipartFile(
                "imagen",
                "foto.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "datos-de-imagen-binarios".getBytes()
        );


        mockMvc.perform(multipart("/archivoImg")
                        .file(imagenMock)
                        .param("idDriver", idDriver.toString())
                        .param("idOrder", idOrder.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Imagen guardada correctamente."));
    }

    @Test
    @DisplayName("Deberia retornar error 500 cuando falle el almacenamiento de la imagen")
    void subirArchivoImagen_ErrorAlmacenamiento() throws Exception {

        UUID idDriver = UUID.randomUUID();
        UUID idOrder = UUID.randomUUID();
        MockMultipartFile imagenMock = new MockMultipartFile(
                "imagen", "foto.png", MediaType.IMAGE_PNG_VALUE, "datos".getBytes()
        );

        doThrow(new IOException("Permiso denegado"))
                .when(almacenamientoService).guardarArchivo(any(), eq(idDriver), eq(idOrder), eq("img"));


        mockMvc.perform(multipart("/archivoImg")
                        .file(imagenMock)
                        .param("idDriver", idDriver.toString())
                        .param("idOrder", idOrder.toString()))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error al guardar la imagen: Permiso denegado"));
    }
}