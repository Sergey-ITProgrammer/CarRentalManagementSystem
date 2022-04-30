package com.system.carRentalManagementSystem.service;

import com.system.carRentalManagementSystem.model.Employee;
import com.system.carRentalManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        }

        return null;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()) {
            employeeRepository.deleteById(id);
        }
    }

    public Employee updateEmployee(Employee newEmployee, Long id) {
        return employeeRepository.findById(id).map(e -> {
            e.setName(newEmployee.getName());
            e.setEmailAddress(newEmployee.getEmailAddress());
            e.setContactNo(newEmployee.getContactNo());

            return employeeRepository.save(e);
        }).orElseGet(() -> {
            newEmployee.setId(id);

            return employeeRepository.save(newEmployee);
        });
    }
}
