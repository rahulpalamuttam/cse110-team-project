package com.googleit.telecom.dao;

import com.googleit.telecom.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    private Map<String, User> users = new HashMap<>();

    @Override
    public void register(User user) {
        logger.info("registering user {}", user.getEmail());

        user.setId(++User.total_user);
        users.put(user.getEmail(), user);

        logger.info("register success on user (Email: {}, ID: {})", user.getEmail(), user.getId());
    }

    @Override
    public boolean isDuplicate(User user) {
        logger.info("is user {} duplicate ? => {}", user.getEmail(), users.containsValue(user));
        return users.containsValue(user);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("getUser who's email is {} => {}", email, users.get(email));
        return users.get(email);
    }
}