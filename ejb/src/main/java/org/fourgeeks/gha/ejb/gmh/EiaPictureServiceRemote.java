/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException 
	 */
	public void save(EiaPicture eiaPicture) throws EJBException;
	/**
	 * @param Id
	 * @return true if the object was deleted
	 * @throws EJBException 
	 */
	public boolean delete(long Id) throws EJBException;
	/**
	 * @param eiaPicture
	 * @return true if the object was updated
	 * @throws EJBException 
	 */
	public boolean update(EiaPicture eiaPicture) throws EJBException;
	/**
	 * @param Id
	 * @return an EiaPicture with this Id
	 * @throws EJBException 
	 */
	public EiaPicture find(long Id) throws EJBException;
	/**
	 * @param eia
	 * @return a List with all the EiaPictres for this eia
	 * @throws EJBException 
	 */
	public List<EiaPicture> find(Eia eia) throws EJBException;
}
