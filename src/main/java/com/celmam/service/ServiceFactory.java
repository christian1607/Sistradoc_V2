package com.celmam.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Christian
 */
public class ServiceFactory {

    private static ApplicationContext context;

    private static ApplicationContext getApplicationContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("spring.xml");
        }
        return context;
    }

    public static TramiteService getTramiteService() {

        context = getApplicationContext();
        return (TramiteService) context.getBean("TramiteService");

    }

    public static CodificadorService getCodificadorService() {

        context = getApplicationContext();
        return (CodificadorService) context.getBean("CodificadorService");

    }

}
