package com.rk.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class SessionCheckFilter implements Filter {

    @Value("${session.timeout:30000}")
    private long SESSION_TIMEOUT;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse=(HttpServletResponse) servletResponse;

        HttpSession session =httpRequest.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        Long loginTime = (session != null) ? session.getCreationTime():null;
        long currentTime = System.currentTimeMillis();

        boolean checkSessionLogic=true;

        if(httpRequest.getServletPath().endsWith("/")
                || httpRequest.getServletPath().endsWith("/login")
                || httpRequest.getServletPath().endsWith("/sessionExp")
        ) {
            System.out.println(httpRequest.getServletPath());
            checkSessionLogic=false;
        }

        if (checkSessionLogic && username == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath()); // Redirect to login page
            return;
        }

        // Check for session timeout
        if (checkSessionLogic && (currentTime - loginTime > SESSION_TIMEOUT)) {
            System.out.println("Session Expired!");
            session.invalidate(); // Invalidate the session
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/sessionExp"); // Redirect to session expiration page
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
