package com.stiho.controller;

import com.stiho.editor.ComplaintEditor;
import com.stiho.editor.CustomerEditor;
import com.stiho.model.Complaint;
import com.stiho.model.Customer;
import com.stiho.model.Review;
import com.stiho.service.ReviewService;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author IS204_1
 */
@Controller
@RequestMapping(value="/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private CustomerEditor customerEditor;

    @Autowired
    private ComplaintEditor complaintEditor;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Customer.class, this.customerEditor);
        binder.registerCustomEditor(Complaint.class, this.complaintEditor);
    }    
    
    /**
     * View voor complaint review
     * @param review
     * @param result
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/send", method = POST)
    public String postReview(@ModelAttribute("review") Review review, BindingResult result, HttpServletRequest request) throws Exception {
        if(result.hasErrors()) {
            return "redirect:/complaint/status/" + review.getComplaint().getTrackingCode();
        }

        review.setIp(request.getRemoteAddr());
        review.setDate(new Date());
        reviewService.addReview(review);

        return "review/sent";
    }

}
