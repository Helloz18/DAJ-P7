package com.nnk.springboot.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
   
    /**
     * This method is called when a user try to reach a URL without having a granted access.
     * return the 403 page through the /forbidden path.
     */
    @Override
    public void handle(
      HttpServletRequest request,
      HttpServletResponse response, 
      AccessDeniedException exc) throws IOException, ServletException {
        
        Authentication auth 
          = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.warn("User: " + auth.getName() 
              + " attempted to access the protected URL: "
              + request.getRequestURI());
        }

        response.sendRedirect(request.getContextPath() + "/forbidden");
    }
}
