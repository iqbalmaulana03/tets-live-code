package com.iqbal.loginsystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqbal.loginsystem.handler.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse webResponse = ErrorResponse.builder()
                .message("Invalid Username or Password")
                .status(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build();

        String webResponseString = objectMapper.writeValueAsString(webResponse);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(webResponseString);
    }
}
