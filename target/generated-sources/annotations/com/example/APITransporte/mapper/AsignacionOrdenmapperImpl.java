package com.example.APITransporte.mapper;

import com.example.APITransporte.dto.AsignacionOrdenDTO;
import com.example.APITransporte.model.entity.AsignacionOrdenEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-30T11:05:36-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.13.0.2 (Microsoft)"
)
@Component
public class AsignacionOrdenmapperImpl implements AsignacionOrdenmapper {

    @Override
    public AsignacionOrdenEntity toEntity(AsignacionOrdenDTO asignacionOrdenDTO) {
        if ( asignacionOrdenDTO == null ) {
            return null;
        }

        AsignacionOrdenEntity asignacionOrdenEntity = new AsignacionOrdenEntity();

        asignacionOrdenEntity.setId( asignacionOrdenDTO.getId() );
        asignacionOrdenEntity.setIdOrder( asignacionOrdenDTO.getIdOrder() );
        asignacionOrdenEntity.setIdDriver( asignacionOrdenDTO.getIdDriver() );
        asignacionOrdenEntity.setRutaAsignacionPdf( asignacionOrdenDTO.getRutaAsignacionPdf() );
        asignacionOrdenEntity.setRutaImagen( asignacionOrdenDTO.getRutaImagen() );

        return asignacionOrdenEntity;
    }
}
