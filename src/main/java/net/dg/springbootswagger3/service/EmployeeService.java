package net.dg.springbootswagger3.service;

import net.dg.springbootswagger3.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void addEmployee(Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    List<Employee> findByKeyword(String keyword);
}
