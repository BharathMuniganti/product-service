package com.products.controller;

import com.products.dto.*;
import com.products.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping(path = "",produces = "application/json" , consumes = "application/json")
    public ResponseEntity<APIResponse> save(@RequestBody LocationDTO locationDTO) throws Exception {
        LocationDTO response = locationService.save(locationDTO);
        APIResponse<LocationDTO> responseDTO = APIResponse
                .<LocationDTO>builder()
                .status(201)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(path = "")
    public ResponseEntity<APIResponse> getProductDetails()
    {
        List<LocationDTO> response = locationService.getAll();

        APIResponse<List<LocationDTO>> responseDTO = APIResponse
                .<List<LocationDTO>>builder()
                .status(200)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping(path = "/{location_id}/department")
    public ResponseEntity<APIResponse> getDepartmentsByLocationId(@PathVariable(name = "location_id") Integer locationId) {
        List<DepartmentDTO> response = locationService.findAllByLocationId(locationId);

        APIResponse<List<DepartmentDTO>> responseDTO = APIResponse
                .<List<DepartmentDTO>>builder()
                .status(200)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping(path = "/{location_id}/department/{department_id}/category")
    public ResponseEntity<APIResponse> getCategoriesByDepartmentId(@PathVariable(name = "location_id") Integer locationId
            , @PathVariable(name = "department_id") Integer departmentId) {
        List<GetCategoryDetailsDTO> response = locationService.findAllCategories(locationId,departmentId) ;

        APIResponse<List<GetCategoryDetailsDTO>> responseDTO = APIResponse
                .<List<GetCategoryDetailsDTO>>builder()
                .status(200)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping(path = "/{location_id}/department/{department_id}/category/{category_id}/subcategory")
    public ResponseEntity<APIResponse> getCategoriesByDepartmentId(@PathVariable(name = "location_id") Integer locationId
            , @PathVariable(name = "department_id") Integer departmentId
            , @PathVariable(name = "category_id") Integer categoryId  ) {
        List<GetSubCategoryDetailsDTO> response = locationService.findAllSubCategories(locationId,departmentId, categoryId) ;

        APIResponse<List<GetSubCategoryDetailsDTO>> responseDTO = APIResponse
                .<List<GetSubCategoryDetailsDTO>>builder()
                .status(200)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @GetMapping(path = "/{location_id}/department/{department_id}/category/{category_id}/subcategory/{subcategory_id}")
    public ResponseEntity<APIResponse> getCategoriesByDepartmentId(@PathVariable(name = "location_id") Integer locationId
            , @PathVariable(name = "department_id") Integer departmentId
             , @PathVariable(name = "category_id") Integer categoryId
            , @PathVariable(name = "subcategory_id") Integer subCategoryId) {
        GetSubCategoryDetailsDTO response = locationService.findAllSubCategoriesById(locationId,departmentId,categoryId,subCategoryId) ;

        APIResponse<GetSubCategoryDetailsDTO> responseDTO = APIResponse
                .<GetSubCategoryDetailsDTO>builder()
                .status(200)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
