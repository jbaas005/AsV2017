/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiho.controller;

import com.stiho.model.Employee;
import com.stiho.model.Login;
import com.stiho.service.EmployeeService;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Robert
 */
@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLogout(ModelMap model) throws IOException {
        return "logout";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showForm(ModelMap model) throws IOException {
        model.addAttribute("login", new Login());
        return "login";
    }

    @Scope("session")
    @RequestMapping(value = "/login", method = POST)
    public ModelAndView postLogin(@Valid @ModelAttribute("login") Login login,
            BindingResult result, HttpServletRequest request,
            HttpServletResponse response, Locale locale) throws ServletException, IOException {
        String email = login.getEmail();
        String password = login.getPassword();
        Employee employee;
        employee = employeeService.getEmployee(email, password);
        if (result.hasErrors() || employee == null) {
            ModelAndView loginError = new ModelAndView("login");
            loginError.addObject("errors", messageSource.getMessage("login.error", null, locale));
            return loginError;
        }
        HttpSession session = request.getSession();
        session.setAttribute("id", employee.getEmployeeId());
        session.setAttribute("name", employee.getEmployeeName());
        if (employee.getManager() == null) {
            session.setAttribute("isManager", 1);
            ModelAndView managerView = new ModelAndView("redirect:/manager");
            return managerView;
        }
        ModelAndView employeeView = new ModelAndView("redirect:/employee");
        return employeeView;
    }
}
