package com.products.service.impl;

import com.products.dto.*;
import com.products.entity.Department;
import com.products.entity.Location;
import com.products.entity.proc.GetCategoryDetails;
import com.products.entity.proc.GetProductDetails;
import com.products.entity.proc.GetSubCategoryDetails;
import com.products.mapper.*;
import com.products.repository.DepartmentRepository;
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
    DepartmentRepository departmentRepository ;
    @Autowired
    LocationMapper locationMapper ;

    @Autowired
    DepartmentMapper departmentMapper ;

    @Autowired
    GetProductDetailsMapper getProductDetailsMapper ;

    @Autowired
    GetCategoryDetailsMapper getCategoryDetailsMapper ;

    @Autowired
    GetSubCategoryDetailsMapper getSubCategoryDetailsMapper ;


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

    @Override
    public List<DepartmentDTO> findAllByLocationId(Integer locationId) {
        List<Department> result = new ArrayList();
        departmentRepository.findAllByLocationId(locationId).forEach(result :: add);
        return departmentMapper.entitiesToDTOs(result) ;
    }

    @Override
    public List<GetCategoryDetailsDTO> findAllCategories(Integer locationId, Integer departmentId) {

        StoredProcedureQuery procedureQuery = entityManager
                .createNamedStoredProcedureQuery(Constants.PROC_getCategoryDetails_prod_serv);
        procedureQuery.setParameter("location_id", locationId);
        procedureQuery.setParameter("department_id", departmentId);


        procedureQuery.execute();
        @SuppressWarnings("unchecked")
        List<GetCategoryDetails> resultList = procedureQuery.getResultList();
        if(resultList==null || resultList.isEmpty())
            return new ArrayList<>();

        return getCategoryDetailsMapper.entitiesToDTOs(resultList);
    }

    @Override
    public List<GetSubCategoryDetailsDTO> findAllSubCategories(Integer locationId, Integer departmentId, Integer categoryId) {

        StoredProcedureQuery procedureQuery = entityManager
                .createNamedStoredProcedureQuery(Constants.PROC_getSubCategoryDetails_prod_serv);
        procedureQuery.setParameter("location_id", locationId);
        procedureQuery.setParameter("department_id", departmentId);
        procedureQuery.setParameter("category_id", categoryId);

        procedureQuery.execute();
        @SuppressWarnings("unchecked")
        List<GetSubCategoryDetails> resultList = procedureQuery.getResultList();
        if(resultList==null || resultList.isEmpty())
            return new ArrayList<>();

        return getSubCategoryDetailsMapper.entitiesToDTOs(resultList);

    }

    @Override
    public GetSubCategoryDetailsDTO findAllSubCategoriesById(Integer locationId, Integer departmentId,
                                                             Integer categoryId, Integer subCategoryId) {

        StoredProcedureQuery procedureQuery = entityManager
                .createNamedStoredProcedureQuery(Constants.PROC_getSubCategoryDetails_by_Id_prod_serv);
        procedureQuery.setParameter("location_id", locationId);
        procedureQuery.setParameter("department_id", departmentId);
        procedureQuery.setParameter("category_id", categoryId);
        procedureQuery.setParameter("sub_category_id", subCategoryId);


        procedureQuery.execute();
        @SuppressWarnings("unchecked")
        GetSubCategoryDetails result = (GetSubCategoryDetails)procedureQuery.getSingleResult();
        if(result==null )
            return null;

        return getSubCategoryDetailsMapper.entityToDTO(result) ;


    }


}
