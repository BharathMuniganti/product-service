package com.products.controller;


import com.products.dto.*;
import com.products.service.LocationService;
import com.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductsController {

  @Autowired
  LocationService locationService;


  @Autowired
  ProductService productService ;

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


  @PostMapping(path = "/product",produces = "application/json" , consumes = "application/json")
  public ResponseEntity<APIResponse> save(@RequestBody ProductDTO productDTO) throws Exception {
    ProductDTO response = productService.save(productDTO);
    APIResponse<ProductDTO> responseDTO = APIResponse
            .<ProductDTO>builder()
            .status(201)
            .data(response)
            .build();
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }






}
