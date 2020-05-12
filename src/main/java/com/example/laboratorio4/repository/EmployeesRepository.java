package com.example.laboratorio4.repository;

import com.example.laboratorio4.dto.EmpleadosDepartamento;
import com.example.laboratorio4.dto.EmpleadosMayoSalario;
import com.example.laboratorio4.dto.RecursosHumanos;
import com.example.laboratorio4.dto.ReporteSalarioMax;
import com.example.laboratorio4.entity.Departments;
import com.example.laboratorio4.entity.Employees;
import com.example.laboratorio4.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {


    @Query(value="SELECT e.first_name as nombre, e.last_name as apellido,jh.start_date as fechainicio, jh.end_date as fechafin, j.job_title as puesto FROM employees e\n" +
            "inner join jobs j on e.job_id = j.job_id\n" +
            "inner join job_history jh on e.employee_id = jh.employee_id\n" +
            "inner join employees man on e.manager_id = man.manager_id and e.salary > 8000\n" +
            "group by e.first_nam\n", nativeQuery=true)
    List<EmpleadosMayoSalario> empMayorSalario();

    @Query(value = "SELECT e.* FROM employees e, jobs j,departments d,locations l\n" +
            "where (e.job_id = j.job_id and e.department_id = d.department_id and  d.location_id = l.location_id) " +
            "and (e.first_name= ? or e.last_name= ? or d.department_name= ? or l.city = ? or j.job_title =?)",nativeQuery = true)
    List<Employees> obtenerEmpleados(String nombre, String apellido, String departamento, String ciudad, String trabajo);




    @Query(value="select e.employee_id as ide,e.first_name as nombre, e.last_name as apellido,j.job_title as cargo,e.salary as sueldo from employees e\n" +
            "left join departments d on e.department_id = d.department_id\n" +
            "left join jobs j on e.job_id = j.job_id and d.department_id = ?1", nativeQuery=true)
    List<EmpleadosDepartamento> empDepartamento(int id);

    @Query(value="select e.first_name as nombre, e.last_name as apellido,j.job_title as cargo,jh.start_date as fechainicio, jh.end_date as fechafin, timestampdiff(year,jh.start_date,jh.end_date) as años, \n" +
            "timestampdiff(month,jh.start_date,jh.end_date)%12 as meses from employees e, jobs j, job_history jh\n" +
            "where e.job_id = j.job_id and e.employee_id = jh.employee_id\n" +
            "group by e.first_name\n" +
            "order by tmy desc\n", nativeQuery=true)
    List<RecursosHumanos> recursosHumanos();

    @Query(value="SELECT d.department_id as ide, d.department_name as departamento ,AVG(e.salary) as promedio  FROM employees e, departments d\n" +
            "WHERE e.department_id = d.department_id\n" +
            "GROUP BY d.department_name\n" +
            "ORDER BY promedio desc\n", nativeQuery=true)
    List<ReporteSalarioMax> reporteSalario();


}
