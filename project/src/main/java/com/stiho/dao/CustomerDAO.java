package com.stiho.dao;

import com.stiho.model.Customer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author IS204_1
 */
@Repository
public class CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 
     * @param id customerId
     * @return customer that has customerId
     */
    public Customer getCustomer(int id) {
        Customer customer = (Customer) getCurrentSession().get(Customer.class, id);
        return customer;
    }

    /**
     * 
     * @return all customers in database
     */
    @SuppressWarnings("unchecked")
    public List<Customer> getCustomer() {
        return getCurrentSession().createQuery("from Customer").list();
    } 
    
    /**
     * Stores multiple customers in database
     * @param customers customers to add.
     */
    public void storeAllCustomers(List<Customer> customers) {
        for (Customer customer : customers) {
            getCurrentSession().save(customer);
        }
    }
}
