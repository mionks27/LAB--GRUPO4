package com.example.laboratorio4.repository;

import com.example.laboratorio4.entity.Departments;
import com.example.laboratorio4.entity.Employees;
import com.example.laboratorio4.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

    @Query(value = "SELECT e.* FROM employees e, jobs j,departments d,locations l\n" +
            "where (e.job_id = j.job_id and e.department_id = d.department_id and  d.location_id = l.location_id) " +
            "and (e.first_name= ? or e.last_name= ? or d.department_name= ? or l.city = ? or j.job_title =?)",nativeQuery = true)
    List<Employees> obtenerEmpleados(String nombre, String apellido, String departamento, String ciudad, String trabajo);





}
