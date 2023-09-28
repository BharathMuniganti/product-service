package com.products.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="departments_prod_serv")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="department_id")
    private int departmentId;

    @Column(name="department_name",nullable = false)
    private String departmentName;


    @Column(name = "location_id",nullable = false)
    private Integer locationId;

    // bi-directional many-to-one association to Location
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Location location;


}
