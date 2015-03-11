package com.googleit.telecom.models.users;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public abstract class User implements UserDetails {
    public static final String CUSTOMER = "Customer";
    public static final String CUSTOMER_REP = "Customer Rep";
    public static final String MARKETING_REP = "Marketing Rep";

    public static long total_user=0;

    private long id;

    //    @NotBlank(message = "*Please enter e-mail address")
//    @Email(message = "*Invalid e-mail form")
    private String email;

    //    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).{5,13})",
//            message = "*Password must contain "
//                    + "digit, "
//                    + "lowercase letter, "
//                    + "uppercase letter, "
//                    + "special character, "
//                    + "and legnth between 5-13")
    private String password;

    private String role;
    public User(){

    }

    public User(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    @Override
    public boolean equals(Object obj) {
        return ((User)obj).getEmail().equals(this.email);
    }

    @Override
    public int hashCode() {
        return getEmail().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + this.id +
                ", Email : " + this.email +
                ", Role : " + this.role;
    }
}