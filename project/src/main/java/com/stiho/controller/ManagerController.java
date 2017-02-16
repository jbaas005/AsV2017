package com.stiho.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.googlecode.wickedcharts.highcharts.jackson.JsonRenderer;
import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.HorizontalAlignment;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.LegendLayout;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.VerticalAlignment;
import com.googlecode.wickedcharts.highcharts.options.series.SimpleSeries;
import com.stiho.model.Complaint;
import com.stiho.model.Customer;
import com.stiho.model.Employee;
import com.stiho.service.ComplaintService;
import com.stiho.service.EmployeeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author IS204_1
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ComplaintService complaintService;

    @RequestMapping(method = GET)
    public ModelAndView renderManagerHome(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.getId();
        request.getSession().getAttribute("id");
        Integer userID = (Integer) session.getAttribute("id");
        ModelAndView managerHome = new ModelAndView("manager/mindex");
        List<Employee> employees = employeeService.getAllEmployees(userID);
        List<Complaint> complaints = new ArrayList<Complaint>();
        for (Employee e : employees) {
            complaints.addAll(complaintService.getComplaints(e.getEmployeeId()));
        }
        managerHome.addObject("complaint", complaints);
        return managerHome;
    }

    /**
     * manager statistieken view
     *
     * @return
     */
    @RequestMapping(value = "/statistics", method = GET)
    public ModelAndView renderStatistics() throws JsonGenerationException, JsonMappingException {
        ModelAndView mv = new ModelAndView("/manager/statistics");
        List<Employee> employees = employeeService.getEmployees();
        Calendar cal = Calendar.getInstance();
        Options options = new Options();
        options
                .setChartOptions(new ChartOptions()
                        .setType(SeriesType.LINE));

        options
                .setTitle(new Title("Cijfer voor werknemer"));
        options
                .setxAxis(new Axis()
                        .setCategories(Arrays
                                .asList(new String[]{"Jan", "Feb", "Mar", "Apr", "May",
                                    "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"})));
        options
                .setyAxis(new Axis()
                        .setTitle(new Title("Cijfer")));
        options
                .setLegend(new Legend()
                        .setLayout(LegendLayout.VERTICAL)
                        .setAlign(HorizontalAlignment.RIGHT)
                        .setVerticalAlign(VerticalAlignment.TOP)
                        .setX(-10)
                        .setY(100)
                        .setBorderWidth(0));
        options
                .addSeries(new SimpleSeries()
                        .setName("Jaap")
                        .setData(
                                Arrays
                                .asList(new Number[]{2, 4, 5, 3, 1, 2,
                                    4, 2, 2, 4, 5, 2})));
        options
                .addSeries(new SimpleSeries()
                        .setName("Johan")
                        .setData(
                                Arrays
                                .asList(new Number[]{1, 4, 2, 2, 2, 2,
                                    1, 2, 2, 2, 4, 2})));
        options
                .addSeries(new SimpleSeries()
                        .setName("Jozef")
                        .setData(
                                Arrays
                                .asList(new Number[]{4, 4, 5, 3, 4, 2,
                                    4, 4, 3, 2, 5, 5})));
        JsonRenderer renderer = new JsonRenderer();
        String jsonString = renderer.toJson(options);
        mv.addObject("chartJson", jsonString);
        mv.addObject("employee", employees);
        return mv;
    }

    @RequestMapping(value = "/employees", method = GET)
    public ModelAndView renderTableEmployees(HttpServletRequest request,
            HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        session.getId();
        Integer Id = (Integer) session.getAttribute("id");
        
        ModelAndView mv = new ModelAndView("/manager/employees");
        List<Employee> employees = employeeService.getAllEmployees(Id);
        mv.addObject("employee", employees);
        return mv;
    }

    @RequestMapping(value = {"/add"}, method = GET)
    public String addEmployeePage(ModelMap model, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.getId();
        Integer id = (Integer) session.getAttribute("id");
        model.addAttribute("employee", new Employee());
        model.addAttribute("Id", id);
        return "/manager/add";
    }

    @RequestMapping(value = "/add", method = POST)
    public ModelAndView addEmployee(HttpServletRequest request,
            HttpServletResponse response, @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult, ModelMap model) throws Exception {
        HttpSession session = request.getSession();
        session.getId();
        Integer Id = (Integer) session.getAttribute("id");
        employee.setManager(employeeService.getEmployee(Id));
        employeeService.addEmployee(employee);
        ModelAndView mv = new ModelAndView("/manager/employees");
        List<Employee> employees = employeeService.getAllEmployees(Id);
        mv.addObject("employee", employees);
        return mv;
    }

    /**
     * medewerkers verwijderen
     *
     * @param employeeId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/employees/delete/{employeeId}", method = GET)
    public ModelAndView deleteEmployee(@PathVariable int employeeId, HttpServletRequest request,
            HttpServletResponse response) {
        employeeService.deleteEmployee(employeeId);
        HttpSession session = request.getSession();
        session.getId();
        Integer Id = (Integer) session.getAttribute("id");
        ModelAndView mv = new ModelAndView("/manager/employees");
        List<Employee> employees = employeeService.getAllEmployees(Id);
        mv.addObject("employee", employees);
        return mv;
    }

}
