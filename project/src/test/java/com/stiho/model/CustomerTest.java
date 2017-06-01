/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiho.model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kenny
 */
public class CustomerTest {
    
    private Customer instance;
    
    public CustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Customer(
                "initCompanyName",
                "initCompanyAddress",
                "initCompanyPostalCode",
                "initCompanyCity",
                "initEmail", 
                1100);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCustomerId method, of class Customer.
     */
    @Test
    public void testGetCustomerId() {
        int expResult = 1100;
        assertEquals(expResult, instance.getCustomerId());
    }

    /**
     * Test of setCustomerId method, of class Customer.
     */
    @Test
    public void testSetCustomerId() {
        int customerId = 420;
        instance.setCustomerId(customerId);
        assertEquals(customerId, instance.getCustomerId());
    }

    /**
     * Test of getCompanyName method, of class Customer.
     */
    @Test
    public void testGetCompanyName() {
        String expResult = "initCompanyName";
        String result = instance.getCompanyName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompanyName method, of class Customer.
     */
    @Test
    public void testSetCompanyName() {
        String companyName = "testCompanyName";
        instance.setCompanyName(companyName);
        assertEquals(companyName, instance.getCompanyName());
    }

    /**
     * Test of getEmail method, of class Customer.
     */ 
    @Test
    public void testGetEmail() {
        String expResult = "initEmail";
        assertEquals(expResult, instance.getEmail());
    }

    /**
     * Test of setEmail method, of class Customer.
     */
    @Test
    public void testSetEmail() {
        String email = "testEmail";
        instance.setEmail(email);
        assertEquals(email, instance.getEmail());
    }

    /**
     * Test of getCompanyAddress method, of class Customer.
     */
    @Test
    public void testGetCompanyAddress() {
        String expResult = "initCompanyAddress";
        assertEquals(expResult, instance.getCompanyAddress());
    }

    /**
     * Test of setCompanyAddress method, of class Customer.
     */
    @Test
    public void testSetCompanyAddress() {
        String companyAddress = "testCompanyAddress";
        instance.setCompanyAddress(companyAddress);
        assertEquals(companyAddress, instance.getCompanyAddress());
    }

    /**
     * Test of getCompanyPostalCode method, of class Customer.
     */
    @Test
    public void testGetCompanyPostalCode() {
        String expResult = "initCompanyPostalCode";
        assertEquals(expResult, instance.getCompanyPostalCode());
    }

    /**
     * Test of setCompanyPostalCode method, of class Customer.
     */
    @Test
    public void testSetCompanyPostalCode() {
        String companyPostalCode = "testCompanyPostalCode";
        instance.setCompanyPostalCode(companyPostalCode);
        assertEquals(companyPostalCode, instance.getCompanyPostalCode());
    }

    /**
     * Test of getCompanyCity method, of class Customer.
     */
    @Test
    public void testGetCompanyCity() {
        String expResult = "initCompanyCity";
        assertEquals(expResult, instance.getCompanyCity());
    }

    /**
     * Test of setCompanyCity method, of class Customer.
     */
    @Test
    public void testSetCompanyCity() {
        String companyCity = "testCompanyCity";
        instance.setCompanyCity(companyCity);
        assertEquals(companyCity, instance.getCompanyCity());
    }

    /**
     * Test of getEmployee method, of class Customer.
     */
    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        instance.setEmployee(employee);
        assertEquals(employee, instance.getEmployee());
    }

    /**
     * Test of setEmployee method, of class Customer.
     */
    @Test
    public void testSetEmployee() {
        Employee employee = new Employee();
        instance.setEmployee(employee);
        assertEquals(employee, instance.getEmployee());
    }

    /**
     * Test of toString method, of class Customer.
     */
    @Test
    public void testToString() {
        String expResult = "Customer{customerId=1100, companyName=initCompanyName, email=initEmail, companyAddress=initCompanyAddress, companyPostalCode=initCompanyPostalCode, companyCity=initCompanyCity}";
        assertEquals(expResult, instance.toString());

    }

    /**
     * Test of getComplaints method, of class Customer.
     */
    @Test
    public void testGetComplaints() {
        List<Complaint> expResult = null;
        List<Complaint> result = instance.getComplaints();
        assertEquals(expResult, result);
    }
    
}
