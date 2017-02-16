package com.stiho.editor;

import com.stiho.model.Customer;
import com.stiho.service.CustomerService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Property editor which transforms an ID to entity
 *
 * @author IS204_1
 */
@Component
public class CustomerEditor extends PropertyEditorSupport {

    @Autowired
    private CustomerService customerService;

    /**
     * Transform text string to entity
     *
     * @param text
     */
    @Override
    public void setAsText(String text) {
        
        int id = (text == null || text.trim().equals("")) ? 0 : Integer.valueOf(text);
        
        Customer customer = this.customerService.getCustomer(Integer.valueOf(id));

        this.setValue(customer);
    }

}
