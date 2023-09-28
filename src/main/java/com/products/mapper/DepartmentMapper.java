package com.products.mapper;


import com.products.dto.DepartmentDTO;
import com.products.dto.LocationDTO;
import com.products.entity.Department;
import com.products.entity.Location;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel="spring")
@Component
public interface DepartmentMapper {

    @Named("entityToDTO")
    DepartmentDTO entityToDTO(Department entity);

    @Named("dtoToEntity")
    Department dtoToEntity(DepartmentDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<DepartmentDTO> entitiesToDTOs(List<Department> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Department> dtosToEntities(List<DepartmentDTO> dtoList);


}
