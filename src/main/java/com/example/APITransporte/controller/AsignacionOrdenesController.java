package com.example.APITransporte.controller;

import com.example.APITransporte.dto.AsignacionOrdenDTO;
import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.model.entity.AsignacionOrdenEntity;
import com.example.APITransporte.service.AlmacenamientoService;
import com.example.APITransporte.service.AsignacionOrderService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Log4j2
@RestController
public class AsignacionOrdenesController {

    @Autowired
    AsignacionOrderService asignacionOrderService;

    @Autowired
    AlmacenamientoService almacenamientoService;


    //Asignar un conductor a una orden (solo si el conductor está activo y la orden está
    //en estado CREATED).
    @PostMapping("/asignacion")
    public AsignacionOrdenEntity asigConductorOrden(@Valid @RequestBody AsignacionOrdenDTO asignacionOrdenDTO){
        log.info("Asignacion recibida: " + asignacionOrdenDTO.toString());

        return asignacionOrderService.creaAsignacionOrderService(asignacionOrdenDTO);
    }



    //Agregar un archivo a la asignación (PDF) y JPG
    @PostMapping("/archivoPdf")
    public ResponseEntity<String> subirArchivoPdf(@RequestParam("pdf") MultipartFile pdfFile,
                                                  @RequestParam UUID idDriver,
                                                  @RequestParam UUID idOrder){
        try {
            almacenamientoService.guardarArchivo(pdfFile,idDriver,idOrder,"pdf");
            return ResponseEntity.status(HttpStatus.OK).body("PDF guardado correctamente.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el pdf: " + e.getMessage());
        }
    }

    //Agregar una imagen (.png, .jpg)
    @PostMapping("/archivoImg")
    public ResponseEntity<String> subirArchivoImagen(@RequestParam("imagen") MultipartFile imagenFile,
                                                     @RequestParam UUID idDriver,
                                                     @RequestParam UUID idOrder) {
        try {
            almacenamientoService.guardarArchivo(imagenFile,idDriver,idOrder,"img");
            return ResponseEntity.status(HttpStatus.OK).body("Imagen guardada correctamente.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen: " + e.getMessage());
        }
    }


}
