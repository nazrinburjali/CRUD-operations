package com.mdm.employeeservice.service.impl;
import com.mdm.employeeservice.domain.Employee;
import com.mdm.employeeservice.dto.EmployeeDto;
import com.mdm.employeeservice.repository.EmployeeRepository;
import com.mdm.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    private static EmployeeDto convertEntityToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getName());
        dto.setLastName(employee.getSurname());
        dto.setSalary(employee.getSalary());
        return dto;
    }

    @Transactional
    @Override
    public void addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getFirstName());
        employee.setSurname(employeeDto.getLastName());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.addEmployee(employee);
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeDto>employeeDtoList = employeeRepository.getEmployeeList()
                .stream()
                .map(EmployeeServiceImpl::convertEntityToDto)
                .collect(Collectors.toList());
        return employeeDtoList;
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.getEmployeeById(id);
        EmployeeDto employeeDto = null;
        if (optionalEmployee.isPresent()){
           employeeDto = convertEntityToDto(optionalEmployee.get());
        }
       return  employeeDto;
    }

    @Override
    public void updatedEmployee(long id, EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.getEmployeeById(id);
        if (optionalEmployee.isPresent()){
            Employee employee = new Employee();
            employee.setId(employeeDto.getId());
            employee.setName(employeeDto.getFirstName());
            employee.setSurname(employeeDto.getLastName());
            employee.setSalary(employeeDto.getSalary());
            employeeRepository.updateEmployee(id, employee);
        }
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteEmployee(id);
    }
}
