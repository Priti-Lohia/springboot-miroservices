package net.javaguidesexample.employeeservice.service;

import net.javaguidesexample.employeeservice.dto.APIResponseDto;
import net.javaguidesexample.employeeservice.dto.EmployeeDto;
import net.javaguidesexample.employeeservice.entity.Employee;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long Id);
}
