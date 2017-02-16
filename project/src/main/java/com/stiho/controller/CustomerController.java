package com.stiho.controller;

import com.stiho.model.Complaint;
import com.stiho.model.Customer;
import com.stiho.service.CustomerService;
import com.stiho.service.ComplaintService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author IS204_1
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ComplaintService complaintService;

    /**
     * Autofill voor customer id veld
     * @param customerId
     * @return
     */
    @RequestMapping(value = "autofill/{customerId}", method = GET)
    public @ResponseBody
    Customer getCustomerJSON(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return customer;
    }
        /**
     * klanten geschiedenis view
     *
     * @param customerId
     * @return
     */
    @RequestMapping(value = "/history", method = GET)
    public ModelAndView renderTableCustomerHistory(@RequestParam int customerId) {
        ModelAndView mv = new ModelAndView("/customer/history");
        List<Complaint> complaints = complaintService.getCustomerComplaints(customerId);
        mv.addObject("complaint", complaints);
        return mv;
    }

}
