package org.fourgeeks.gha.webclient.server.eia;


import static gwtupload.shared.UConsts.PARAM_SHOW;
import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;


/**
 * Servlet encargado de la subida de fotografias al servidor con gwtupload
 * 
 * @author lcampo
 */
public class UploadPhotographsHttpServlet extends UploadAction {

	public static final String ATTR_ARCHIVOS = "listaFotos";
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UploadPhotographsHttpServlet.class);

	Hashtable<String, String> tipoContenidoRecibido = new Hashtable<String, String>();
	Hashtable<String, File> archRecibidos = new Hashtable<String, File>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.server.UploadAction#executeAction(javax.servlet.http.
	 * HttpServletRequest, java.util.List)
	 */
	@Override
	public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
		HttpSession session = request.getSession(true);

		if (session.getAttribute(ATTR_ARCHIVOS) == null)
			session.setAttribute(ATTR_ARCHIVOS, archRecibidos);

		//@SuppressWarnings("unchecked")
		//List<ItemSimpleStr> listaArchivos = (ArrayList<ItemSimpleStr>) session.getAttribute(ATTR_ARCHIVOS);

		for (FileItem item : sessionFiles) {
			if (false == item.isFormField()) {
				try {
					String name = item.getName();
					int posPunto = name.lastIndexOf(".");
					String ext = name.substring(posPunto + 1);
				
					File arch = File.createTempFile("gha", "." + ext, new File("/tmp"));
					item.write(arch);

					// Save a list with the received files
					archRecibidos.put(item.getFieldName(), arch);
					tipoContenidoRecibido.put(item.getFieldName(), item.getContentType());
					//listaArchivos.add(new ItemSimpleStr(name, arch.getName()));
					LOG.debug("La foto "+name+" se ha subido correctamente");
				} catch (Exception e) {
					LOG.error("Error en la subida de multiples archivos fotos: " + e);
				}
			}
		}
		removeSessionFileItems(request);

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.server.UploadServlet#getUploadedFile(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void getUploadedFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombreCampo = request.getParameter(PARAM_SHOW);
		File arch = archRecibidos.get(nombreCampo);
		if (arch != null) {
			response.setContentType(tipoContenidoRecibido.get(nombreCampo));
			FileInputStream imputStream = new FileInputStream(arch);
			copyFromInputStreamToOutputStream(imputStream, response.getOutputStream());
		} else {
			renderXmlResponse(request, response, XML_ERROR_ITEM_NOT_FOUND);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gwtupload.server.UploadAction#removeItem(javax.servlet.http.
	 * HttpServletRequest, java.lang.String)
	 */
	@Override
	public void removeItem(HttpServletRequest request, String fieldName) throws UploadActionException {
		File file = archRecibidos.get(fieldName);
		archRecibidos.remove(fieldName);
		tipoContenidoRecibido.remove(fieldName);

		if (file != null) {
			file.delete();
		}
	}
}