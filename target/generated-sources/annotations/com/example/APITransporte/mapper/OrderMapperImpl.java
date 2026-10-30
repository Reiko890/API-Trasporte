package com.example.APITransporte.mapper;

import com.example.APITransporte.dto.OrderDTO;
import com.example.APITransporte.model.entity.OrderEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-30T13:19:40-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.13.0.2 (Microsoft)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId( orderDTO.id() );
        orderEntity.setStatus( orderDTO.status() );
        orderEntity.setOrigin( orderDTO.origin() );
        orderEntity.setDestination( orderDTO.destination() );
        orderEntity.setCreatedAt( orderDTO.createdAt() );
        orderEntity.setUpdatedAt( orderDTO.updatedAt() );

        return orderEntity;
    }
}
