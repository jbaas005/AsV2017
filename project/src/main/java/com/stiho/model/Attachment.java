package com.stiho.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author IS204_1
 */
@Entity(name = "Attachment")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int attachmentId;

    @Column
    private String contentType;
    
    @Column
    private String name;
    
    @Column
    private String path;

    @ManyToOne
    @JoinColumn(name="complaintId")
    private Complaint complaint;

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return name;
    }
    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.name = fileName;
    }
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }
    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the complaint
     */
    public Complaint getComplaint() {
        return complaint;
    }

    /**
     * @param complaint the complaint to set
     */
    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    @Override
    public String toString() {
        return "Attachment{" + "attachmentId=" + attachmentId + ", contentType=" + contentType + ", name=" + name + ", path=" + path + ", complaint=" + complaint + '}';
    }
    
    
}
