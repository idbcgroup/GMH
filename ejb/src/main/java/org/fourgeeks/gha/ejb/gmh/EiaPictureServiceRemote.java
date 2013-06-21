/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

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
	 */
	public void save(EiaPicture eiaPicture);
	/**
	 * @param Id
	 * @return true if the object was deleted
	 */
	public boolean delete(long Id);
	/**
	 * @param eiaPicture
	 * @return true if the object was updated
	 */
	public boolean update(EiaPicture eiaPicture);
	/**
	 * @param Id
	 * @return an EiaPicture with this Id
	 */
	public EiaPicture find(long Id);
	/**
	 * @param eia
	 * @return a List with all the EiaPictres for this eia
	 */
	public List<EiaPicture> find(Eia eia);
}
