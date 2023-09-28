package com.products.service;

import com.products.dto.*;

import java.util.List;

public interface LocationService {

    List<LocationDTO> getAll();

    List<GetProductDetailsDTO> getProductDetails(String locationName, String departmentName, String categoryName, String subcategoryName);


    LocationDTO save(LocationDTO locationDTO);

    List<DepartmentDTO> findAllByLocationId(Integer locationId);


    List<GetCategoryDetailsDTO> findAllCategories(Integer locationId , Integer departmentId);

    List<GetSubCategoryDetailsDTO> findAllSubCategories(Integer locationId , Integer departmentId , Integer categoryId);

    GetSubCategoryDetailsDTO findAllSubCategoriesById(Integer locationId , Integer departmentId
            , Integer categoryId , Integer subCategoryId );



}
