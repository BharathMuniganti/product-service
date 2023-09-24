package com.products.controller;


import com.products.dto.APIResponse;
import com.products.dto.GetProductDetailsDTO;
import com.products.dto.LocationDTO;
import com.products.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductsController {

  @Autowired
  LocationService locationService;

  @GetMapping(path = "location")
  public ResponseEntity<List<LocationDTO>> getLocations() {
    List<LocationDTO> response =  locationService.getAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(path = "{locationName}/{departmentName}/{categoryName}/{subcategoryName}")
  public ResponseEntity<APIResponse> getProductDetails(@PathVariable(name = "locationName") String locationName,
                                                        @PathVariable(name = "departmentName") String departmentName,
                                                        @PathVariable(name = "categoryName") String categoryName,
                                                        @PathVariable(name = "subcategoryName") String subcategoryName
  )
  {
    List<GetProductDetailsDTO> response = locationService.getProductDetails(locationName,departmentName,categoryName,subcategoryName);

    APIResponse<List<GetProductDetailsDTO>> responseDTO = APIResponse
            .<List<GetProductDetailsDTO>>builder()
            .status(200)
            .data(response)
            .build();
    return new ResponseEntity<>(responseDTO, HttpStatus.OK);

  }


}
