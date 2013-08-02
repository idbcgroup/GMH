package org.fourgeeks.gha.webclient.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * Descarga la fotografia del servidor
 * @author lcampo
 *
 */
public class CaptureImageHttpServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CaptureImageHttpServlet.class);
	private static final String USER_NAME = System.getProperty("user.name");
	private String getURLPhotograph() {
		String url = null;
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			javax.naming.Context env = (Context) ctx.lookup("java:comp/env");
			url = (String) env.lookup("EIATYPE_IMAGES")+USER_NAME+"/GHA/Imagenes";
		} catch (NamingException e) {
			LOG.error("Imposible obtener variables de contexto ", e);
			return null;
		}
		return url;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getURLPhotograph();
		LOG.info(req.getPathInfo());
		String pictureName = req.getPathInfo().substring(req.getPathInfo().lastIndexOf("/"));
		String fileName = getURLPhotograph() + req.getPathInfo();	
		File file = new File(fileName);
		LOG.info("comenzando a descargar imagen");
		LOG.info("url: " + fileName);
		resp.setHeader("Content-Disposition", "inline, filename=" + pictureName);
		LOG.info("Content-Disposition:" + "inline, filename=" + pictureName);
		resp.setContentType(new MimetypesFileTypeMap().getContentType(file));
		LOG.info("content-type: " + new MimetypesFileTypeMap().getContentType(file));
		resp.setContentLength((int) file.length());
		LOG.info("content-length" + file.length());

		BufferedInputStream i = null;
		BufferedOutputStream o = null;
		int l = 0;
		try {
			i = new BufferedInputStream(new FileInputStream(file));
			o = new BufferedOutputStream(resp.getOutputStream());
			byte[] buffer = new byte[8192];

			while ((l = i.read(buffer)) > 0) {
				LOG.info("buffer at " + l);
				o.write(buffer, 0, l);
			}
		} finally {
			i.close();
			o.close();
		}

		LOG.info("Done.... flushing");
		resp.flushBuffer();
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
