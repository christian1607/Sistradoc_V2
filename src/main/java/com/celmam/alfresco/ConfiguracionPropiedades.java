package com.celmam.alfresco;

import java.util.ResourceBundle;

public class ConfiguracionPropiedades {

    private static final String BUNDLE_NAME = "alfresco";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private ConfiguracionPropiedades() {
    }

    public static String getString(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
