package com.products.mapper;


import com.products.dto.LocationDTO;
import com.products.entity.Location;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;



@Mapper(componentModel="spring")
@Component
public interface LocationMapper {

    @Named("entityToDTO")
    LocationDTO entityToDTO(Location entity);

    @Named("dtoToEntity")
    Location dtoToEntity(LocationDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<LocationDTO> entitiesToDTOs(List<Location> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Location> dtosToEntities(List<LocationDTO> dtoList);


}
