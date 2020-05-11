package com.example.laboratorio4.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Entity
@Table(name="employees")
public class Employees {

    @Id
    private
    int employee_id;
    @Size(max = 20,message = "Solo se permiten 20 caracteres")
    @NotBlank(message = "No puede ser vacío")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "No puede ser vacío")
    @Size(max = 25,message = "Solo se permiten 25 caracteres")
    private String lastName;
    @Column(nullable = false)
    @Size(max = 25,message = "Solo se permiten 25 caracteres")
    @NotBlank
    private String email;
    @Size(max = 20,message = "Solo se permiten 20 caracteres")
    private String phoneNumber;
    @Column(nullable = false)
    @NotBlank
    private String hireDate;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private
    Jobs trabajo;
    @Digits(integer = 8,fraction = 2)
    @Positive(message = "Debe ser Positivo")
    private Double salary;
    @Digits(integer = 8,fraction = 2)
    @Positive(message = "Debe ser Positivo")
    private Double commissionPct;
    @Digits(integer = 6,fraction = 0)
    private Integer managerId;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private
    Departments departamento;
    @Size(min = 8, message = "Debe tener mínimo 8 caracteres")
    @NotBlank(message = "No puede ser vacío")
    private String password;
    @Digits(integer = 6,fraction = 0)
    private Integer enabled;


    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Jobs getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Jobs trabajo) {
        this.trabajo = trabajo;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Departments getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departments departamento) {
        this.departamento = departamento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
