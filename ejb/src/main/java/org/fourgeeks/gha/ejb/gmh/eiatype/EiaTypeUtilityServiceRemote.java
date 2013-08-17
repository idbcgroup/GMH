package org.fourgeeks.gha.ejb.gmh.eiatype;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	 * @param code
	 * @return a list of EiaTypeUtility
	 * @throws EJBException
	 */
	public List<EiaTypeUtility> findByEiaType(String code)
			throws EJBException;

	/**
	 * Deletes a EiaTypeUtility
	 * 
	 * @param id
	 * @throws EJBException
	 */
	public void delete(long id) throws EJBException;

}
