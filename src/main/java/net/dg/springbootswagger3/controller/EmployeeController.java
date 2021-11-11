package net.dg.springbootswagger3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import net.dg.springbootswagger3.entity.Employee;
import net.dg.springbootswagger3.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    @Operation(summary = "Get all Employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "404", description = "No Employees found", content = @Content) })
    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees(){

        List<Employee> employeesList = employeeService.getAllEmployees();
        if(!employeesList.isEmpty()){
            return new ResponseEntity<>(employeesList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Create an Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = { @
                    Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content) })
    @PostMapping()
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {

        try {
            employeeService.addEmployee(employee);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted"),
            @ApiResponse(responseCode = "404", description = "Employee not found with given id", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {

        try {
            employeeService.deleteEmployee(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get an Employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found with given id", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){

        Optional<Employee> employee = employeeService.getEmployeeById(id);

        if(!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    }

    @Operation(summary = "Update an Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "No Employee exists with given id", content = @Content) })
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Map<String, Object> requestBody) {

        Optional<Employee> isEmployeePresent = employeeService.getEmployeeById(id);

        if(!isEmployeePresent.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        requestBody.put("id", id);
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employeeToBeUpdated = objectMapper.convertValue(requestBody, Employee.class);
        employeeService.addEmployee(employeeToBeUpdated);

        return new ResponseEntity<>(employeeToBeUpdated, HttpStatus.OK);
    }

    @Operation(summary = "Find Employees based on keyword")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "No Employees found", content = @Content) })
    @GetMapping(value = "/search/{keyword}")
    public ResponseEntity<List<Employee>> getEmployeeByKeyword(@PathVariable String keyword) {

        List<Employee> employeeList = employeeService.findByKeyword(keyword);
        if(employeeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


}
