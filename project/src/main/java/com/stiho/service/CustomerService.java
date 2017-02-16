package com.stiho.service;

import com.stiho.dao.CustomerDAO;
import com.stiho.model.Customer;
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
public class CustomerService {

    /**
     * getters and setters to get custumer
     *
     */
    @Autowired
    private CustomerDAO customerDAO;

    public Customer getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }

    /*
     public void addCustomer(Customer customer) {
     customerDAO.addCustomer(customer);
     }

     public void updateCustomer(Customer customer) {
     customerDAO.updateCustomer(customer);
     }


     public void deleteCustomer(int id) {
     customerDAO.deleteCustomer(id);
     }

     public List<Customer> getCustomers() {
     return customerDAO.getCustomers();
     }
     */
    public List<Customer> getCustomer() {
        return customerDAO.getCustomer();
    }

    public void storeAllCustomers(List<Customer> customers) {
        customerDAO.storeAllCustomers(customers);
    }
}
