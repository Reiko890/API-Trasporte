package com.example.APITransporte.service.imp;

import com.example.APITransporte.model.entity.AsignacionOrdenEntity;
import com.example.APITransporte.repository.AsignacionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class AlmacenamientoService {

    @Autowired
    AsignacionOrderRepository asignacionOrderRepository;

    private final Path rootLocation = Paths.get("archivos");

    public AlmacenamientoService() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo inicializar la carpeta de almacenamiento", e);
        }
    }

    public void guardarArchivo(MultipartFile file, UUID idDriver,UUID idOrder, String tipoArchivo) throws IOException {

        String nombreOriginal = file.getOriginalFilename();
        String nombreFinal = UUID.randomUUID().toString() + "_" + (nombreOriginal != null ? nombreOriginal : "archivo");

        if (file.isEmpty()) {
            throw new IllegalArgumentException("El archivo está vacío.");
        }

        System.out.println("idDriver: "+idDriver);
        System.out.println("idOrder: "+idOrder);
        AsignacionOrdenEntity asignacionOrdenEntity = asignacionOrderRepository.findByIdDriverAndIdOrder(idDriver,idOrder);
        System.out.println("asignacionOrdenEntity: "+asignacionOrdenEntity.toString());



        Path destinationFile = this.rootLocation.resolve(
                        Paths.get(nombreFinal))
                .normalize().toAbsolutePath();


        if(tipoArchivo.equalsIgnoreCase("img")){
            asignacionOrdenEntity.setRutaImagen(String.valueOf(destinationFile));
            asignacionOrderRepository.save(asignacionOrdenEntity);
        }if(tipoArchivo.equalsIgnoreCase("pdf")){
            asignacionOrdenEntity.setRutaAsignacionPdf(String.valueOf(destinationFile));
            asignacionOrderRepository.save(asignacionOrdenEntity);
        }

        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
    }
}