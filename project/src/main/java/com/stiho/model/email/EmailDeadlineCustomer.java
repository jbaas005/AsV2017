
package com.stiho.model.email;

import com.stiho.model.Complaint;

/**
 *
 * @author IS204-1
 */
public class EmailDeadlineCustomer {

    /**
     *
     * @param complaint
     * @return message
     */
    public String emailDeadLine(Complaint complaint) {
        String message = "<p>Geachte heer/mevrouw " + complaint.getContactName() + ",</p><br>"
                + "<p>U heeft bij ons een klacht ingezonden om: " + complaint.getFormattedCreatedAt() + ".<br></p>"
                + "<p>Helaas hebben wij niet binnen 24 uur op uw klacht kunnen reageren.<br>"
                + "<p>Er is een notificatie gestuurd naar een van onze medewerkers, deze zal zo spoedig mogelijk contact met u opnemen.</p>"
                + "Als uw klacht binnen 24 uur nog niet behandeld is bel dan: " + complaint.getCustomer().getEmployee().getEmployeePhone() + "</p><br><br>"
                + "<p>Excuses voor het ongemak,</p><br><br>" + "<p>Stiho Klantenservice</p><br>020-1235920";
        return message;
    }
}
