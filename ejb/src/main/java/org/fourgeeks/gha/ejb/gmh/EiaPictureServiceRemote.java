package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPicture;

/**
 * @author emiliot
 * 
 */

@Remote
public interface EiaPictureServiceRemote {
	/**
	 * @param eiaPicture
	 * @throws GHAEJBException
	 */
	public void save(EiaPicture eiaPicture) throws GHAEJBException;

	/**
	 * @param Id
	 * @return true if the object was deleted
	 * @throws GHAEJBException
	 */
	public boolean delete(long Id) throws GHAEJBException;

	/**
	 * @param eiaPicture
	 * @return true if the object was updated
	 * @throws GHAEJBException
	 */
	public boolean update(EiaPicture eiaPicture) throws GHAEJBException;

	/**
	 * @param Id
	 * @return an EiaPicture with this Id
	 * @throws GHAEJBException
	 */
	public EiaPicture find(long Id) throws GHAEJBException;

	/**
	 * @param eia
	 * @return a List with all the EiaPictres for this eia
	 * @throws GHAEJBException
	 */
	public List<EiaPicture> find(Eia eia) throws GHAEJBException;
}
