package com.products.repository;

import com.products.entity.Location;
import com.products.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    
}
