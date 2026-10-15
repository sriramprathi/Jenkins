package com.nexturn.testing;

import com.nexturn.entity.Employee;
import com.nexturn.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTesting {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    private Employee createEmployee(int code, String name, double salary) {
        Employee employee = new Employee();
        employee.setEmpCode(code);
        employee.setEmpName(name);
        employee.setSalary(salary);
        return employee;
    }

    @Test
    void testAddEmployee() {
        Employee employee = createEmployee(101, "Rahul", 35000);
        String result = employeeService.addEmployee(employee);
        assertEquals("Employee added successfully", result);
        assertEquals(1, employeeService.getAllEmployees().size());
    }

    @Test
    void testAddDuplicateEmployee() {
        Employee employee = createEmployee(101, "Rahul", 35000);
        employeeService.addEmployee(employee);
        String result = employeeService.addEmployee(createEmployee(101, "Priya", 40000));
        assertEquals("Employee code already exists", result);
    }
    @Test
    void testGetAllEmployees() {
        employeeService.addEmployee(createEmployee(101, "Rahul", 35000));
        employeeService.addEmployee(createEmployee(102, "Priya", 45000));
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(2, employees.size());
    }

    @Test
    void testGetEmployeeByCode() {
        employeeService.addEmployee(createEmployee(101, "Rahul", 35000));
        Employee result = employeeService.getEmployeeByCode(101);
        assertNotNull(result);
        assertEquals("Rahul", result.getEmpName());
        assertEquals(35000, result.getSalary());
    }

    @Test
    void testGetEmployeeByInvalidCode() {
        Employee result = employeeService.getEmployeeByCode(999);
        assertNull(result);
    }

    @Test
    void testUpdateEmployee() {
        employeeService.addEmployee(createEmployee(101, "Rahul", 35000));
        String result = employeeService.updateEmployee(101, "Rahul Kumar", 40000);
        assertEquals("Employee updated successfully", result);
        Employee updated = employeeService.getEmployeeByCode(101);
        assertEquals("Rahul Kumar", updated.getEmpName());
        assertEquals(40000, updated.getSalary());
    }

    @Test
    void testUpdateNonExistingEmployee() {
        String result = employeeService.updateEmployee(999, "Unknown", 30000);
        assertEquals("Employee not found", result);
    }

    @Test
    void testDeleteEmployee() {
        employeeService.addEmployee(createEmployee(101, "Rahul", 35000));
        String result = employeeService.deleteEmployee(101);
        assertEquals("Employee deleted successfully", result);
        assertNull(employeeService.getEmployeeByCode(101));
    }

    @Test
    void testDeleteNonExistingEmployee() {
        String result = employeeService.deleteEmployee(999);
        assertEquals("Employee not found", result);
    }
}
