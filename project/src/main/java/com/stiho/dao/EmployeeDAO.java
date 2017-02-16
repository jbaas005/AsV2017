/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiho.dao;

import com.stiho.model.Employee;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jesse
 */
@Repository
public class EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * employee toevoegen
     * @param employee
     */
    public void addEmployee(Employee employee) {
        getCurrentSession().save(employee);
    }

    /**
     * employee verwijderen
     * @param id
     */
    public void deleteEmployee(int id) {
        Employee employee = getEmployee(id);
        if (employee != null) {
            getCurrentSession().delete(employee);
        }
    }

    /**
     * employee opvragen
     * @param id
     * @return
     */
    public Employee getEmployee(int id) {
        Employee employee = (Employee) getCurrentSession().get(Employee.class, id);
        return employee;
    }

    /**
     * alle employees opvragen
     * @return
     */
    public List<Employee> getEmployees(int id) {
        return getCurrentSession().createQuery("FROM Employee WHERE manager = '" + id + "'").list();
    }
    
    public List<Employee> getEmployees() {
        return getCurrentSession().createQuery("FROM Employee").list();
    }
    
    /**
     * employee opvragen met inloggegevens
     * @param email
     * @param password
     * @return
     */
    public Employee getEmployee(String email, String password) {
        Employee employee = (Employee) getCurrentSession().createQuery("FROM Employee WHERE employeeMail = '" + email + "' AND passWord = '" + password + "'").uniqueResult();
        return employee;
    }
    
    /**
     * alle employees opslaan
     * @param employees
     */
    public void storeAllEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            getCurrentSession().save(employee);
        }
    }

      
}
