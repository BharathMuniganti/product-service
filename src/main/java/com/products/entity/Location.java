package com.products.entity;


import com.products.entity.proc.GetProductDetails;
import com.products.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NamedStoredProcedureQuery(name = Constants.PROC_getProductDetails,
        procedureName = Constants.PROC_getProductDetails, resultClasses = {
        GetProductDetails.class },
        parameters = {
                @StoredProcedureParameter(name = "location_name", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "department_name", type = String.class, mode = ParameterMode.IN) ,
                @StoredProcedureParameter(name = "category_name", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "sub_cat_name", type = String.class, mode = ParameterMode.IN)
        })


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="locations_prod_serv")
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="location_id")
    private int locationId;

    @Column(name="location_name",nullable = false)
    private String locationName;

}
