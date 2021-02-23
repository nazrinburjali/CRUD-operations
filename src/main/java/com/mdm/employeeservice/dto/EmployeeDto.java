package com.mdm.employeeservice.dto;
import lombok.Data;
import java.math.BigDecimal;


@Data
public class EmployeeDto {
    private long id;
    private String firstName;
    private String lastName;
    private BigDecimal salary;
}
