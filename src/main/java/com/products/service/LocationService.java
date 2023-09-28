package com.products.service;

import com.products.dto.GetProductDetailsDTO;
import com.products.dto.LocationDTO;
import com.products.dto.SubCategoryDTO;

import java.util.List;

public interface LocationService {

    List<LocationDTO> getAll();

    List<GetProductDetailsDTO> getProductDetails(String locationName, String departmentName, String categoryName, String subcategoryName);


    LocationDTO save(LocationDTO locationDTO);

}
