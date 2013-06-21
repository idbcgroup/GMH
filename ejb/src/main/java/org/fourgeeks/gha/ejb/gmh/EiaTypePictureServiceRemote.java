/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

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
	 */
	public void save(EiaTypePicture eiaTypePicture);
	/**
	 * @param Id
	 * @return true if the object was deleted
	 */
	public boolean delete(long Id);
	/**
	 * @param eiaTypePicture
	 * @return true if the object was updated
	 */
	public boolean update(EiaTypePicture eiaTypePicture);
	/**
	 * @param Id
	 * @return the EiaTypePicture with this id
	 */
	public EiaTypePicture find(long Id);
	/**
	 * @param eiaType
	 * @return the list of EiaTypePicture pictures 
	 */
	public List<EiaTypePicture> find(EiaType eiaType);
}
