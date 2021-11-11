package net.dg.springbootswagger3.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dg.springbootswagger3.entity.Employee;
import net.dg.springbootswagger3.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void addEmployee(Employee employee) {

        log.info("Inside addEmployee method of EmployeeService");
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {

        log.info("Inside deleteEmployee method of EmployeeService");
        employeeRepository.deleteById(id);
    }


    @Override
    public List<Employee> getAllEmployees() {

        log.info("Inside getAllEmployees method of EmployeeService");
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {

        log.info("Inside getEmployeeById method of EmployeeService");
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findByKeyword(String keyword) {

        log.info("Inside findByKeyword method of EmployeeService");
        return employeeRepository.findByKeyword(keyword);
    }
}
