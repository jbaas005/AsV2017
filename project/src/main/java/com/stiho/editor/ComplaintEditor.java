package com.stiho.editor;

import com.stiho.model.Complaint;
import com.stiho.service.ComplaintService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Property editor which transforms an ID to entity
 *
 * @author IS204_1
 */
@Component
public class ComplaintEditor extends PropertyEditorSupport {

    @Autowired
    private ComplaintService complaintService;

    /**
     * Transform text string to entity
     *
     * @param text
     */
    @Override
    public void setAsText(String text) {

        int id = (text == null || text.trim().equals("")) ? 0 : Integer.valueOf(text);

        Complaint complaint = this.complaintService.getComplaint(id);

        this.setValue(complaint);
    }

}
