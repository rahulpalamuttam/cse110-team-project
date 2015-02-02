package com.googleit.telecom.models.users;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {
    
    private int id;

    @NotBlank(message = "*Please enter e-mail address")
    @Email(message = "*Invalid e-mail form")
    private String email;

    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).{5,13})",
            message = "*Password must contain "
                    + "digit, "
                    + "lowercase letter, "
                    + "uppercase letter, "
                    + "special character, "
                    + "and legnth between 5-13")
    private String password;

    @NotBlank(message = "*Please enter last name")
    private String last_name;

    @NotBlank(message = "*Please enter first name")
    private String first_name;

    private String reg_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}