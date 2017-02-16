package com.stiho.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author IS204_1
 */
@Entity(name = "Complaint")
public class Complaint implements Serializable {

    /**
     * @return the Status value
     */
    public static enum Status {

        OPEN(1),
        PROCESSING(2),
        HANDLED(3);

        private int value;
        private static Map<Integer, Status> map = new HashMap<Integer, Status>();

        private Status(int value) {
            this.value = value;
        }

        static {
            for (Status legEnum : Status.values()) {
                map.put(legEnum.value, legEnum);
            }
        }

        public static Status valueOf(int status) {
            return map.get(status);
        }

        public int getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int complaintId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column
    private int priority;

    @Enumerated
    @Column
    private Status status;

    @Column(columnDefinition = "TEXT")
    @Lob
    @NotEmpty
    private String description;

    @Column
    private int category;

    @Column
    @Size(max = 100)
    @NotEmpty
    private String contactName;

    @Column
    @Size(max = 100)
    @NotEmpty
    private String contactLastName;

    @Column
    @Size(max = 15)
    @NotEmpty
    private String contactPhone;

    @Column
    @NotEmpty
    @Email
    private String contactEmail;

    @Column
    private String trackingCode;

    @Column
    private Date createdAt;

    @Column
    private boolean deadlineNoticeSent;

    @Column
    private String commentsInternal;

    @Column
    private String commentsExternal;

    @OneToOne(mappedBy = "complaint")
    private Review review;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "complaint", orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<Attachment>();

    public Complaint() {
        status = Status.OPEN;
        trackingCode = UUID.randomUUID().toString();
        createdAt = new Date();
        deadlineNoticeSent = false;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public boolean getDeadlineNoticeSent() {
        return deadlineNoticeSent;
    }

    public void setDeadlineNoticeSent(boolean status) {
        this.deadlineNoticeSent = status;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPriority() {
        return priority;
    }

    public String getStringPriority() {
        switch (priority) {
            case 1:
                return "Normaal";
            case 2:
                return "Hoog";
            case 3:
                return "Spoed";
        }
        return "Normaal";
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentsInternal() {
        return commentsInternal;
    }

    public void setCommentsInternal(String commentsInternal) {
        this.commentsInternal = commentsInternal;
    }

    public String getCommentsExternal() {
        return commentsExternal;
    }

    public void setCommentsExternal(String commentsExternal) {
        this.commentsExternal = commentsExternal;
    }

    public Status getStatus() {
        return status;
    }

    public String getFullyQualifiedStatus() {
        switch (status) {
            case OPEN:
                return "open";
            case HANDLED:
                return "behandeld";
            case PROCESSING:
                return "in behandeling";
        }
        return "open";
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;

    }

    public String getStringCategory() {
        switch (category) {
            case 1:
                return "Administratie";
            case 2:
                return "Offerte";
            case 3:
                return "Medewerker";
            case 4:
                return "Levering";
            case 5:
                return "Bestelling";
            case 6:
                return "Anders";
        }
        return "";
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String code) {
        trackingCode = code;
    }

    public Date getCreatedAt() {

        return createdAt;
    }

    public String getFormattedCreatedAt() {
        SimpleDateFormat format = new SimpleDateFormat("EEE',' dd MMM 'at' HH:mm:ss");
        return format.format(createdAt);
    }

    public String getDeadline() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(createdAt);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat format = new SimpleDateFormat("EEE',' dd MMM 'at' HH:mm:ss");
        return format.format(cal.getTime());
    }

    public Date getDeadlineDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(createdAt);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return "Complaint{" + "complaintId=" + complaintId + ", customer=" + customer.toString() + ", priority=" + priority + ", status=" + status + ", description=" + description + ", category=" + category + ", contactName=" + contactName + ", contactLastName=" + contactLastName + ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail + ", trackingCode=" + trackingCode + ", createdAt=" + createdAt + '}';
    }

}
