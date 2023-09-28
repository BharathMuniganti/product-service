package com.products.service.impl;

import com.products.dto.DepartmentDTO;
import com.products.dto.LocationDTO;
import com.products.mapper.DepartmentMapper;
import com.products.mapper.LocationMapper;
import com.products.repository.DepartmentRepository;
import com.products.repository.LocationRepository;
import com.products.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        return departmentMapper.entityToDTO(
                departmentRepository.save(departmentMapper.dtoToEntity(departmentDTO)));
    }
}
