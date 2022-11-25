package com.fresco.springbootapp.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Properties properties = new Properties();
        properties.setProperty("status_code", "403");
        properties.setProperty("status", "Forbidden");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        OutputStream outputStream = response.getOutputStream();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, properties);
        outputStream.flush();
    }

}