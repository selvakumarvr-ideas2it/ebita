package com.ideas2it.ebita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ideas2it.ebita.entity.Employee;
import com.ideas2it.ebita.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee emp) {
        empRepo.saveEmployee(emp);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id) {
        return empRepo.getEmployee(id);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return empRepo.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmp(@PathVariable String id) {
        return empRepo.deleteEmployee(id);
    }

    @PutMapping
    public void updateEmp(@RequestBody Employee emp) {
        empRepo.updateEmp(emp);
    }

}