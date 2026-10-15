package com.nexturn.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.nexturn.entity.Employee;
import com.nexturn.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController() {
        this.employeeService = new EmployeeService();
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{empCode}")
    public Employee getEmployeeByCode(@PathVariable int empCode) {
        return employeeService.getEmployeeByCode(empCode);
    }
    @PutMapping("/{empCode}")
    public String updateEmployee(@PathVariable int empCode,@RequestParam String empName,@RequestParam double salary) {
        return employeeService.updateEmployee(empCode, empName, salary);
    }

    @DeleteMapping("/{empCode}")
    public String deleteEmployee(@PathVariable int empCode) {
        return employeeService.deleteEmployee(empCode);
    }
}
