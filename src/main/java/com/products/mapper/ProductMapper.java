package com.products.mapper;

import com.products.dto.ProductDTO;
import com.products.dto.SubCategoryDTO;
import com.products.entity.Product;
import com.products.entity.SubCategory;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel="spring")
@Component
public interface ProductMapper {

    @Named("entityToDTO")
    ProductDTO entityToDTO(Product entity);

    @Named("dtoToEntity")
    Product dtoToEntity(ProductDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<ProductDTO> entitiesToDTOs(List<Product> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Product> dtosToEntities(List<ProductDTO> dtoList);
}
