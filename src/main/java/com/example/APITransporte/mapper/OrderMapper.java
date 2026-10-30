package com.example.APITransporte.mapper;

import com.example.APITransporte.dto.DriverDTO;
import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.model.entity.DriverEntity;
import com.example.APITransporte.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    // Convierte DTO a Entity
    OrderEntity toEntity(OrderDTO orderDTO);
}
