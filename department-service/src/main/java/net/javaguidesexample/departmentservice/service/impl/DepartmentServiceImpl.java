package net.javaguidesexample.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguidesexample.departmentservice.dto.DepartmentDto;
import net.javaguidesexample.departmentservice.entity.Department;
import net.javaguidesexample.departmentservice.repository.DepartmentRepository;
import net.javaguidesexample.departmentservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository deptRepository;
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Department savedDepartment = deptRepository.save(department);
        /*DepartmentDto savedDepartementDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );*/
        DepartmentDto savedDepartementDto = modelMapper.map(savedDepartment, DepartmentDto.class);
        return savedDepartementDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = deptRepository.findByDepartmentCode(departmentCode);
        DepartmentDto fetchedDepartmentDto = modelMapper.map(department, DepartmentDto.class);
        return fetchedDepartmentDto;
    }
}
