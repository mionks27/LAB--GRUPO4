package com.example.laboratorio4.controller;

import com.example.laboratorio4.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/history")
public class HistoryController {

    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping(value = {"", "listar"})
    public String listaRecursosHumanos(Model model){
        model.addAttribute("listaHistorial", employeesRepository.recursosHumanos());
        return "history/lista";
    }



}
