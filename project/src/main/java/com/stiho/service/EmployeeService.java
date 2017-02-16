package com.stiho.service;

import com.stiho.dao.EmployeeDAO;
import com.stiho.model.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IS204_1
 */
@Service
@Transactional
public class EmployeeService {

    /**
     * getters and setters to store all employees and delete them.
     *
     */
    @Autowired
    private EmployeeDAO employeeDAO;

    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    public Employee getEmployee(String email, String password) {
        return employeeDAO.getEmployee(email, password);
    }

    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
    
    public List<Employee> getEmployees() {
        return employeeDAO.getEmployees();
    }
    
    public List getAllEmployees(int id) {
        return employeeDAO.getEmployees(id);
    }

    public void storeAllEmployees(List<Employee> employees) {
        employeeDAO.storeAllEmployees(employees);
    }

}
