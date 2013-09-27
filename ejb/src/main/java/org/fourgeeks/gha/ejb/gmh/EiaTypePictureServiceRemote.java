/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public EiaTypePicture save(EiaTypePicture eiaTypePicture)
			throws EJBException;

	/**
	 * @param Id
	 * @return true if the object was deleted
	 * @throws EJBException
	 */
	public boolean delete(long Id) throws EJBException;

	/**
	 * @param eiaTypePicture
	 * @return true if the object was updated
	 * @throws EJBException
	 */
	public boolean update(EiaTypePicture eiaTypePicture) throws EJBException;

	/**
	 * @param Id
	 * @return the EiaTypePicture with this id
	 * @throws EJBException
	 */
	public EiaTypePicture find(long Id) throws EJBException;

	/**
	 * @param eiaType
	 * @return the list of EiaTypePicture pictures
	 * @throws EJBException
	 */
	public List<EiaTypePicture> find(EiaType eiaType) throws EJBException;
}
