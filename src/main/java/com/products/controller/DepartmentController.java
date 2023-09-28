package com.products.controller;

import com.products.dto.APIResponse;
import com.products.dto.DepartmentDTO;
import com.products.dto.LocationDTO;
import com.products.service.DepartmentService;
import com.products.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping(path = "",produces = "application/json" , consumes = "application/json")
    public ResponseEntity<APIResponse> save(@RequestBody DepartmentDTO departmentDTO) throws Exception {
        DepartmentDTO response = departmentService.save(departmentDTO);
        APIResponse<DepartmentDTO> responseDTO = APIResponse
                .<DepartmentDTO>builder()
                .status(201)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }



}
