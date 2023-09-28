package com.products.mapper;


import com.products.dto.SubCategoryDTO;
import com.products.dto.UserDTO;
import com.products.entity.SubCategory;
import com.products.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel="spring")
@Component
public interface SubCategoryMapper {

    @Named("entityToDTO")
    SubCategoryDTO entityToDTO(SubCategory entity);

    @Named("dtoToEntity")
    SubCategory dtoToEntity(SubCategoryDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<SubCategoryDTO> entitiesToDTOs(List<SubCategory> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<SubCategory> dtosToEntities(List<SubCategoryDTO> dtoList);

}
