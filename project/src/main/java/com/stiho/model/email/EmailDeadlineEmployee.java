
package com.stiho.model.email;

import com.stiho.model.Complaint;

/**
 *
 * @author IS204-1
 */
public class EmailDeadlineEmployee {

    /**
     *
     * @param complaint
     * @return Message
     */
    public String emailDeadLine(Complaint complaint) {
        String message = "<p>Beste werknemer, <br></p>" + "<p>Dead deadline voor het reageren op klacht:"
                + complaint.getComplaintId() + " is verstreken</p>" + "<p>Er is een email verstuurd naar de klant,</p>"
                + "<p>gelieve zo spoedig mogelijk contact opnemen.<br></p>" + "<p><b>Informatie klant: </b></p>"
                + "<p>KlantID: " + complaint.getCustomer().getCustomerId() + "</p>"
                + "<p>Klant email: " + complaint.getContactEmail() + "</p>"
                + "<p>Klant telefoon: " + complaint.getContactPhone() + "<br><br></p>"
                + "<p>Dit is een automatisch gegenereerd bericht.</p>";
        return message;
    }
}
