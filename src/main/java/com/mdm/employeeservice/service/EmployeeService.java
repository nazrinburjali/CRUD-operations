package com.mdm.employeeservice.service;
import com.mdm.employeeservice.dto.EmployeeDto;
import java.util.List;


public interface EmployeeService {

    List<EmployeeDto> getAll();

    EmployeeDto getEmployeeById(long id);

    void addEmployee(EmployeeDto employeeDto);

    void updatedEmployee(long id, EmployeeDto employeeDto);

    void deleteEmployee(long id);
}
