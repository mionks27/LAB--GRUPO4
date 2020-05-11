package com.example.laboratorio4.controller;
import com.example.laboratorio4.entity.Employees;
import com.example.laboratorio4.repository.DepartmentsRepository;
import com.example.laboratorio4.repository.EmployeesRepository;
import com.example.laboratorio4.repository.JobsRepository;
import com.example.laboratorio4.repository.LocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    JobsRepository jobRepository;
    @Autowired
    DepartmentsRepository departmentRepository;
    @Autowired
    LocationsRepository locationRepository;


    @GetMapping(value = {"", "/lista"})
    public String listaEmployee(Model model) {
        model.addAttribute("listaEmpleados", employeesRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("nuevo")
    public String nuevoEmployeeForm(@ModelAttribute("employee") Employees employee, Model model) {
        model.addAttribute("listaPuesto", jobRepository.findAll());
        model.addAttribute("listaJefes", employeesRepository.findAll());
        model.addAttribute("listaDepartamento", departmentRepository.findAll());
        return "employee/Frm";
    }

    @PostMapping("guardar")
    public String guardarEmployee(@ModelAttribute("employee") @Valid Employees employee, BindingResult bindingResult, RedirectAttributes attr, Model model) {

        if (bindingResult.hasErrors()){
            model.addAttribute("listaPuesto", jobRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartamento", departmentRepository.findAll());
            return "employee/Frm";
        }else{
            if (employee.getEmployee_id() == 0) {
                attr.addFlashAttribute("msg", "Empleado creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
            }
            Calendar fecha = new GregorianCalendar();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int hora = fecha.get(Calendar.HOUR_OF_DAY);
            int minuto = fecha.get(Calendar.MINUTE);
            int segundo = fecha.get(Calendar.SECOND);
            String fechaActual = año + "-" + (mes + 1) + "-" + dia + " " + hora + ":" + minuto + ":" + segundo;
            employee.setHireDate(fechaActual);
            employeesRepository.save(employee);

            return "redirect:/lista";
        }

    }


    @GetMapping("editar")
    public String editarEmployee(@ModelAttribute("employee") Employees employee,Model model, @RequestParam("id") int id) {
        Optional<Employees> optemployees = employeesRepository.findById(id);
        if (optemployees.isPresent()) {
            employee = optemployees.get();
            model.addAttribute("employee", employee);
            model.addAttribute("listaPuesto", jobRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartamento", departmentRepository.findAll());
            return "employee/Frm";
        } else {
            return "redirect:/lista";
        }
    }


    @GetMapping("borrar")
    public String borrarEmpleado(@RequestParam("id") int id, RedirectAttributes attr) {
        Optional<Employees> empleado1 = employeesRepository.findById(id);
        if (empleado1.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Borrado exitosamente");
        }


        return "redirect:/lista";
    }

    @PostMapping("/search")
    public String buscar (){

        return "";
    }

}
