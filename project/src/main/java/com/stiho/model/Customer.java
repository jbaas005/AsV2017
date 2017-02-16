package com.stiho.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author IS204_1
 */
@Entity(name = "Customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column
    private String companyName;

//    @Column
//    private String contactName;
//    
    @Column
    private String email;

    @Column
    private String companyAddress;

    @Column
    private String companyPostalCode;

    @Column
    private String companyCity;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    @JsonIgnore
    private Employee employee;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Complaint> complaints;

    public Customer() {
    }

//    public Customer(String companyName, String contactName, String email) {
//        this.companyName = companyName;
//        this.contactName = contactName;
//        this.email = email;
//    }
    public Customer(String companyName, String companyAddress, String companyPostalCode, String companyCity, String email) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPostalCode = companyPostalCode;
        this.companyCity = companyCity;
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

//    public String getContactName() {
//        return contactName;
//    }
//
//    public void setContactName(String contactName) {
//        this.contactName = contactName;
//    }
//
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", companyName=" + companyName + ", email=" + email + ", companyAddress=" + companyAddress + ", companyPostalCode=" + companyPostalCode + ", companyCity=" + companyCity + '}';
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

}
