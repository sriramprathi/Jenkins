package com.nexturn.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.nexturn.entity.Employee;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    
    public String addEmployee(Employee employee) {
        if (getEmployeeByCode(employee.getEmpCode()) != null)
            return "Employee code already exists";
        employees.add(employee);
        return "Employee added successfully";
    }
    
    public List<Employee> getAllEmployees() {
        return employees;
    }
    
    public Employee getEmployeeByCode(int empCode) {
        for (Employee employee : employees) {
            if (employee.getEmpCode() == empCode)
                return employee;
        }
        return null;
    }
    
    public String updateEmployee(int empCode, String empName, double salary) {
        Employee employee = getEmployeeByCode(empCode);
        if (employee == null) 
            return "Employee not found";
        employee.setEmpName(empName);
        employee.setSalary(salary);
        return "Employee updated successfully";
    }
    
    public String deleteEmployee(int empCode) {
        Employee employee = getEmployeeByCode(empCode);
        if (employee == null) 
            return "Employee not found";
        employees.remove(employee);
        return "Employee deleted successfully";
    }

}
