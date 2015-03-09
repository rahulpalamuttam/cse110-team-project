package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.User;

public interface UserDAO {
    public void register(User user);
    public boolean isDuplicate(User user);
    public User getUserByEmail(String email);
}