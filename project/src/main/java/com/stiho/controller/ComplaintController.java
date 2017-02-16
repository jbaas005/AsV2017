package com.stiho.controller;

import com.stiho.dao.ComplaintDAO;
import com.stiho.editor.ComplaintEditor;
import com.stiho.editor.CustomerEditor;
import com.stiho.model.Attachment;
import com.stiho.model.Complaint;
import com.stiho.model.Customer;
import java.util.HashMap;
import com.stiho.model.Employee;
import com.stiho.model.Review;
import com.stiho.model.email.EmailStatusHandled;
import com.stiho.model.email.EmailStatusOpen;
import com.stiho.model.email.EmailStatusProcessing;
import com.stiho.service.AttachmentUploadService;
import com.stiho.service.ComplaintService;
import com.stiho.service.EmailService;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author IS204_1
 */
@Controller
@RequestMapping(value = "/complaint")
public class ComplaintController {

    //autowired opschonen
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AttachmentUploadService attachmentUploadService;
    @Autowired
    private CustomerEditor customerEditor;
    @Autowired
    private ComplaintEditor complaintEditor;
    @Autowired
    private ComplaintDAO complaintDAO;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Customer.class, this.customerEditor);
        binder.registerCustomEditor(Complaint.class, this.complaintEditor);
    }

    @RequestMapping(method = GET)
    public String showForm(ModelMap model) throws IOException {
        model.addAttribute("complaint", new Complaint());
        return "complaint/form";
    }

    @RequestMapping(method = POST)
    public String postForm(
            @Valid @ModelAttribute("complaint") Complaint complaint,
            BindingResult result,
            @RequestParam("attachment") MultipartFile file
    ) throws Exception {

        if (!file.isEmpty()) {
            try {
                Attachment attachment = attachmentUploadService.upload(file);
                attachment.setComplaint(complaint);
                complaint.addAttachment(attachment);
            } catch (Exception e) {
                result.addError(new ObjectError("attachment", e.getMessage()));
            }
        }

        if (result.hasErrors()) {
            return "complaint/form";
        }

        complaintService.addComplaint(complaint);
        EmailStatusOpen email = new EmailStatusOpen();
        emailService.send("noreply@stiho.nl", complaint.getContactEmail(), "Uw klacht", email.emailStatusOpen(complaint));
        return "complaint/formSent";
    }

    @RequestMapping(value = "/status/{trackingCode}", method = GET)
    public ModelAndView showComplaintStatus(@PathVariable String trackingCode) throws IOException {
        ModelAndView complaintStatus = new ModelAndView("complaint/status");
        Complaint complaint = complaintService.getComplaint(trackingCode);
        Employee employee;
        employee = complaint.getCustomer().getEmployee();
        if (complaint.getStatus().getValue() == Complaint.Status.HANDLED.getValue()) {
            complaintStatus.addObject("review", complaint.getReview() != null ? complaint.getReview() : new Review());
        }
        complaintStatus.addObject("complaint", complaint);
        complaintStatus.addObject("employee", employee);
        return complaintStatus;
    }

    @RequestMapping(value = "/details/{complaintId}", method = POST)
    public ModelAndView updateComplaint(@ModelAttribute("complaint") Complaint complaint,
            @PathVariable int complaintId, @RequestParam("commentsInt") String commentsInt,
            @RequestParam("commentsExt") String commentsExt, @RequestParam("stat") String stat,
            @RequestParam("mail") String mail) {
        complaintService.updatesComplaint(complaint, commentsInt, commentsExt, stat);
        ModelAndView details = new ModelAndView("details");
        List<Complaint> complaints = complaintService.getDetails(complaintId);
        if (stat.equals("OPEN")) {
            EmailStatusOpen email = new EmailStatusOpen();
            emailService.send("noreply@stiho.nl", mail, "Uw klacht", email.emailStatusOpen(complaint));
        } else if (stat.equals("PROCESSING")) {
            EmailStatusProcessing email = new EmailStatusProcessing();
            emailService.send("noreply@stiho.nl", mail, "Uw klacht", email.emailStatusProcessing(complaint));
        } else if (stat.equals("HANDLED")) {
            EmailStatusHandled email = new EmailStatusHandled();
            emailService.send("noreply@stiho.nl", mail, "Uw klacht", email.emailStatusHandled(complaint));
        }
                details.addObject("complaint", complaints);
        String message = "Complaint was successfully edited.";
        details.addObject("message", message);
        return details;

    }
        /**
     *
     * @param status
     * @return
     */


        @RequestMapping(value = "/overview/{status}", method = GET)
    public ModelAndView renderTable(@PathVariable String status) {
        ModelAndView mv = new ModelAndView("complaint/overview");
        List<Complaint> complaints = complaintService.getComplaints(status);
        mv.addObject("complaint", complaints);
        return mv;
    }
        /**
     * klachten details view
     *
     * @param complaintId
     * @return
     */
    @RequestMapping(value = "/details/{complaintId}", method = GET)
    public ModelAndView renderComplaintDetails(@PathVariable int complaintId) {
        ModelAndView mv = new ModelAndView("complaint/details");
        List<Complaint> complaints = complaintService.getDetails(complaintId);
        mv.addObject("complaint", complaints);
        return mv;
    }
            /**
     * 
     *zet de klachten per maand in een hashmap vanuit de ComplaintDAO 
     * 
     * @return
     */
    
    public HashMap<String, Integer> renderComplaintsByYear(){
    HashMap<String, Integer> monthMap = complaintDAO.getComplaintsByMonth(2014);
       
    
    return monthMap;
    
    }}