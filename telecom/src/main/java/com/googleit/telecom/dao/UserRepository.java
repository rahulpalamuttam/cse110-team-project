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

    private Map<String, User> userRepo = new HashMap<>();

    @Override
    public void register(User user) {
        logger.info("Registering user {}", user.getEmail());

        user.setId(++User.total_user);
        userRepo.put(user.getEmail(), user);

        logger.info("Register success on user (Email: {}, ID: {})", user.getEmail(), user.getId());
    }

    @Override
    public boolean isDuplicate(User user) {
        logger.info("Is user {} duplicate ? => {}", user.getEmail(), userRepo.containsValue(user));
        return userRepo.containsValue(user);
    }

    @Override
    public User getUserByEmail(String email) {
        logger.info("Get user who's email is {} => {}", email, userRepo.get(email));
        return userRepo.get(email);
    }
}