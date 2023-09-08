package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.jwt.AuthTokenFilter;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final UserRepository userRepository;

    private final AuthTokenFilter authTokenFilter;

    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, AuthTokenFilter authTokenFilter, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authTokenFilter = authTokenFilter;
        this.jwtUtils = jwtUtils;
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }

    public Long getUserIdFromAuth(HttpServletRequest request) {
        try {
            String token = this.authTokenFilter.parseJwt(request);
            String email = this.jwtUtils.getUserMailFromJwtToken(token);
            User user = findByEmail(email);
            return user.getId();
        } catch (Exception e) {
            logger.error("Error while retrieve user from auth", e);
            return null;
        }
    }
}
