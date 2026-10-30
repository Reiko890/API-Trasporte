package com.example.APITransporte.mapper;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.model.entity.DriverEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-30T12:45:50-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.13.0.2 (Microsoft)"
)
@Component
public class DriverMapperImpl implements DriverMapper {

    @Override
    public DriverEntity toEntity(DriverDTO driverDTO) {
        if ( driverDTO == null ) {
            return null;
        }

        DriverEntity driverEntity = new DriverEntity();

        driverEntity.setId( driverDTO.id() );
        driverEntity.setName( driverDTO.name() );
        driverEntity.setLicenseNumber( driverDTO.licenseNumber() );
        driverEntity.setActive( driverDTO.active() );

        return driverEntity;
    }
}
