package com.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCategoryDetailsDTO implements Serializable {

    private int categoryId;
    private String categoryName;
    private int location_id;
    private String locationName;
    private int departmentId;
    private String departmentName;

}
