
package com.stiho.model.email;

import com.stiho.model.Complaint;

/**
 *
 * @author IS204-1
 */
public class EmailStatusProcessing {

    /**
     *
     * @param complaint
     * @return message
     */
    public String emailStatusProcessing(Complaint complaint) {
        String message = "<p>Geachte heer/mevrouw " + complaint.getContactName() + ",</p><br>"
                + "<p>De status van uw klacht is reeds veranderd naar '" + complaint.getFullyQualifiedStatus() + "'.<br><p>"
                + "<p>Het is mogelijk dat onze afdeling u contacteert met betrekking tot uw klacht.</p>"
                + "Houd daarom ook uw e-mail in de gaten in het geval dat u telefonisch niet bereikbaar bent."
                
                + "Uw klacht zal binnen 24 uur in behandeling worden genomen!</p><br>"
                + "<p>Natuurlijk kunt u uw klacht volgen op <a href=\"http://localhost:8084/StihoComplaints/complaint/status/"
                + complaint.getTrackingCode() + "\">Deze link</a><br>"
                + "Bedankt voor het gebruik maken van het Stiho klachtenformulier.</p><br><br>"
                + "<p>Met vriendelijke groet,</p><br><br>" + "<p>Stiho Klantenservice</p><br>020-1235920";
        return message;
    }
}
