package com.celmam.alfresco;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.ObjectIdImpl;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.client.util.FileUtils;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

public class AlfrescoUtils {

    private static final String CMIS_DOCUMENT = "cmis:document";
    private static final String CMIS_FOLDER = "cmis:folder";

    public static Document actualizarFile(String nombreCarpeta, String nombreArchivo, String extension,
            byte[] byteStream, String nuevoNombre, String mimetype) {
        Session session = AlfrescoUtils.getSesion();
        String path = nombreCarpeta + nombreArchivo;
        Document doc = (Document) session.getObjectByPath(path);
        ObjectId idOfCheckedOutDocument = doc.checkOut();
        Document pwc = (Document) session.getObject(idOfCheckedOutDocument);
        ByteArrayInputStream input = new ByteArrayInputStream(byteStream);
        ContentStream contentStream = session.getObjectFactory().createContentStream(nombreArchivo, byteStream.length,
                mimetype, input);
        Map<String, String> properties = new HashMap<>();
        properties.put(PropertyIds.NAME, nuevoNombre + extension);
        ObjectId objectId = pwc.checkIn(false, properties, contentStream, "update by Crossnet");
        return (Document) session.getObject(objectId);
    }

    public static Folder crearCarpeta(String raiz, String nombreCarpeta) {

        Session session = AlfrescoUtils.getSesion();
        nombreCarpeta = formatearNombreCarpeta(nombreCarpeta);
        Folder parent = FileUtils.getFolder(raiz, session);

        try {
            return FileUtils.createFolder(parent, nombreCarpeta, CMIS_FOLDER);
        } catch (org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException e) {
            // Si la carpeta existe no se generar error
        }
        return null;
    }

    private static String formatearNombreCarpeta(String nombreCarpeta) {
        if (nombreCarpeta.startsWith("/")) {
            nombreCarpeta = nombreCarpeta.substring(1, nombreCarpeta.length());
        }
        if (nombreCarpeta.endsWith("/")) {
            nombreCarpeta = nombreCarpeta.substring(0, nombreCarpeta.length() - 1);
        }
        return nombreCarpeta;
    }

    private static String eliminarBackslash(String nombreCarpeta) {
        if (nombreCarpeta.endsWith("/")) {
            nombreCarpeta = nombreCarpeta.substring(0, nombreCarpeta.length() - 1);
        }
        return nombreCarpeta;
    }

    public static Folder obtenerCarpeta(String nombreCarpeta) {
        Folder parent;
        try {
            Session session = AlfrescoUtils.getSesion();
            parent = FileUtils.getFolder(nombreCarpeta, session);
        } catch (Exception e) {
            parent = null;
        }
        return parent;
    }

//    public static void eliminarCarpeta(String nombreCarpeta) {
//        Session session = AlfrescoUtils.getSesion();
//        Folder folder = FileUtils.getFolder(CMParametros.ALFRESCO_CARPETA_INICIAL + nombreCarpeta, session);
//        folder.deleteTree(true, UnfileObject.DELETE, true);
//    }
    public static Session getSesion() {
        SessionFactory factory = SessionFactoryImpl.newInstance();
        Map<String, String> parameters = new HashMap<>();
        parameters.put(SessionParameter.USER, CMParametros.ALFRESCO_USER_ADMIN);
        parameters.put(SessionParameter.PASSWORD, CMParametros.ALFRESCO_PASSWORD_ADMIN);
        parameters.put(SessionParameter.REPOSITORY_ID, CMParametros.ALFRESCO_REPOSITORY_ID);
        parameters.put(SessionParameter.BROWSER_URL, CMParametros.ALFRESCO_BROWSER_URL);
        parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
        return factory.createSession(parameters);
    }

    public static void deleteFileById(String idFile) {
        Document document = AlfrescoUtils.obtenerFileById(idFile);
        document.delete();
    }

    public static String obtenerNombreFileById(String idFile) {
        Document document = AlfrescoUtils.obtenerFileById(idFile);
        return document.getName();
    }

