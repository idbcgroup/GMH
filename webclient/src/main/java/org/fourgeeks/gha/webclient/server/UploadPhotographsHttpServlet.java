package org.fourgeeks.gha.webclient.server;

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

import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import gwtupload.shared.UConsts;

/**
 * Sube la fotografia en un carpeta temporal y en la seccion del usuario
 * @author lcampo
 *
 */
public class UploadPhotographsHttpServlet extends UploadAction {
	public static final String ATTR_ARCHIVOS = "picture";
	  private static final long serialVersionUID = 1L;
	  private static final Logger LOG = Logger.getLogger(UploadPhotographsHttpServlet.class);
	  Hashtable<String, String> receivedContentTypes = new Hashtable<String, String>();
	  /**
	   * Maintain a list with received files and their content types. 
	   */
	  Hashtable<String, File> receivedFiles = new Hashtable<String, File>();

	  /**
	   * Override executeAction to save the received files in a custom place
	   * and delete this items from session.  
	   */
	  @Override
	  public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
	    String response = "";
	    HttpSession session = request.getSession(true);
	    if(sessionFiles.size() == 0)
    	{
	    	LOG.info("No hay imagenes en la session");
    		return "No hay imagenes en la session";
    	}
	    if (session.getAttribute(ATTR_ARCHIVOS) == null)
	    		session.setAttribute(ATTR_ARCHIVOS, new ArrayList<String>());
	  
	    List<String> photos = (List<String>) session.getAttribute(ATTR_ARCHIVOS);
	    
	    for (FileItem item : sessionFiles) {
	      if (false == item.isFormField()) {
	        try {
	          /// Create a new file based on the remote file name in the client
	          // String saveName = item.getName().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
	          // File file =new File("/tmp/" + saveName);
	          
	          /// Create a temporary file placed in /tmp (only works in unix)
	          // File file = File.createTempFile("upload-", ".bin", new File("/tmp"));
	          
	          /// Create a temporary file placed in the default system temp folder
	        	
	        	String name = item.getName();
				int posPunto = name.lastIndexOf(".");
				String ext = name.substring(posPunto + 1);

				File file = File.createTempFile("gha", "." + ext);
	          
	          item.write(file);
	          
	          /// Save a list with the received files
	          receivedFiles.put(item.getFieldName(), file);
	          receivedContentTypes.put(item.getFieldName(), item.getContentType());
	          photos.add(file.getName());
	        
	          /// Send a customized message to the client.
	          response += file.getName();

	        } catch (Exception e) {
	          throw new UploadActionException(e);
	        }
	      }
	    }
	    
	    /// Remove files from session because we have a copy of them
	    removeSessionFileItems(request);
	    
	    /// Send your customized message to the client.
	    return response;
	  }
	  
	  /**
	   * Get the content of an uploaded file.
	   */
	  @Override
	  public void getUploadedFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String fieldName = request.getParameter(UConsts.PARAM_SHOW);
	    File f = receivedFiles.get(fieldName);
	    if (f != null) {
	      response.setContentType(receivedContentTypes.get(fieldName));
	      FileInputStream is = new FileInputStream(f);
	      copyFromInputStreamToOutputStream(is, response.getOutputStream());
	    } else {
	      renderXmlResponse(request, response, XML_ERROR_ITEM_NOT_FOUND);
	   }
	  }
	  
	  /**
	   * Remove a file when the user sends a delete request.
	   */
	  @Override
	  public void removeItem(HttpServletRequest request, String fieldName)  throws UploadActionException {
	    File file = receivedFiles.get(fieldName);
	    receivedFiles.remove(fieldName);
	    receivedContentTypes.remove(fieldName);
	    if (file != null) {
	      file.delete();
	    }
	  }
	}

