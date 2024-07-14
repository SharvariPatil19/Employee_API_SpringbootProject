package com.example.demo.controllers;


import com.example.demo.dto.EmployeeDto;
import com.example.demo.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping(path="/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Integer id){
        //return new EmployeeDto(id,"Sharvari",LocalDate.of(2022,8,1),true);
        return employeeService.getEmployeeById(id);
    }
    @PostMapping
    public EmployeeDto AddEmployee(@RequestBody EmployeeDto employeeDto){
        //return new EmployeeDto(id,name,LocalDate.of(2024,7,14),false);
        return employeeService.addNewEmployee(employeeDto);
    }

    @GetMapping
    public List<EmployeeDto> getAllEmployees(){
        return  employeeService.getAllEmployees();
    }

    @DeleteMapping(path="/{id}")
    public boolean deleteEmployee(@PathVariable Integer id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping(path="/update/{id}")
    public EmployeeDto updateEmployee(@PathVariable Integer id,@RequestBody EmployeeDto employeeDto)
    {
        return employeeService.updateEmployee(id,employeeDto);
    }
}
