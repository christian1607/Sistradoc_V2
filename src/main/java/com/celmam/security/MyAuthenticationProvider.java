package com.celmam.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationProvider implements AuthenticationProvider {

    final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        logger.info(" Autenticando Usuario");

        Authentication res = isValid(authentication);
        if (!res.isAuthenticated()) {
            logger.warn(" Bad credentials");
            throw new BadCredentialsException("Bad credentials");

        }
        logger.info(" Usuario Autenticado");
        return res;
    }

    private Authentication isValid(final Authentication authentication) {
        Authentication res = authentication;
        System.out.println("Selected method: " + ((MyAuthenticationDetails) authentication.getDetails()).getMethod());
        if ("Admin".equals(authentication.getPrincipal()) && "Password".equals(authentication.getCredentials())) {
            res = createSuccessAuthentication(authentication);
        }
        return res;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    protected Authentication createSuccessAuthentication(final Authentication authentication) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details
        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
        result.setDetails(authentication.getDetails());

        return result;
    }

}
