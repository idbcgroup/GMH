/**
 * 
 */
package org.fourgeeks.gha.webclient.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Rmueve la imegen especificada  de la session del usuario
 * @author lcampo
 *
 */
public class RemoveImageSessionHttpServlet extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(RemoveImageSessionHttpServlet.class);
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	String nameImage = req.getParameter("nameImage");
	LOG.debug("borrando de la session la imagen "+nameImage);
	  HttpSession session = req.getSession(true);
	  if (session.getAttribute(UploadPhotographsHttpServlet.ATTR_ARCHIVOS) != null)
  		{
		  LOG.debug("obtenido atributo de la session");
		  List<String> pictures = (List<String>) session.getAttribute(UploadPhotographsHttpServlet.ATTR_ARCHIVOS);
		  pictures.remove(nameImage);
		  LOG.debug("imagen borrada con exito");
  		}

}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
