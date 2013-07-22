/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author lcampo
 * 
 */
@RemoteServiceRelativePath("eiaTypePicture")
public interface GWTEiaTypePictureService extends RemoteService {

	/**
	 * @param eiaTypePicture
	 * @return EiaTypePicture saved
	 * @throws EJBException
	 */
	public void save(EiaTypePicture eiaTypePicture) throws EJBException;

	/**
	 * @param Id
	 * @return the EiaTypePicture with this Id
	 * @throws EJBException
	 */
	public EiaTypePicture find(long id) throws EJBException;

	/**
	 * @param eiaType
	 * @return a list with possible matches to the eiaType passed by param
	 * @throws EJBException
	 */
	public List<EiaTypePicture> find(EiaType eiaType) throws EJBException;

	
	/**
	 * @param EiaTypePicture
	 *            the EiaTypePicture to be updated
	 * @return a EiaTypePicture updated
	 * @throws EJBException
	 */
	public boolean update(EiaTypePicture eiaTypePicture) throws EJBException;

	/**
	 * Delete an EiaTypePicture from database by Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;
}
