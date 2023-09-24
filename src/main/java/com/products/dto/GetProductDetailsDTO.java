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
public class GetProductDetailsDTO implements Serializable {

    private int sku;
    private String name;
    private int location_id;
    private String locationName;
    private int departmentId;
    private String departmentName;
    private int categoryId;
    private String categoryName;
    private Integer subCategoryId;
    private String subCatName;

}
