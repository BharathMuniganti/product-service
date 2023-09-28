package com.products.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="categories_prod_serv")
public class Category  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private Integer categoryId;

	@Column(name="category_name",nullable = false)
	private String categoryName;

	@Column(name="department_id",nullable = false)
	private int departmentId;


	//bi-directional many-to-one association to SubCategory
	@OneToMany(mappedBy="category", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<SubCategory> subCategories;


	public SubCategory addSubCategory(SubCategory subCategory) {
		getSubCategories().add(subCategory);
		subCategory.setCategory(this);

		return subCategory;
	}

	public SubCategory removeSubCategory(SubCategory subCategory) {
		getSubCategories().remove(subCategory);
		subCategory.setCategory(null);

		return subCategory;
	}

}