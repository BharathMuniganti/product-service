package com.products.mapper;


import com.products.dto.GetCategoryDetailsDTO;
import com.products.dto.GetProductDetailsDTO;
import com.products.entity.proc.GetCategoryDetails;
import com.products.entity.proc.GetProductDetails;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel="spring")
@Component
public interface GetCategoryDetailsMapper {

    @Named("entityToDTO")
    GetCategoryDetailsDTO entityToDTO(GetCategoryDetails entity);

    @Named("dtoToEntity")
    GetCategoryDetails dtoToEntity(GetCategoryDetailsDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<GetCategoryDetailsDTO> entitiesToDTOs(List<GetCategoryDetails> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<GetCategoryDetails> dtosToEntities(List<GetCategoryDetailsDTO> dtoList);

}
