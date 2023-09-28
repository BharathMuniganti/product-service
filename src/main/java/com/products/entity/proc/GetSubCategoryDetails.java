package com.products.entity.proc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class GetSubCategoryDetails implements Serializable {


    @Id
    @Column(name = "sub_category_id")
    private Integer subCategoryId;

    @Column(name = "sub_cat_name",nullable = false)
    private String subCatName;

    @Column(name="category_id")
    private int categoryId;

    @Column(name="category_name",nullable = false)
    private String categoryName;

    @Column(name="location_id")
    private int location_id;

    @Column(name="location_name",nullable = false)
    private String locationName;

    @Column(name="department_id")
    private int departmentId;

    @Column(name="department_name",nullable = false)
    private String departmentName;
}
