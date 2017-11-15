package com.celmam.alfresco;

import com.celmam.exception.CMAdapterServiceException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;

public class CMAdapterServiceImpl implements CMAdapterService {

    private static final String FALLA_AL_SUBIR_EL_ARCHIVO = "Falla al subir el archivo ";

    @Override
    public void eliminarCarpeta(String nombreFolder) {
//        AlfrescoUtils.eliminarCarpeta(nombreFolder);
    }

    @Override
    public Folder crearCarpeta(String raiz, String nombreFolder) {
        return AlfrescoUtils.crearCarpeta(raiz, nombreFolder);
    }

    @Override
    public String obtenerNombreFileById(String idFile) {
        return AlfrescoUtils.obtenerNombreFileById(idFile);
    }

    @Override
    public String subirFile(File file, String nombreCarpeta) throws CMAdapterServiceException {
        try {
            return AlfrescoUtils.subirFile(file, nombreCarpeta).getId();
        } catch (FileNotFoundException e) {
            throw new CMAdapterServiceException(FALLA_AL_SUBIR_EL_ARCHIVO + file.getName(), e);
        }
    }

    @Override
    public String subirFile(File file, int tipoArchivo) throws CMAdapterServiceException {
        try {
            return AlfrescoUtils.subirFile(file, tipoArchivo).getId();
        } catch (FileNotFoundException e) {
            throw new CMAdapterServiceException(FALLA_AL_SUBIR_EL_ARCHIVO + file.getName(), e);
        }
    }

    @Override
    public String subirFileCarpetaIntermedia(File file, int tipoArchivo, String nombreCarpetaIntermedia)
            throws CMAdapterServiceException {
//        try {
//            return AlfrescoUtils.subirFileCarpetaIntermedia(file, tipoArchivo, nombreCarpetaIntermedia).getId();
//        } catch (FileNotFoundException e) {
//            throw new CMAdapterServiceException(FALLA_AL_SUBIR_EL_ARCHIVO + file.getName(), e);
//        }

        return "";
    }

    @Override
    public InputStream obtenerFile(String nombreArchivo) throws IOException {
        return AlfrescoUtils.obtenerFile(nombreArchivo);
    }

    @Override

    public String obtenerIdAlfresco(String nombreArchivo, int tipoArchivo, String nombreCarpetaIntemedia) {
//        return AlfrescoUtils.obtenerIdAlfresco(nombreArchivo, tipoArchivo, nombreCarpetaIntemedia);
        return "";
    }

    @Override
    public Document actualizarContenido(String nombreCarpeta, String nombreArchivo, byte[] byteStream,
            String mimetype) {
        return AlfrescoUtils.actualizarFile(nombreCarpeta, nombreArchivo, byteStream, mimetype);
    }

    @Override
    public void deleteFileById(String idFile) {
        AlfrescoUtils.deleteFileById(idFile);
    }

    @Override
    public String actualizarContenido(String nombreCarpeta, String nombreArchivo, String extension, byte[] byteStream,
            String nuevoNombre, String mimetype) {
        return AlfrescoUtils.actualizarFile(nombreCarpeta, nombreArchivo, extension, byteStream, nuevoNombre, mimetype)
                .getId();
    }

    @Override
    public List<String> obtenerFiles(int tipoArchivo, String nombreCarpetaIntermedia) throws IOException {
      //  return AlfrescoUtils.obtenerFiles(tipoArchivo, nombreCarpetaIntermedia);
      return null;
    }

    @Override
    public InputStream obtenerFile(String nombreArchivo, int tipoArchivo) throws IOException {
        return AlfrescoUtils.obtenerFile(nombreArchivo, tipoArchivo);
    }

    @Override
    public void vaciarCarpeta(String nombreCarpeta) {
        //AlfrescoUtils.vaciarCarpeta(nombreCarpeta);
    }

    @Override
    public String descargarArchivo(String idAlfresco, String destinationPath) throws IOException {
        return AlfrescoUtils.descargarArchivo(idAlfresco, destinationPath);
    }

    @Override
    public Document obtenerFileIntermedio(int tipoArchivo, String nombreCarpetaIntemedia, String nombreArchivo) {
       // return AlfrescoUtils.obtenerFileIntermedio(tipoArchivo, nombreCarpetaIntemedia, nombreArchivo);
       return null;
    }

    @Override
    public Document copiarDocumentoHaciaFolder(String idFile, String idFolder) {
        return AlfrescoUtils.copyDocumentoByIdFolder(idFile, idFolder);
    }

    @Override
    public Folder obtenerCarpeta(String nombreCarpeta) {
        return AlfrescoUtils.obtenerCarpeta(nombreCarpeta);
    }

}
