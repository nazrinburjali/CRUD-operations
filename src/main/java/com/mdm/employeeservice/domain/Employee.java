package com.mdm.employeeservice.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private long id;
    private String name;
    private String surname;
    private BigDecimal salary;
}
