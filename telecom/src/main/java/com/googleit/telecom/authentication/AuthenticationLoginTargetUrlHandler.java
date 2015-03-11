package com.googleit.telecom.authentication;

import com.googleit.telecom.models.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthenticationLoginTargetUrlHandler implements AuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationLoginTargetUrlHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication)throws IOException, ServletException {

        logger.info("onAuthenticationSuccess method");

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        logger.info("Defined role: {}", roles);

        logger.info("Get Session");
        HttpSession session = httpServletRequest.getSession();
        logger.info("Session : {}", session);

        logger.info("Get user from {}", SecurityContextHolder.getContext().getAuthentication());
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        Map<String, Object> sessionUser = new HashMap<>();
        sessionUser.put("id", user.getId());
        sessionUser.put("email", user.getEmail());

        logger.info("User : {}", sessionUser);

        logger.info("Set session");
        session.setAttribute("user", sessionUser);
        logger.info("Session : {}", session);

        logger.info("Set status for Http response");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        logger.info("Redirecting to dashboard");
        httpServletResponse.sendRedirect("/dashboard");
    }
}
