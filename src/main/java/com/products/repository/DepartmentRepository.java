package com.products.repository;

import com.products.entity.Department;
import com.products.entity.SubCategory;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
    Iterable<Department> findAllByLocationId(Integer locationId);

}





