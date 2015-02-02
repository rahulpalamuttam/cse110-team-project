package com.googleit.telecom.models.users;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/* TODO:: password reg pattern */

public class User {
    
    private int id;

    @NotBlank(message = "*Please enter e-mail address")
    @Email(message = "*Invalid e-mail form")
    private String email;

    @Size(min = 5, message="*Please enter password greater than 4")
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
        // Hash password using BCrypt algorithm. 
//        BCryptPasswordEncoder passEncryp = new BCryptPasswordEncoder();
        if(password.length() > 4)
//            this.password = passEncryp.encode(password);
            this.password = password;
        else
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

    /**
     * Changes the first and last name
     * @param firstName
     * @param lastName
     */
    public void ChangeName(String firstName, String lastName){
        this.first_name = firstName;
        this.last_name = lastName;
    }

    /**
     * User requests that they be deleted from the system.
     */
    public void RequestDelete(){
        // Code to delete user from database
    }

    /**
     * Changes the password of the user
     * The user needs to confirm their changed
     * password as well.
     * @param newPassword
     */
    public void ChangePassword(String newPassword){
        this.password = newPassword;
    }




}
