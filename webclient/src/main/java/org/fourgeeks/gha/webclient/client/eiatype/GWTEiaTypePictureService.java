/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	 * @throws GHAEJBException
	 */
	public void save(EiaType eiaType) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the EiaTypePicture with this Id
	 * @throws GHAEJBException
	 */
	public EiaTypePicture find(long id) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return a list with possible matches to the eiaType passed by param
	 * @throws GHAEJBException
	 */
	public List<EiaTypePicture> findByEiaType(EiaType eiaType) throws GHAEJBException;

	
	/**
	 * @param EiaType
	 *            the EiaType to be update eiaTypePicture
	 * @return a EiaType updated
	 * @throws GHAEJBException
	 */
	public boolean update(EiaType eiaType, int noDeletePicture[]) throws GHAEJBException;
	
	/**
	 * @param EiaTypePicture
	 *            the EiaTypePicture to be updated
	 * @return a EiaTypePicture updated
	 * @throws GHAEJBException
	 */
	public boolean update(EiaTypePicture eiaTypePicture) throws GHAEJBException;

	/**
	 * Delete an EiaTypePicture from database by Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;
	/**
	 * 
	 * Delete an Picture from session by name
	 * @param namePicture
	 * @throws Exception
	 */
	public void deletePictureFromSession(String namePicture) throws Exception;
}
