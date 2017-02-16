/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stiho.model;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 *
 * @author Robert
 */
public class Login implements Serializable {
    @Size(min=1, max=100)
    private String email;
    
    @Size(min=1, max=100)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
