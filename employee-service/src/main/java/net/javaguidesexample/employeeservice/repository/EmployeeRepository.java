package net.javaguidesexample.employeeservice.repository;

import net.javaguidesexample.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
