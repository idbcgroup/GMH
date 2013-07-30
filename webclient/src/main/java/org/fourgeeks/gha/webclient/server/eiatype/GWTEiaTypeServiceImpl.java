/**
 * 
 */
package org.fourgeeks.gha.webclient.server.eiatype;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fourgeeks.gha.domain.enu.EiaPictureStateEnum;
import org.fourgeeks.gha.domain.enu.PictureStateEnum;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;
import org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.GWTEiaTypeService;
import org.fourgeeks.gha.webclient.server.Photo;
import org.fourgeeks.gha.webclient.server.UploadPhotographsHttpServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
public class GWTEiaTypeServiceImpl extends RemoteServiceServlet implements GWTEiaTypeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(GWTEiaTypeServiceImpl.class);
	@EJB(name = "gmh.EiaTypeService")
	EiaTypeServiceRemote eiaTypeServiceRemote;
	@EJB(name = "gmh.EiaTypePictureService")
	EiaTypePictureServiceRemote eiaTypePictureServiceRemote;
	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
	private static final String HOME_DIR = System.getProperty("user.home");
	private static final String USER_NAME = System.getProperty("user.name");
	private static final String USER_DIR = System.getProperty("user.dir");
	
	private List<EiaTypePicture> pictures;

	/************************************************ MOVER ARCHIVO AL DIRECTORIO DIFINITIVO ************************************/
	private String getURLPhotograph() {
		String url = null;
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			javax.naming.Context env = (Context) ctx.lookup("java:comp/env");
			url = (String) env.lookup("LOC_FILES");
			LOG.info("URL Servidor: " + url);
			//LOG.info("url prueba: " + (String) env.lookup("jboss.server.dir"));
		} catch (NamingException e) {
			LOG.error("Imposible obtener variables de contexto ", e);
			return null;
		}
		return url;
	}

	/**
	 * Construye la ruta final de la fotografia del EIA Type
	 * 
	 * @param idEIAType
	 *            ID deL EIAType
	 * @return File con la ruta final
	 */
	private File getDirPhotograph(long idEIAType) {
		//String url = getURLPhotograph();
		File dirEndPhotograph = new File(USER_DIR+SEPARATOR+"GHA" + SEPARATOR +idEIAType
				+ SEPARATOR);
		return dirEndPhotograph;
	}

	/**
	 * Crea el directorio dada la ruta
	 * 
	 * @param dirEndPhotograph
	 *            Ruta final del archivo
	 */
	private static void createDir(File dirEndPhotograph) {
		if (!dirEndPhotograph.exists())
			dirEndPhotograph.mkdirs();
	}

	/**
	 * Mueve una fotografia temporal al directorio correspondiente y le coloca
	 * el nombre real
	 * 
	 * @param dirEndPhotograph
	 *            Directorio donde será almacenada la foto
	 * @param nombreReal
	 *            Nombre real de la foto
	 * @param photographTemp
	 *            Nombre temporal de la foto
	 * @return
	 */
	private boolean movePhotograph(File dirEndPhotograph, String nameReal, File photographTemp) {
		boolean success = photographTemp.renameTo(new File(dirEndPhotograph, nameReal));
		return success;
	}

	/**
	 * 
	 * @param eiaType
	 */
	private void locatePhotographs(EiaType eiaType) {
		HttpServletRequest threadLocalRequest = this.getThreadLocalRequest();
		HttpSession session = threadLocalRequest.getSession(true);
		Object attribute = session.getAttribute(UploadPhotographsHttpServlet.ATTR_ARCHIVOS);
		LOG.info("objeto recibido de la sesion: "+attribute.getClass());
		LOG.info("Directorio home: "+HOME_DIR);
		LOG.info("USER_NAME: "+USER_NAME);
		LOG.info("USER_DIR: "+USER_DIR);
		if (attribute == null)
			return;

		@SuppressWarnings("unchecked")
		Hashtable<String, String> fotos = (Hashtable<String, String>) attribute;
	//	getURLPhotograph() ;
		LOG.info("Si llego aqui hizo bien la conversion");
		File dirPhotograph = getDirPhotograph(eiaType.getId());
		createDir(dirPhotograph);
		pictures = new ArrayList<EiaTypePicture>();
		for (String key : fotos.keySet()) {
			EiaTypePicture picture = new EiaTypePicture();
			String nameReal = key;
			picture.setEiaType(eiaType);
			picture.setPicture(nameReal);
			picture.setDescription(dirPhotograph.getAbsolutePath()+ SEPARATOR);
			picture.setDate(new java.sql.Date(new Date().getTime()));
			picture.setPictureState(EiaPictureStateEnum.ACTIVE);
			pictures.add(picture);
			LOG.info("moviendo la fotografia : " + nameReal);
			String nameTemp = fotos.get(key);
			//File photographTemp = new File(item.get);
			File photographTemp = new File(TMP_DIR + SEPARATOR + nameTemp);
			LOG.error("desde : " + photographTemp.getAbsolutePath() + " hasta : " + dirPhotograph.getAbsolutePath()
					+ SEPARATOR + nameReal);

			boolean isMovePhoto = movePhotograph(dirPhotograph, nameReal, photographTemp);

			if (isMovePhoto) {
				if (LOG.isInfoEnabled())
					LOG.info("La fotografia fue movida");
			} else {
				LOG.error("No fue posible mover la fotografia al directorio: " + dirPhotograph.getAbsolutePath());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#save(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public EiaType save(EiaType eiaType) throws EJBException {
		return eiaTypeServiceRemote.save(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#find(long)
	 */
	@Override
	public EiaType find(long Id) throws EJBException {
		return eiaTypeServiceRemote.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#update(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public EiaType update(EiaType eiaType) throws EJBException {
		return eiaTypeServiceRemote.update(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		eiaTypeServiceRemote.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#getAll()
	 */
	@Override
	public List<EiaType> getAll() throws EJBException {
		return eiaTypeServiceRemote.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#getAll(int,
	 * int)
	 */
	@Override
	public List<EiaType> getAll(int offset, int size) throws EJBException {
		return eiaTypeServiceRemote.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType) throws EJBException {
		return eiaTypeServiceRemote.find(eiaType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.GWTEiaTypeService#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType, int offset, int size) throws EJBException {
		return eiaTypeServiceRemote.find(eiaType, offset, size);
	}
}
