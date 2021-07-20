package com.slalom.sluber.controllers;

import com.slalom.sluber.model.Employee;
import com.slalom.sluber.model.Trip;
import com.slalom.sluber.repositories.EmployeeRepository;
import com.slalom.sluber.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/sluber")
public class EmployeeController {


    private final EmployeeRepository employeeRepository;

    //with at autowired srpingboot injects the actual service into the constructor
    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity(employeeRepository.saveEmployeeToDB(employee), HttpStatus.OK);
    }

}
