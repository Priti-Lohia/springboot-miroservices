package net.javaguidesexample.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import net.javaguidesexample.employeeservice.dto.APIResponseDto;
import net.javaguidesexample.employeeservice.dto.DepartmentDto;
import net.javaguidesexample.employeeservice.dto.EmployeeDto;
import net.javaguidesexample.employeeservice.dto.OrganizationDto;
import net.javaguidesexample.employeeservice.entity.Employee;
import net.javaguidesexample.employeeservice.repository.EmployeeRepository;
import net.javaguidesexample.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    //private RestTemplate restTemplate;
    private WebClient webClient;

    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee= employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);
        return savedEmployeeDto;
    }

    @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getEmployeeById(Long Id) {
        Employee employee = employeeRepository.findById(Id).get();

        /*
        ResponseEntity<DepartmentDto> responseEntity =restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(),
                DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();

         */

       DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
        //DepartmentDto departmentDto= apiClient.getDepartmentByCode(employee.getDepartmentCode());

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/"+employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto fetchedEmployee = modelMapper.map(employee, EmployeeDto.class);

        APIResponseDto apiResponse = new APIResponseDto(fetchedEmployee,departmentDto, organizationDto);
        return apiResponse;
    }

    public APIResponseDto getDefaultDepartment(Long Id, Exception exception) {
        Employee employee = employeeRepository.findById(Id).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department ");

        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationName("Dummy Organization");
        organizationDto.setOrganizationDescription("Dummy Organization For Default data");
        organizationDto.setOrganizationCode("DD001");

        EmployeeDto fetchedEmployee = modelMapper.map(employee, EmployeeDto.class);

        APIResponseDto apiResponse = new APIResponseDto(fetchedEmployee,departmentDto, organizationDto);
        return apiResponse;
    }
}
