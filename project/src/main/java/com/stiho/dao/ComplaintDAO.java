package com.stiho.dao;

import com.stiho.model.Complaint;
import com.stiho.model.Complaint.Status;
import java.util.HashMap;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author IS204_1
 */
@Repository
public class ComplaintDAO {

    @Autowired
    private SessionFactory sessionFactory;
    /**
     * 
     * @return current Session object
     */
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Adds complaint to database
     * @param complaint complaint to add
     */
    public void addComplaint(Complaint complaint) {
        getCurrentSession().save(complaint);
    }
    
    /**
     * TODO: Updates complaint
     * @param complaint complaint to update
     */
    public void updateComplaint(Complaint complaint) {
        getCurrentSession().update(complaint);
    }
    
    public void updatesComplaint(Complaint complaint, String commentsInt, String commentsExt, String stat) {

        Complaint complaintDet = getComplaint(complaint.getComplaintId());
        if ("OPEN".equals(stat)) {
            complaintDet.setStatus(Complaint.Status.OPEN);
        } else if ("PROCESSING".equals(stat)) {
            complaintDet.setStatus(Complaint.Status.PROCESSING);
        } else if ("HANDLED".equals(stat)) {
            complaintDet.setStatus(Complaint.Status.HANDLED);
        }
        complaintDet.setCommentsExternal(commentsExt);
        complaintDet.setCommentsInternal(commentsInt);   


        getCurrentSession().update(complaintDet);
    }    
    
    /**
     * Returns complaint that has complaintId id
     * @param id complaintId to retrieve
     * @return requested complaint object
     */
    public Complaint getComplaint(int id) {
        Complaint complaint = (Complaint) getCurrentSession().get(Complaint.class, id);
        return complaint;
    }

    /**
     * Return complaint that has trackingcode
     * @param trackingCode trackingcode to retrieve
     * @return requested complaint object
     */
    public Complaint getComplaint(String trackingCode) {
        Complaint complaint = (Complaint) getCurrentSession().createQuery("FROM Complaint WHERE trackingCode = '" + trackingCode + "'").uniqueResult();
        return complaint;
    }
    
    /**
     * Deletes complaint with complaintId id from the database
     * @param id complaintId to retrieve
     */
    public void deleteComplaint(int id) {
        Complaint complaint = getComplaint(id);
        if (complaint != null) {
            getCurrentSession().delete(complaint);
        }
    }
    /**
     * Retrieves all complaints from database
     * @return list with complaints
     */
    @SuppressWarnings("unchecked")
    public List<Complaint> getComplaints() {
        return getCurrentSession().createQuery("from Complaint").list();
    }
    
    /**
     * Retrieves all complaints that have status status.
     * @param status status to retrieve
     * @return list with complaints
     */
    @SuppressWarnings("unchecked")
    public List<Complaint> getComplaints(String status) {
        if (status.equals("open")) {
            return getCurrentSession().createQuery("from Complaint where status = 0").list();
        } else if (status.equals("unhandled")) {
            return getCurrentSession().createQuery("from Complaint where status = 1").list();           
        } else if (status.equals("handled")) {
            return getCurrentSession().createQuery("from Complaint where status = 2").list();
        } else 
        return getCurrentSession().createQuery("from Complaint").list();
    }
    
   /**
    * TODO: Returns complaints bound to a specific employee.
    * @param id employee id for complaints to retrieve
    * @return list of complaints
    */
    @SuppressWarnings("unchecked")
    public List<Complaint> getComplaints(int id) {
        //return getCurrentSession().createQuery("from Complaint").list();
        return getCurrentSession().createQuery("select comp from Complaint comp " +
                "inner join comp.customer inner join comp.customer.employee emp "
                + "where emp.employeeId=  " + id + ")").list();
    }

    /**
     * Returns all complaints from a single customer
     * @param customerId 
     * @return list of complaint objects
     */
    public List<Complaint> getCustomerComplaints(int customerId) {
        Complaint complaint = (Complaint) getCurrentSession().get(Complaint.class, customerId);
        return getCurrentSession().createQuery("from Complaint where customerId= " + customerId + ")").list();
    }
    
    /**
     * @param complaintId
     * @return 
     */
    public List<Complaint> getDetails(int complaintId) {
        Complaint complaint = (Complaint) getCurrentSession().get(Complaint.class, complaintId);
        return getCurrentSession().createQuery("from Complaint Customer where complaintId = " + complaintId + ")").list();
    }  
    
    /**
     * Stores multiple complaints into the database
     * @param complaints list of complaints to add.
     */
    public void storeAllComplaints(List<Complaint> complaints) {
        for (Complaint complaint : complaints) {
            getCurrentSession().save(complaint);
        }
    }
     // Gets all complaints of parameter given year
    public HashMap getComplaintsByMonth(int startYear) {
        //Create a map with the month as a key, the query result as the value
        HashMap<String, Integer> map = new HashMap<>();
        String[] months = {"jan", "feb", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"};
       //I'm using a array of numbers to query the months in the loop
        int[] intMonths = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        //Execute the query 12 times, one time for each month ( intevarls )
        //Put these in the hasmap
        for (int i = 0; i < months.length; i++) {
            SQLQuery query1 = sessionFactory.getCurrentSession().createSQLQuery("SELECT  count(*) FROM web_springhibernate_assignment.complaint WHERE month(dateIssued) =  " + intMonths[i]);
            map.put(months[i], Integer.parseInt(query1.list().get(0).toString()));

        }
        //return the hashmap
        return map;

    }
}
