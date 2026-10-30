package com.example.APITransporte.mapper;

import com.example.APITransporte.dto.AsignacionOrdenDTO;
import com.example.APITransporte.model.entity.AsignacionOrdenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AsignacionOrdenmapper {

    AsignacionOrdenmapper INSTANCE = Mappers.getMapper(AsignacionOrdenmapper.class);

    AsignacionOrdenEntity toEntity(AsignacionOrdenDTO asignacionOrdenDTO);

}
