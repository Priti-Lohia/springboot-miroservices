package net.javaguidesexample.departmentservice.controller;

import lombok.AllArgsConstructor;
import net.javaguidesexample.departmentservice.dto.DepartmentDto;
import net.javaguidesexample.departmentservice.service.impl.DepartmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentServiceImpl departmentService;

    //Build save department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
       DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
       return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    //Build get department by code REST API
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto fetchedDepartment = departmentService.getDepartmentByCode(departmentCode);
        return ResponseEntity.ok(fetchedDepartment);
    }

}
