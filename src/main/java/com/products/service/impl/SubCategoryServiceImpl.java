package com.products.service.impl;

import com.products.dto.SubCategoryDTO;
import com.products.mapper.SubCategoryMapper;
import com.products.repository.SubCategoryRepository;
import com.products.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    SubCategoryMapper subCategoryMapper;
    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategoryDTO save(SubCategoryDTO SubCategoryDTO) {
        return subCategoryMapper.entityToDTO(
                subCategoryRepository.save(subCategoryMapper.dtoToEntity(SubCategoryDTO)));
    }



}
