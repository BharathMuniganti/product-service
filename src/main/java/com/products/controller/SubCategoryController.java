package com.products.controller;

import com.products.dto.APIResponse;
import com.products.dto.AuthRequest;
import com.products.dto.SignInResponse;
import com.products.dto.SubCategoryDTO;
import com.products.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sub-category")
public class SubCategoryController {

    @Autowired
    SubCategoryService subCategoryService ;

    @PostMapping(path = "",produces = "application/json" , consumes = "application/json")
    public ResponseEntity<APIResponse> save(@RequestBody SubCategoryDTO subCategoryDTO) throws Exception {
        SubCategoryDTO response = subCategoryService.save(subCategoryDTO);
        APIResponse<SubCategoryDTO> responseDTO = APIResponse
                .<SubCategoryDTO>builder()
                .status(201)
                .data(response)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }



}
