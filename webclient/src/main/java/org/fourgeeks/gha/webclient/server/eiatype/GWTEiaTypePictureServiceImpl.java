package org.fourgeeks.gha.webclient.server.eiatype;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;
import org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote;
import org.fourgeeks.gha.webclient.client.eiatype.GWTEiaTypePictureService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * 
 * @author lcampo
 *
 */
public class GWTEiaTypePictureServiceImpl extends RemoteServiceServlet implements GWTEiaTypePictureService {

	@EJB(name = "gmh.EiaTypePictureService")
	EiaTypePictureServiceRemote eiaTypePictureServiceRemote;
	@Override
	public void save(EiaTypePicture eiaTypePicture)
			throws EJBException {
		eiaTypePictureServiceRemote.save(eiaTypePicture);
	}

	@Override
	public EiaTypePicture find(long id) throws EJBException {
		
		return eiaTypePictureServiceRemote.find(id);
	}

	@Override
	public List<EiaTypePicture> find(EiaType eiaType) throws EJBException {
		return eiaTypePictureServiceRemote.find(eiaType);
	}

	@Override
	public boolean update(EiaTypePicture eiaTypePicture) throws EJBException {
		
		return eiaTypePictureServiceRemote.update(eiaTypePicture);
	}

	@Override
	public void delete(long id) throws EJBException {
		eiaTypePictureServiceRemote.delete(id);
		
	}

}
