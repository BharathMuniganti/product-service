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
public class SubCategoryDTO implements Serializable {

    private Integer subCategoryId;
    private String subCatName;
    private Integer categoryId;
    private int departmentId;

}
