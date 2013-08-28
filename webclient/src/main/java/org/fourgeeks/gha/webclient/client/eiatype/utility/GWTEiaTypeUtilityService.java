/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("eiaTypeUtility")
public interface GWTEiaTypeUtilityService extends RemoteService {

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
