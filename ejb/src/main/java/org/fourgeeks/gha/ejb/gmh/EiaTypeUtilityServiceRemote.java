package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @throws EJBException
	 */
	public EiaTypeUtility save(EiaTypeUtility eiaTypeUtility)
			throws EJBException;

	/**
	 * REtrieve a list of EiaTypeUtility of the eiatype
	 * 
	 * @param eiaType
	 * @return a list of EiaTypeUtility
	 * @throws EJBException
	 */
	public List<EiaTypeUtility> findByEiaType(EiaType eiaType)
			throws EJBException;

	/**
	 * Deletes a EiaTypeUtility
	 * 
	 * @param id
	 * @throws EJBException
	 */
	public void delete(long id) throws EJBException;

}
