package com.stiho.service;

import com.stiho.dao.ComplaintDAO;
import com.stiho.dao.CustomerDAO;
import com.stiho.model.Complaint;
import com.stiho.model.Customer;
import com.stiho.model.Employee;
import com.stiho.model.email.EmailDeadlineCustomer;
import com.stiho.model.email.EmailDeadlineEmployee;
import java.util.Date;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author IS204_1
 */
@Service
@Transactional
public class ComplaintService {

    /**
     *
     * getters and setters to get the complaint and set the complaint so it can
     * be updated.
     */
    @Autowired
    private ComplaintDAO complaintDAO;

    @Autowired
    private EmailService mail;

    public void addComplaint(Complaint complaint) {
        complaintDAO.addComplaint(complaint);
    }

    public void updateComplaint(Complaint complaint) {
        complaintDAO.updateComplaint(complaint);
    }
    
    public void updatesComplaint(Complaint complaint, String commentsInt, String commentsExt, String stat) {
        complaintDAO.updatesComplaint(complaint, commentsInt, commentsExt, stat);
    }

    public Complaint getComplaint(int id) {
        return complaintDAO.getComplaint(id);
    }

    /**
     * Overload method to get by tracking code gets complains and deletes
     * complains
     *
     * @param trackingCode
     * @return Complaint
     */
    public Complaint getComplaint(String trackingCode) {
        return complaintDAO.getComplaint(trackingCode);
    }

    public void deleteComplaint(int id) {
        complaintDAO.deleteComplaint(id);
    }

    public List<Complaint> getComplaints() {
        return complaintDAO.getComplaints();
    }

    public List<Complaint> getComplaints(String status) {
        return complaintDAO.getComplaints(status);
    }

    public List<Complaint> getComplaints(int id) {
        return complaintDAO.getComplaints(id);
    }

    public List<Complaint> getCustomerComplaints(int customerId) {
        return complaintDAO.getCustomerComplaints(customerId);
    }

    public List<Complaint> getDetails(int complaintId) {
        return complaintDAO.getDetails(complaintId);
    }

    public void storeAllComplaints(List<Complaint> complaints) {
        complaintDAO.storeAllComplaints(complaints);
    }

    /**
     *
     * checks if the time limit is exceeded and if the employee and customer
     * should be warned when time is exceeded.
     */
    @Scheduled(fixedDelay = 60000)
    public void checkComplaintTimeLimit() {
        List<Complaint> complaints = getComplaints();
        EmailDeadlineCustomer customerEmailGenerator = new EmailDeadlineCustomer();
        EmailDeadlineEmployee employeeEmailGenerator = new EmailDeadlineEmployee();
        Date dateNow = new Date();
        for (Complaint c : complaints) {
            if (c.getFullyQualifiedStatus().equals("open")) {
                if (dateNow.getTime() > c.getDeadlineDate().getTime() && c.getDeadlineNoticeSent() == false) {
                    mail.send("noreply@stiho.nl", c.getCustomer().getEmail(), "Uw klacht", customerEmailGenerator.emailDeadLine(c));
                    mail.send("noreply@stiho.nl", c.getCustomer().getEmployee().getEmployeeMail(), "Klacht deadline", 
                            employeeEmailGenerator.emailDeadLine(c),c.getCustomer().getEmployee().getManager().getEmployeeMail());
                    c.setDeadlineNoticeSent(true);
                    updateComplaint(c);
                }
            }
        }

    }
}
