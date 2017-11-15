package com.celmam.alfresco;

import com.celmam.exception.CMAdapterServiceException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;

public interface CMAdapterService {
 
    void eliminarCarpeta(String nombreFolder);

    Folder crearCarpeta(String raiz, String nombreFolder);

    String subirFile(File file, String nombreCarpeta) throws CMAdapterServiceException;

    String subirFile(File file, int tipoArchivo) throws CMAdapterServiceException;

    String subirFileCarpetaIntermedia(File file, int tipoArchivo, String nombreCarpetaIntermedia)
            throws CMAdapterServiceException;

    InputStream obtenerFile(String nombreArchivo) throws IOException;

    InputStream obtenerFile(String nombreArchivo, int tipoArchivo) throws IOException;

    Document actualizarContenido(String nombreCarpeta, String nombreArchivo, byte[] byteStream, String mimetype);

    void deleteFileById(String idFile);

    String actualizarContenido(String nombreCarpeta, String nombreArchivo, String extension, byte[] byteStream,
            String nuevoNombre, String mimetype);

    List<String> obtenerFiles(int tipoArchivo, String nombreCarpetaIntermedia) throws IOException;

    String obtenerIdAlfresco(String nombreArchivo, int tipoArchivo, String nombreCarpetaIntemedia);

    void vaciarCarpeta(String nombreCarpeta);

    String descargarArchivo(String idAlfresco, String destinationPath) throws IOException;

    Document obtenerFileIntermedio(int tipoArchivo, String nombreCarpetaIntemedia, String nombreArchivo);

    Document copiarDocumentoHaciaFolder(String idFile,String idFolder);
    
    Folder obtenerCarpeta(String nombreCarpeta);
    
    String obtenerNombreFileById(String idFile);
}
