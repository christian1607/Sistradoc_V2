package com.celmam.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * This class provides a custom AuthenticationDetail which stores the connection
 * type method (Oracle/Ldap)
 *
 */
public class MyAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    /**
     * returns the {@link WebAuthenticationDetails} according to LuxFact rule
     * 
     * @param context
     */
    @Override
    public WebAuthenticationDetails buildDetails(final HttpServletRequest context) {
        return new MyAuthenticationDetails(context);
    }

}