    public static String obtenerCarpetaInicial(int tipoArchivo) {
        String carpetaInicial = CMParametros.ALFRESCO_CARPETA_DEFAULT;
        if (tipoArchivo == CMParametros.TIPO_DOCUMENTO_SOLICITUD) {
            carpetaInicial = CMParametros.ALFRESCO_CARPETA_SOLICITUD;
        }
        if (tipoArchivo == CMParametros.TIPO_DOCUMENTO_OFICIO) {
            carpetaInicial = CMParametros.ALFRESCO_CARPETA_OFICIOS;
        }
        if (tipoArchivo == CMParametros.TIPO_DOCUMENTO_MEMORANDUM) {
            carpetaInicial = CMParametros.ALFRESCO_CARPETA_MEMORANDUM;
        }

        return carpetaInicial;
    }

    public static Document actualizarFile(String nombreCarpeta, String nombreArchivo, byte[] byteStream,
            String mimetype) {
        Session session = AlfrescoUtils.getSesion();
        String path = nombreCarpeta + nombreArchivo;
        Document doc = (Document) session.getObjectByPath(path);
        ObjectId idOfCheckedOutDocument = doc.checkOut();
        Document pwc = (Document) session.getObject(idOfCheckedOutDocument);
        ByteArrayInputStream input = new ByteArrayInputStream(byteStream);
        ContentStream contentStream = session.getObjectFactory().createContentStream(nombreArchivo, byteStream.length,
                mimetype, input);
        Map<String, String> properties = new HashMap<>();
        properties.put(PropertyIds.NAME, nombreArchivo);
        ObjectId objectId = pwc.checkIn(false, properties, contentStream, "update by Crossnet");
        doc = (Document) session.getObject(objectId);
        return doc;

    }

    public static InputStream obtenerFile(String nombreArchivo, int tipoArchivo) {
        Session session = AlfrescoUtils.getSesion();
        String carpetaInicial = obtenerCarpetaInicial(tipoArchivo);
        String path = carpetaInicial + nombreArchivo;
        Document doc = (Document) session.getObjectByPath(path);
        return doc.getContentStream().getStream();
    }

    public static InputStream obtenerFile(String nombreArchivo) {
        Session session = AlfrescoUtils.getSesion();
        Document doc = (Document) session.getObjectByPath(nombreArchivo);
        return doc.getContentStream().getStream();
    }

    public static InputStream obtenerFile(String carpeta, String nombreArchivo) {
        Session session = AlfrescoUtils.getSesion();
        String path = carpeta + nombreArchivo;
        Document doc = (Document) session.getObjectByPath(path);
        return doc.getContentStream().getStream();
    }

//    public static Document obtenerFileIntermedio(int tipoArchivo, String nombreCarpetaIntemedia, String nombreArchivo) {
//        Session session = AlfrescoUtils.getSesion();
//        String carpeta = AlfrescoUtils.obtenerCarpetaIntermedia(tipoArchivo, nombreCarpetaIntemedia);
//        String path = carpeta + nombreArchivo;
//        return (Document) session.getObjectByPath(path);
//    }
//    public static List<String> obtenerFiles(int tipoArchivo, String nombreCarpetaIntemedia) {
//        List<String> nombres = new ArrayList<>();
//        Session session = AlfrescoUtils.getSesion();
//        String carpeta = AlfrescoUtils.obtenerCarpetaIntermedia(tipoArchivo, nombreCarpetaIntemedia);
//        carpeta = eliminarBackslash(carpeta);
//        Folder folder = FileUtils.getFolder(carpeta, session);
//        ItemIterable<CmisObject> items = folder.getChildren();
//        for (Iterator<CmisObject> item = items.iterator(); item.hasNext();) {
//            CmisObject cmiso = item.next();
//            if (cmiso instanceof Document) {
//                nombres.add(((FileableCmisObject) cmiso).getPaths().get(0));
//            }
//        }
//        return nombres;
//    }
//    public static String obtenerIdAlfresco(String nombreArchivo, int tipoArchivo, String nombreCarpetaIntemedia) {
//        Session session = AlfrescoUtils.getSesion();
//        String carpeta = AlfrescoUtils.obtenerCarpetaIntermedia(tipoArchivo, nombreCarpetaIntemedia);
//        Document documento = (Document) FileUtils.getObject(carpeta + nombreArchivo, session);
//        return documento.getId();
//    }
    public static Document obtenerFileById(String idFile) {
        Session session = AlfrescoUtils.getSesion();
        return (Document) session.getObject(idFile);
    }

