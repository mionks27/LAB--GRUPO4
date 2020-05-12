package com.example.laboratorio4.controller;


import com.example.laboratorio4.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/Search")
public class SearchController {

    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping(value = {"","/"})
    public String indice(){
        return "Search/indice";
    }

    @GetMapping(value = {"/Salario"})
    public String listaEmpleadosMayorSalrio (Model model){
        model.addAttribute("listaEmpMayorSalario", employeesRepository.empMayorSalario());
        return "Search/lista2";
    }

    @GetMapping(value = "/Filtro2")
    public String listarReporteSalarioMaximo(Model model){
        model.addAttribute("listaSalarioMax", employeesRepository.reporteSalario());
        return "/Search/salario";
    }

    @GetMapping("/listar")
    public String listarEmpleadoDep(Model model, @RequestParam("id") int id) {
        model.addAttribute("listaEmpDep", employeesRepository.empDepartamento(id));
        return "/Search/lista3";
    }

    @PostMapping("/busqueda")
    public String buscar (){
        return "";
        //COMPLETAR
    }

}
