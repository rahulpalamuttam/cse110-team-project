package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.User;

public interface UserDAO {
    public void insert(User user);
    public boolean isDuplicate(String email);
    public User getUser(String email);
    public User getUser(long id);
    public void setThreshold(long id, Double threshold);
}