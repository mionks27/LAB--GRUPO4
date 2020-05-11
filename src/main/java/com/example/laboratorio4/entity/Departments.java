package com.example.laboratorio4.entity;

import javax.persistence.*;

@Entity
@Table(name="departments")
public class Departments {

    @Id
    private
    int department_id;
    @Column(nullable = false)
    private String departmentName;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employees managerId;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private
    Locations locacion;

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employees getManagerId() {
        return managerId;
    }

    public void setManagerId(Employees managerId) {
        this.managerId = managerId;
    }

    public Locations getLocacion() {
        return locacion;
    }

    public void setLocacion(Locations locacion) {
        this.locacion = locacion;
    }
}
