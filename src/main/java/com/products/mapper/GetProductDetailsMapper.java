package com.products.mapper;


import com.products.dto.GetProductDetailsDTO;
import com.products.entity.proc.GetProductDetails;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel="spring")
@Component
public interface GetProductDetailsMapper {

    @Named("entityToDTO")
    GetProductDetailsDTO entityToDTO(GetProductDetails entity);

    @Named("dtoToEntity")
    GetProductDetails dtoToEntity(GetProductDetailsDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<GetProductDetailsDTO> entitiesToDTOs(List<GetProductDetails> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<GetProductDetails> dtosToEntities(List<GetProductDetailsDTO> dtoList);

}
