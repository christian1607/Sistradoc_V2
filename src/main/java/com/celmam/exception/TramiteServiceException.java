
package com.celmam.exception;

import com.celmam.util.*;

/**
 *
 * @author Christian
 */
public class TramiteServiceException extends  Exception{

    public TramiteServiceException(String message) {
        super(message);
    }

    public TramiteServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TramiteServiceException(Throwable cause) {
        super(cause);
    }

    public TramiteServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
  
    
    
}
