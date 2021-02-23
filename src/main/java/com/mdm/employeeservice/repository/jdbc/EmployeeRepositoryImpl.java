package com.mdm.employeeservice.repository.jdbc;
import com.mdm.employeeservice.domain.Employee;
import com.mdm.employeeservice.repository.EmployeeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private EmployeeRowMapper employeeRowMapper;

    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, EmployeeRowMapper employeeRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.employeeRowMapper = employeeRowMapper;
    }

    @Override
    public List<Employee> getEmployeeList() {
        String sql = "select id, name, surname, salary " +
                "from employees";

        List<Employee>employeeList = jdbcTemplate.query(sql, employeeRowMapper);
        return employeeList;

    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employees(name, surname, salary) " +
                " values(:employee_name, :employee_surname, :employee_salary)";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("employee_name", employee.getName());
        parameterSource.addValue("employee_surname", employee.getSurname());
        parameterSource.addValue("employee_salary", employee.getSalary());
        int saved = namedParameterJdbcTemplate.update(sql, parameterSource);
        if (saved > 0){
            System.out.println("employee created");

        }
        if(saved < 0){
            throw new RuntimeException("Error while adding employee");
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = Optional.empty();
        String sql = "select id, name, surname, salary " +
                "from employees " +
                "where id = :employee_id";

        MapSqlParameterSource params =  new MapSqlParameterSource("employee_id", id);
        List<Employee>employeeList = namedParameterJdbcTemplate.query(sql, params, employeeRowMapper);
        if (employeeList != null && !employeeList.isEmpty()){
            optionalEmployee = Optional.of(employeeList.get(0));
        }
        return optionalEmployee;
    }

    @Override
    public void updateEmployee(long id, Employee employee) {
        String sql = "update employees " +
                "set id=:new_employee_id, name=:employee_name, surname=:employee_surname, salary=:employee_salary "+
                "where id=:employee_id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("employee_id", id);
        parameterSource.addValue("new_employee_id", employee.getId());
        parameterSource.addValue("employee_name", employee.getName());
        parameterSource.addValue("employee_surname", employee.getSurname());
        parameterSource.addValue("employee_salary", employee.getSalary());
        int updated = namedParameterJdbcTemplate.update(sql, parameterSource);
        if (updated>0){
            System.out.println("Employee was updated");
        }else{
            throw new RuntimeException("Id = " + id + " employee was not found");
        }

    }
    @Override
    public void deleteEmployee(long id) {
        String sql = "delete from employees " +
                "where id=:employee_id";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource("employee_id", id);
        int deleted = namedParameterJdbcTemplate.update(sql,parameterSource);
        if (deleted>0){
            System.out.println("Employee was deleted");
        }else{

            throw new RuntimeException("Id = " + id + " employee was not found");
        }
    }
}
