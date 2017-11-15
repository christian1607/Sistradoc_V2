package com.celmam.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class ComponentBean implements Serializable {

    final Logger logger = LoggerFactory.getLogger(ComponentBean.class);   
    private static final long serialVersionUID = 1L;
    
    private String solicitudVersion = "1";
    private String tupa="tupa-02";
   
    @PostConstruct
    public void init() {
    	
    }

	public String getSolicitudVersion() {
		return solicitudVersion;
	}

	public void setSolicitudVersion(String solicitudVersion) {
		this.solicitudVersion = solicitudVersion;
	}

	public String getTupa() {
		return tupa;
	}

	public void setTupa(String tupa) {
		this.tupa = tupa;
	}
	
    
  

}
