package org.fourgeeks.gha.webclient.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CaptureImageHttpServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CaptureImageHttpServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		String url = req.getParameter("url");
		String name = req.getParameter("name");
		getImageVer3(resp, url, name);		
	}

	/**
	 * @param resp
	 * @param url
	 * @param name
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void getImageVer1(HttpServletResponse resp, String url, String name)
			throws FileNotFoundException, IOException {
		BufferedInputStream bInputStream = null;
		BufferedOutputStream bOutputStream = null;
		LOG.info("url: " + url + name);
		File file = new File(url + name);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileInputStream inputStream = new FileInputStream(file);

		FileReader fr = new FileReader(file);
		bInputStream = new BufferedInputStream(inputStream);
		int i;
		byte[] bytes = new byte[(int)file.length()];
		raf.read(bytes);
		LOG.info("Nombre: "+file.getName());
		//cabecera

        resp.setHeader("Content-Type","img/jpeg");
        resp.setIntHeader("Content-Length", (int) file.length());
		// inputStream.close();
        OutputStream out = resp.getOutputStream();

        out.write(bytes); 

        out.close();                     

        raf.close();

        inputStream.close();

        fr.close();
	}


	private void getImageVer2(HttpServletResponse resp, String url, String name) throws IOException
	{
		BufferedInputStream bInputStream = null;
		BufferedOutputStream bOutputStream = null;
		LOG.info("url: " + url + name);
		File file = new File(url + name);
		FileInputStream inputStream = new FileInputStream(url + name);

		bInputStream = new BufferedInputStream(inputStream);
		byte[] bytes = new byte[inputStream.available()];
		// inputStream.close();

		ServletOutputStream outStream = resp.getOutputStream();
		try {
			resp.setContentType(new MimetypesFileTypeMap().getContentType(file));
			LOG.info("ContentType: "+new MimetypesFileTypeMap().getContentType(file));
			resp.setContentLength((int) file.length());
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + name + "\"");
			bOutputStream = new BufferedOutputStream(outStream);//
			int leidos = bInputStream.read(bytes);//
			while (leidos > 0) {
				bOutputStream.write(bytes, 0, leidos);
				leidos = bInputStream.read(bytes);
			}
			if (LOG.isInfoEnabled())
				LOG.info("imagen descargada");
		} catch (Exception ex) {
			LOG.error("Error al escribir la imagen", ex);
		} finally {
			if (bInputStream != null) {
				bInputStream.close();
			}
			if (bOutputStream != null) {
				bOutputStream.close();
			}
			if (bOutputStream != null) {
				bOutputStream.flush();
				bOutputStream.close();
			}
		}
	}
	
	private void getImageVer3(HttpServletResponse resp, String url, String name) throws IOException
	{
		LOG.info("url: "+url+name);
		 		File file = new File(url+name);
		 		FileInputStream inputStream = new FileInputStream(file);
		 		byte[] bytes = new byte[inputStream.available()];
		 		inputStream.close();
		 		
		 		OutputStream outStream= resp.getOutputStream();
		 		 try {
		 	            resp.setHeader("Content-Disposition", "inline, filename="+name);
		 	            resp.setContentType(new MimetypesFileTypeMap().getContentType(file));
		 	            resp.setContentLength((int) file.length());
		 	            outStream.write(bytes);
		 	            LOG.info("respuesta procesada");
		 	        } catch (Exception ex) {
		 	            LOG.error("Error al escribir la imagen", ex);
		 	        }
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
