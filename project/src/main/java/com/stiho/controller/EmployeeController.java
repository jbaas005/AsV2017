/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiho.controller;

import com.stiho.model.Complaint;
import com.stiho.model.Customer;
import com.stiho.model.Employee;
import com.stiho.service.ComplaintService;
import com.stiho.service.CustomerService;
import com.stiho.service.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jesse
 */
@Controller
public class EmployeeController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeservice;

    /**
     * klanten lijst view
     *
     * @return
     */
    @RequestMapping(value = "/list", method = GET)
    public ModelAndView renderTableCustomers() {
        ModelAndView mv = new ModelAndView("list");
        List<Customer> customers = customerService.getCustomer();
        mv.addObject("customer", customers);
        return mv;
    }
    
    @RequestMapping(value = "/employee", method = GET)
    public ModelAndView renderHomePage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getId();
        Integer userID = (Integer) session.getAttribute("id");
        Employee employee = employeeservice.getEmployee(userID);
        ModelAndView overview = new ModelAndView("overview");
        List<Complaint> complaints = complaintService.getComplaints(employee.getEmployeeId());
        overview.addObject("complaint", complaints);
        return overview;
    }

}
