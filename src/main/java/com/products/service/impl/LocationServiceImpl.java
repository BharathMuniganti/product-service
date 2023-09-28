package com.products.service.impl;

import com.products.dto.GetProductDetailsDTO;
import com.products.dto.LocationDTO;
import com.products.entity.Location;
import com.products.entity.proc.GetProductDetails;
import com.products.mapper.GetProductDetailsMapper;
import com.products.mapper.LocationMapper;
import com.products.repository.LocationRepository;
import com.products.service.LocationService;
import com.products.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    LocationRepository locationRepository ;

    @Autowired
    LocationMapper locationMapper ;


    @Autowired
    GetProductDetailsMapper getProductDetailsMapper ;



    @Override
    public List<LocationDTO> getAll() {
        List<Location> result = new ArrayList();
        locationRepository.findAll().forEach(result::add);
        return locationMapper.entitiesToDTOs(result);
    }

    @Override
    public List<GetProductDetailsDTO> getProductDetails(String locationName, String departmentName, String categoryName, String subcategoryName) {

        StoredProcedureQuery procedureQuery = entityManager
                .createNamedStoredProcedureQuery(Constants.PROC_getProductDetails);
        procedureQuery.setParameter("location_name", locationName);
        procedureQuery.setParameter("department_name", departmentName);
        procedureQuery.setParameter("category_name", categoryName);
        procedureQuery.setParameter("sub_cat_name", subcategoryName);

        procedureQuery.execute();
        @SuppressWarnings("unchecked")
        List<GetProductDetails> resultList = procedureQuery.getResultList();
        if(resultList==null || resultList.isEmpty())
            return new ArrayList<>();

        return getProductDetailsMapper.entitiesToDTOs(resultList);
    }

    @Override
    public LocationDTO save(LocationDTO locationDTO) {
        return locationMapper.entityToDTO(
                locationRepository.save(locationMapper.dtoToEntity(locationDTO)));
    }


}
