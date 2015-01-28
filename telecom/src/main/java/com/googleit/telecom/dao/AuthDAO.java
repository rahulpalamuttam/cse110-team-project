package com.googleit.telecom.dao;


public interface AuthDAO {
    public boolean authenticate(String email, String password);
}
