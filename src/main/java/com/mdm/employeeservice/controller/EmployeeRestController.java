package com.mdm.employeeservice.controller;
import com.mdm.employeeservice.domain.Employee;
import com.mdm.employeeservice.dto.EmployeeDto;
import com.mdm.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees/api")

public class EmployeeRestController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public List<EmployeeDto> getEmployeeList(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "id") int employeeId){
     EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
     if (employeeDto == null){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee id = " + employeeId + " id not found");
     }
     return  employeeDto;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void addEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable(name = "id") int employeeId, @RequestBody EmployeeDto employeeDto){
     employeeService.updatedEmployee(employeeId, employeeDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(name = "id") long employeeId){
       employeeService.deleteEmployee(employeeId);
    }
}
