package com.celmam.alfresco;

public class CMParametros {

    public static final String ALFRESCO_BROWSER_URL = ConfiguracionPropiedades.getString("ALFRESCO_BROWSER_URL");
    public static final String ALFRESCO_REPOSITORY_ID = ConfiguracionPropiedades.getString("ALFRESCO_REPOSITORY_ID");
    public static final String ALFRESCO_USER_ADMIN = ConfiguracionPropiedades.getString("ALFRESCO_USER_ADMIN");
    public static final String ALFRESCO_PASSWORD_ADMIN = ConfiguracionPropiedades.getString("ALFRESCO_PASSWORD_ADMIN");

    public static final String ALFRESCO_CARPETA_SOLICITUD = ConfiguracionPropiedades.getString("ALFRESCO_CARPETA_SOLICITUD");
    public static final String ALFRESCO_CARPETA_OFICIOS = ConfiguracionPropiedades.getString("ALFRESCO_CARPETA_OFICIOS");
    public static final String ALFRESCO_CARPETA_MEMORANDUM = ConfiguracionPropiedades.getString("ALFRESCO_CARPETA_MEMORANDUM");
    public static final String ALFRESCO_CARPETA_DEFAULT = ConfiguracionPropiedades.getString("ALFRESCO_CARPETA_DEFAULT");

    public static final int TIPO_DOCUMENTO_SOLICITUD = 1;
    public static final int TIPO_DOCUMENTO_OFICIO = 2;
    public static final int TIPO_DOCUMENTO_MEMORANDUM = 3;

}
