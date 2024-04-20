package com.unicalsocial.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityErrorHandler implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(SecurityErrorHandler.class);

    public void commence(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException e)
            throws IOException {
        logger.error("Unauthorized error. Message - {}", e.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> Unauthorized");
    }
}
