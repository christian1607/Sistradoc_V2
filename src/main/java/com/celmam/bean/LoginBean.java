package com.celmam.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@RequestScoped
public class LoginBean {

    final Logger logger = LoggerFactory.getLogger(LoginBean.class);

    public String doLogin() throws IOException, ServletException {

        logger.info("Iniciando Autenticacion");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();
        return null;
    }

}
