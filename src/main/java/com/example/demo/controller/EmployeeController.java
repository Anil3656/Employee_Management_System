package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "CRUD operations for Employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/getAllEmp")
    @Operation(summary = "Get all employees")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/GetEmp")
    @Operation(summary = "Get employee by ID")
    @ApiResponse(responseCode = "200", description = "Employee found")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping("/AddEmp")
    @Operation(summary = "Create a new employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(null);
        return service.saveEmployee(employee);
    }

    @PutMapping("/updateEmp")
    @Operation(summary = "Update an existing employee")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updated) {
        Employee employee = service.getEmployeeById(id);
        employee.setName(updated.getName());
        employee.setEmail(updated.getEmail());
        employee.setDepartment(updated.getDepartment());
        return service.saveEmployee(employee);
    }

    @DeleteMapping("/deleteEmp")
    @Operation(summary = "Delete an employee")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "Employee deleted successfully!";
    }
}
