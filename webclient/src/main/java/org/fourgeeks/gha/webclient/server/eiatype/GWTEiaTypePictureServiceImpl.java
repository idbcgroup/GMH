package org.fourgeeks.gha.webclient.server.eiatype;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.fourgeeks.gha.domain.enu.EiaPictureStateEnum;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;
import org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.GWTEiaTypePictureService;
import org.fourgeeks.gha.webclient.server.UploadPhotographsHttpServlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * Se encarga de administrar el EiaTypePicture (insert, update, delete, find)
 * @author lcampo
 *
 */
public class GWTEiaTypePictureServiceImpl extends RemoteServiceServlet implements GWTEiaTypePictureService {

	@EJB(name = "gmh.EiaTypePictureService")
	EiaTypePictureServiceRemote eiaTypePictureServiceRemote;
	private static final Logger LOG = Logger.getLogger(GWTEiaTypeServiceImpl.class);
	private static final String SEPARATOR = System.getProperty("file.separator");
	private static final String TMP_DIR = System.getProperty("java.io.tmpdir");
	private static final String USER_NAME = System.getProperty("user.name");
	
	private List<EiaTypePicture> pictures = null;

	/************************************************ MOVER ARCHIVO AL DIRECTORIO DIFINITIVO ************************************/
	private String getURLPhotograph() {
		String url = null;
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			javax.naming.Context env = (Context) ctx.lookup("java:comp/env");
			url = (String) env.lookup("EIATYPE_IMAGES")+USER_NAME+"/GHA/Imagenes/eiaType/";
		} catch (NamingException e) {
			LOG.error("Imposible obtener variables de contexto ", e);
			return null;
		}
		return url;
	}

	/**
	 * Construye la ruta final de la fotografia del EIA Type
	 * 
	 * 
	 * @return File con la ruta final
	 */
	private File getDirPhotograph() {
		String url = getURLPhotograph();
		File dirEndPhotograph = new File(url);
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
	 * Obtiene la fotografia de la carpeta temporal y la mueve a su ubicacion definitiva
	 * @param eiaType
	 */
	private void locatePhotographs(EiaType eiaType) {
		HttpServletRequest threadLocalRequest = this.getThreadLocalRequest();
		HttpSession session = threadLocalRequest.getSession(true);
		Object attribute = session.getAttribute(UploadPhotographsHttpServlet.ATTR_ARCHIVOS);
		List<String> listImgDelete = new ArrayList<String>();
		if (attribute == null)
		{
			LOG.info("No hay imagenes en la session");
			pictures = null;
			return;
		}
			

		LOG.info("objeto recibido de la sesion: "+attribute.getClass());
		@SuppressWarnings("unchecked")
		List<String> fotos = (List<String>) attribute;
		LOG.info("Si llego aqui hizo bien la conversion");
		File dirPhotograph = getDirPhotograph();
		createDir(dirPhotograph);
		pictures = new ArrayList<EiaTypePicture>();
		for (String name : fotos) {
			EiaTypePicture picture = new EiaTypePicture();
			picture.setEiaType(eiaType);
			picture.setPicture(name);
			picture.setDescription(dirPhotograph.getAbsolutePath()+ SEPARATOR);
			picture.setDate(new java.sql.Date(new Date().getTime()));
			picture.setPictureState(EiaPictureStateEnum.ACTIVE);
			pictures.add(picture);
			LOG.info("moviendo la fotografia : " + name);
			File photographTemp = new File(TMP_DIR + SEPARATOR + name);
			LOG.error("desde : " + photographTemp.getAbsolutePath() + " hasta : " + dirPhotograph.getAbsolutePath()
					+ SEPARATOR + name);

			boolean isMovePhoto = movePhotograph(dirPhotograph, name, photographTemp);

			if (isMovePhoto) {
				if (LOG.isInfoEnabled())
					LOG.info("La fotografia fue movida");
				listImgDelete.add(name);
			} else {
				LOG.error("No fue posible mover la fotografia al directorio: " + dirPhotograph.getAbsolutePath());
			}
		}
		for(String nameImg: listImgDelete)
		{
			if(fotos.remove(nameImg))
			{
				LOG.info("Metodo: GWTEiaTypePictureServiceImpl.locatePhotographs");
				LOG.info("imagen "+nameImg+ " borrada con exito");
			}				  
			else
				LOG.info("no se pudo borrar la imagen "+nameImg+", no se encuentra en la lista");
		}
		
	}
/**
 * Almacena las imagenes del eia type
 */
	@Override
	public void save(EiaType eiaType)
			throws EJBException {
		LOG.debug("Guardando el eiaTypePicture");
		locatePhotographs(eiaType);
		if(pictures != null){
			for (EiaTypePicture picture : pictures) {
				eiaTypePictureServiceRemote.save(picture);
			}		
		}
		
	}

	@Override
	public EiaTypePicture find(long id) throws EJBException {
		
		return eiaTypePictureServiceRemote.find(id);
	}

	@Override
	public List<EiaTypePicture> find(EiaType eiaType) throws EJBException {
		return eiaTypePictureServiceRemote.find(eiaType);
	}

	/**
	 * actualiza las imagenes del eia type especificado
	 */
	@Override
	public boolean update(EiaType eiaType, int noDeletePicture[]) throws EJBException {
		LOG.info("actualizando las imagenes de un eia type");
		List<EiaTypePicture> listEiaTypePictures = eiaTypePictureServiceRemote.find(eiaType);
		
		for(EiaTypePicture eiaTypePicture: listEiaTypePictures)
		{
			boolean deleteEiaTyPict = false;
			/**
			 * busca las imagenes que se van a eliminar
			 */
			for(int i = 0; i< noDeletePicture.length; i++){	
				LOG.info("buscando las imagenes a eliminar");
				
				if(eiaTypePicture.getId() != noDeletePicture[i]){
					LOG.info(eiaTypePicture.getId()+"!="+noDeletePicture[i]);
					deleteEiaTyPict = true;
				}
					
				else {
					LOG.info(eiaTypePicture.getId()+"=="+noDeletePicture[i]);
					deleteEiaTyPict = false;
					break;
				}
					
			}
			
			if(deleteEiaTyPict){
				String url = getURLPhotograph();
				File imgDeleted = new File(url+eiaTypePicture.getPicture());
				LOG.info("eliminar imagen: "+deleteEiaTyPict);
				LOG.info("Borrado eia type numero: "+eiaTypePicture.getId());
				delete(eiaTypePicture.getId());	
				if(imgDeleted.delete())
					LOG.info("la imagen "+eiaTypePicture.getPicture()+" ha sido eliminada con exito");
				else
					LOG.info("la imagen "+eiaTypePicture.getPicture()+" no fue eliminada");
			}					
		}
		
		locatePhotographs(eiaType);
		if(pictures != null){
			LOG.info("Almacenando imagenes en la base de datos");
			for(EiaTypePicture eiaTypePicture: pictures)
			{
				eiaTypePictureServiceRemote.save(eiaTypePicture);
				LOG.info("imagen "+eiaTypePicture.getPicture()+" ha sido almacenada con exito");
			}
			LOG.info("la actualización se ha realizado con exito");
		}
		
		return true;
	}

	@Override
	public void delete(long id) throws EJBException {
		eiaTypePictureServiceRemote.delete(id);
		
	}

	@Override
	public boolean update(EiaTypePicture eiaTypePicture) throws EJBException {
		return eiaTypePictureServiceRemote.update(eiaTypePicture);
	}
/**
 * Elimina la fotografia de la session del usuario
 */
	@Override
	public void deletePictureFromSession(String namePicture) throws Exception {
		HttpServletRequest threadLocalRequest = this.getThreadLocalRequest();
		HttpSession session = threadLocalRequest.getSession(true);
		
		
		  if (session.getAttribute(UploadPhotographsHttpServlet.ATTR_ARCHIVOS) != null)
	  		{
			  LOG.info("borrando de la session la imagen "+namePicture);
			  LOG.info("obteniendo atributo de la session");
			  List<String> pictures = (List<String>) session.getAttribute(UploadPhotographsHttpServlet.ATTR_ARCHIVOS);
			  if(pictures.remove(namePicture))
				  LOG.info("imagen "+namePicture+ " borrada con exito");
			  else
				  LOG.info("no se pudo borrar la imagen, no se encuentra en la lista");
	  		} else
	  			LOG.info("no hay imagenes en la session");
	}

}
