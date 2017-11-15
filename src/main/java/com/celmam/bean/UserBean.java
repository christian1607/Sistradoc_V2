package com.celmam.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    final Logger logger = LoggerFactory.getLogger(UserBean.class);   
    private static final long serialVersionUID = 1L;
    
    private String usuario;
   
    @PostConstruct
    public void init() {
    	usuario = "Juan Perez Velasquez Curtis";
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
    
  

}
