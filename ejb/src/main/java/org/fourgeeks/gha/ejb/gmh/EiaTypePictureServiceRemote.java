/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;

/**
 * @author emiliot
 * 
 */

@Remote
public interface EiaTypePictureServiceRemote {
	/**
	 * @param eiaTypePicture
	 * @throws GHAEJBException 
	 */
	public void save(EiaTypePicture eiaTypePicture) throws GHAEJBException;
			throws EJBException;

	/**
	 * @param Id
	 * @return true if the object was deleted
	 * @throws GHAEJBException 
	 */
	public boolean delete(long Id) throws GHAEJBException;

	/**
	 * @param eiaTypePicture
	 * @return true if the object was updated
	 * @throws GHAEJBException 
	 */
	public boolean update(EiaTypePicture eiaTypePicture) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the EiaTypePicture with this id
	 * @throws GHAEJBException 
	 */
	public EiaTypePicture find(long Id) throws GHAEJBException;

	/**
	 * @param eiaType
	 * @return the list of EiaTypePicture pictures
	 * @throws GHAEJBException 
	 */
	public List<EiaTypePicture> find(EiaType eiaType) throws GHAEJBException;
}
