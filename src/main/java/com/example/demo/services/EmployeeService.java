package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;

    public EmployeeDto getEmployeeById(Integer id){
        EmployeeEntity employeeEntity=employeeRepository.getById(id);
        return modelMapper.map(employeeEntity,EmployeeDto.class);

    }

    public EmployeeDto addNewEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);//dto to entity
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDto.class);
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class)).collect(Collectors.toList());
    }

    public boolean deleteEmployee(Integer id) {
        boolean isPresent=employeeRepository.existsById(id);
        if(isPresent)
        {
            employeeRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=employeeRepository.getById(id);
        EmployeeEntity employeeEntity1=modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setName(employeeEntity1.getName());
        employeeEntity.setDoj(employeeEntity1.getDoj());
        employeeEntity.setActive(employeeEntity1.isActive());
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }
}
