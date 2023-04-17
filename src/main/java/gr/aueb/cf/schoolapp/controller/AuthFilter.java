package gr.aueb.cf.schoolapp.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebFilter(filterName = "AuthFilter", urlPatterns = {"/schoolapp/*"})
public class AuthFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        // Add initialization code here
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Check for session cookie
        Cookie[] cookies = req.getCookies();
        boolean authenticated = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    HttpSession session = req.getSession(false);

                    if ((session != null) && (session.getId().equals(cookie.getValue()))) {
                        // Session is valid, user is authenticated
                        authenticated = true;
                    } else {
                        String modifiedCookie = cookie.getValue().substring(0, cookie.getValue().length() - 6);
                        assert session != null;
                        if (session.getId().equals(modifiedCookie)) authenticated = true;
                    }
                }
            }
        }

        if (authenticated) {
            // User is authenticated, continue with request
            chain.doFilter(request, response);
        } else {
            // User is not authenticated, redirect to login page
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }

    public void destroy() {
        // Add cleanup code here
    }
}
