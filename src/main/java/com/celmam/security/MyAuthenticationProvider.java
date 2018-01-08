package com.celmam.security;

import com.celmam.dto.UsuarioDto;
import com.celmam.exception.TramiteServiceException;
import com.celmam.service.SecurityService;
import com.celmam.service.ServiceFactory;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class MyAuthenticationProvider implements AuthenticationProvider {

    final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

    private final SecurityService securityService = ServiceFactory.getSecurityService();

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        logger.info(" Autenticando Usuario");

        Authentication res = authentication;
        try {
            res = isValid(authentication);
            if (!res.isAuthenticated()) {
                logger.warn("Bad credentials");
                throw new BadCredentialsException("Bad credentials");
            }
        } catch (TramiteServiceException ex) {
            logger.error("", ex);
        }

        logger.info(" Usuario Autenticado");
        return res;
    }

    private Authentication isValid(final Authentication authentication) throws TramiteServiceException {
        Authentication res = authentication;

        if (null != authentication.getPrincipal() && null != authentication.getCredentials()) {
            UsuarioDto usuario = securityService.login(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
            System.out.println("Selected method: " + ((MyAuthenticationDetails) authentication.getDetails()).getMethod());

            if (usuario != null) {
                res = createSuccessAuthentication(authentication);

                if (null != usuario.getRoles()) {
                    List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<>();

                    usuario.getRoles().forEach(p -> {
                        SimpleGrantedAuthority e = new SimpleGrantedAuthority(p.getRol());
                        updatedAuthorities.add(e);
                    });

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                            authentication.getPrincipal(), authentication.getCredentials(), updatedAuthorities));

                    res = SecurityContextHolder.getContext().getAuthentication();
                }
            }
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
        final UsernamePasswordAuthenticationToken result
                = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                        authentication.getCredentials(),
                        authentication.getAuthorities());
        result.setDetails(authentication.getDetails());

        return result;
    }

}
