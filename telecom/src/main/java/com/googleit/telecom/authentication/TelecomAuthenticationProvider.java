package com.googleit.telecom.authentication;

import com.googleit.telecom.dao.UserDAO;
import com.googleit.telecom.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class TelecomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(TelecomAuthenticationProvider.class);

    @Autowired
    UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("authenticate method");

        String email = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        logger.info("Entered Email: {}, Password: {}", email, password);


        User user = userDAO.getUserByEmail(email);
        logger.info("Fetched user info : {}", user);

        if (user != null && user.getPassword().equals(password)) {
            logger.info("User with {} exist. Logging in", email);

            List<GrantedAuthority> roles = new ArrayList<>();

            logger.info("{} has a role {}", email, user.getRole());
                roles.add(new SimpleGrantedAuthority(user.getRole()));

            logger.info("Setting authentication token");
            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(email, password, roles);

            result.setDetails(user);
            logger.info("UsernamePasswordAuthenticationToken result : {}", result);

            return result;
        } else {
            logger.info("Log in failed");
            throw new BadCredentialsException("Bad credential");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
