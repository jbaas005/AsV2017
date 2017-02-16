
package com.stiho.model.email;

import com.stiho.model.Complaint;

/**
 *
 * @author IS204-1
 */
public class EmailStatusHandled {

    /**
     *
     * @param complaint
     * @return message
     */
    public String emailStatusHandled(Complaint complaint) {
        String message = "<p>Geachte heer/mevrouw " + complaint.getContactName() + ",</p><br>"
                + "<p>De status van uw klacht is nu '" + complaint.getFullyQualifiedStatus() + "'.<br></p>"
                + "<p>Wij hopen u hierbij goed van dienst te zijn geweest.<br>"
                + "<p>Is uw klacht niet of deels opgelost? Neem dan zo spoedig mogelijk contact op met onze klantenservice.</p>"
                + "Bedankt voor het gebruik maken van het Stiho klachtenformulier.</p><br><br>"
                + "<p>Met vriendelijke groet,</p><br><br>" + "<p>Stiho Klantenservice</p><br>020-1235920";
        return message;
    }
}
