package com.example.APITransporte.mapper;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.model.entity.DriverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    // Convierte DTO a Entity
    DriverEntity toEntity(DriverDTO driverDTO);
}
