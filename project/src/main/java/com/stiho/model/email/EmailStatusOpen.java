package com.stiho.model.email;

import com.stiho.model.Complaint;

/*
 *
 * @author IS204-1
 */
public class EmailStatusOpen {

    /**
     *
     * @param complaint
     * @return message
     */
    public String emailStatusOpen(Complaint complaint) {
        String message = "<p>Geachte heer/mevrouw " + complaint.getContactName() + ",</p><br>"
                + "<p>De status van uw klacht is nu '" + complaint.getFullyQualifiedStatus() + "'.<br><p>"
                + "<p>De urgentie van uw klacht is nu '" + complaint.getStringPriority() + "'.</p>"
                + "<p>Wij hebben uw klacht ontvangen om " + complaint.getCreatedAt().toString() + ".<br>"
                + "Uw klacht zal binnen 24 uur in behandeling worden genomen!</p><br>"
                + "<p>Tevens kunt u uw klacht volgen op <a href=\"http://localhost:8084/StihoComplaints/complaint/status/"
                + complaint.getTrackingCode() + "\">Deze link</a><br>"
                + "Bedankt voor het gebruik maken van het Stiho klachtenformulier.</p><br><br>"
                + "<p>Met vriendelijke groet,</p><br><br>" + "<p>Stiho Klantenservice</p><br>020-1235920";
        return message;
    }

}
