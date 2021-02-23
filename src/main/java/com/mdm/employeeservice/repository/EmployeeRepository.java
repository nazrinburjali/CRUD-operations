package com.mdm.employeeservice.repository;
import com.mdm.employeeservice.domain.Employee;
import com.mdm.employeeservice.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> getEmployeeList();
    void addEmployee(Employee employee);
    Optional<Employee> getEmployeeById(long id);
    void updateEmployee(long id, Employee employee);
    void deleteEmployee(long id);
}
