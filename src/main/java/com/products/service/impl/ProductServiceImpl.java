package com.products.service.impl;

import com.products.dto.LocationDTO;
import com.products.dto.ProductDTO;
import com.products.mapper.LocationMapper;
import com.products.mapper.ProductMapper;
import com.products.repository.LocationRepository;
import com.products.repository.ProductRepository;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository ;

    @Autowired
    ProductMapper productMapper ;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        return productMapper.entityToDTO(
                productRepository.save(productMapper.dtoToEntity(productDTO)));
    }
}
