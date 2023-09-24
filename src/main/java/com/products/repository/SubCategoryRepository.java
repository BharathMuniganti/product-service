package com.products.repository;

import com.products.entity.Category;
import com.products.entity.SubCategory;
import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Integer> {
    
}
