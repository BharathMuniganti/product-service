package com.products.mapper;


import com.products.dto.GetCategoryDetailsDTO;
import com.products.dto.GetSubCategoryDetailsDTO;
import com.products.entity.proc.GetCategoryDetails;
import com.products.entity.proc.GetSubCategoryDetails;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel="spring")
@Component
public interface GetSubCategoryDetailsMapper {

    @Named("entityToDTO")
    GetSubCategoryDetailsDTO entityToDTO(GetSubCategoryDetails entity);

    @Named("dtoToEntity")
    GetSubCategoryDetails dtoToEntity(GetSubCategoryDetailsDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<GetSubCategoryDetailsDTO> entitiesToDTOs(List<GetSubCategoryDetails> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<GetSubCategoryDetails> dtosToEntities(List<GetSubCategoryDetailsDTO> dtoList);

}
