package com.celmam.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * this class stores data used for authentication (mainly the authentication
 * method)
 */
public class MyAuthenticationDetails extends WebAuthenticationDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final String method;

    public MyAuthenticationDetails(final HttpServletRequest request) {
        super(request);
        method = request.getParameter("method");
    }

    public String getMethod() {
        return method;
    }

}
