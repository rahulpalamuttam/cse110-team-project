package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.User;

public interface UserDAO {
    public boolean insert(User user);
}