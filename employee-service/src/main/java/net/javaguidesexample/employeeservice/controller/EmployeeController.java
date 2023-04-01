package net.javaguidesexample.employeeservice.controller;

import lombok.AllArgsConstructor;
import net.javaguidesexample.employeeservice.dto.APIResponseDto;
import net.javaguidesexample.employeeservice.dto.EmployeeDto;
import net.javaguidesexample.employeeservice.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {
    private EmployeeServiceImpl employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{Id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long Id){
        APIResponseDto fetchedAPIResponse = employeeService.getEmployeeById(Id);
        return ResponseEntity.ok(fetchedAPIResponse);
    }

}
