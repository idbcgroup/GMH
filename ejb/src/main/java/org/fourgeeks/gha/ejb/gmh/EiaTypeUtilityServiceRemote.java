package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;

/**
 * @author alacret
 * 
 */
@Remote
public interface EiaTypeUtilityServiceRemote {

	/**
	 * Save an EiaTypeUtility
	 * 
	 * @param eiaTypeUtility
	 * @return the saved EiaTypeUtility
	 * @throws GHAEJBException
	 */
	public EiaTypeUtility save(EiaTypeUtility eiaTypeUtility)
			throws GHAEJBException;

	/**
	 * REtrieve a list of EiaTypeUtility of the eiatype
	 * 
	 * @param eiaType
	 * @return a list of EiaTypeUtility
	 * @throws GHAEJBException
	 */
	public List<EiaTypeUtility> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * Deletes a EiaTypeUtility
	 * 
	 * @param id
	 * @throws GHAEJBException
	 */
	public void delete(long id) throws GHAEJBException;

}