    public static Document copyDocumentoByIdFolder(String idFile, String idFolder) {
        Session session = AlfrescoUtils.getSesion();

        Document documento = (Document) session.getObject(idFile);
        return documento.copy(new ObjectIdImpl(idFolder));

    }

    public static Document subirFile(File file, int tipoArchivo) throws FileNotFoundException {
        Session session = AlfrescoUtils.getSesion();
        String carpetaInicial = obtenerCarpetaInicial(tipoArchivo);
        Folder folder = FileUtils.getFolder(carpetaInicial, session);
        String parentIdOrPath = folder.getPath();
        return crearDocumento(file, session, parentIdOrPath);
    }

    private static Document crearDocumento(File file, Session session, String parentIdOrPath)
            throws FileNotFoundException {
        Document documento;
        try {
            documento = FileUtils.createDocumentFromFile(parentIdOrPath, file, CMIS_DOCUMENT, VersioningState.MAJOR,
                    session);
        } catch (org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException e) {
            FileUtils.delete(parentIdOrPath + "/" + file.getName(), session);
            documento = FileUtils.createDocumentFromFile(parentIdOrPath, file, CMIS_DOCUMENT, VersioningState.MAJOR,
                    session);
        }
        return documento;
    }

//    public static Document subirFileCarpetaIntermedia(File file, int tipoArchivo, String nombreCarpetaIntermedia)
//            throws FileNotFoundException {
//        Session session = AlfrescoUtils.getSesion();
//        String carpetaInicial = obtenerCarpetaIntermedia(tipoArchivo, nombreCarpetaIntermedia);
//        Folder folder = FileUtils.getFolder(eliminarBackslash(carpetaInicial), session);
//        String parentIdOrPath = folder.getPath();
//        return crearDocumento(file, session, parentIdOrPath);
//    }
    public static Document subirFile(File file, String nombreCarpeta) throws FileNotFoundException {
        Session session = AlfrescoUtils.getSesion();
        Folder folder = FileUtils.getFolder(nombreCarpeta, session);
        String parentIdOrPath = folder.getPath();
        return crearDocumento(file, session, parentIdOrPath);
    }

//    public static void vaciarCarpeta(String nombreCarpeta) {
//        Session session = AlfrescoUtils.getSesion();
//        try {
//            Folder folder = FileUtils.getFolder(nombreCarpeta, session);
//            FileUtils.delete(folder.getPath(), session);
//        } catch (CmisObjectNotFoundException e) {
//            //nothing
//        }
//        String nombre = nombreCarpeta.replace(CMParametros.ALFRESCO_CARPETA_INICIAL, "");
//        crearCarpeta(CMParametros.ALFRESCO_CARPETA_INICIAL, nombre);
//    }
    public static String descargarArchivo(String idAlfresco, String destinationPath) throws IOException {
        Session session = AlfrescoUtils.getSesion();
        Document doc = (Document) session.getObject(idAlfresco);
        FileUtils.download(idAlfresco, destinationPath + "/" + doc.getName(), session);
        return doc.getName();
    }

    public static void main(String[] args) {
        try {
            AlfrescoUtils.subirFile(new File("D:\\CROSSNET\\Recibos Honorarios\\Recibo Honorario  Abril 2017.pdf"), 1);
          
        } catch (Exception e) {
            System.out.println("Error"+ e);
        }
    }

}
