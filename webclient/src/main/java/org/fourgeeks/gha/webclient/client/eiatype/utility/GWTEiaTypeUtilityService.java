/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret, emiliot
 * 
 */
@RemoteServiceRelativePath("eiaTypeUtility")
public interface GWTEiaTypeUtilityService extends RemoteService {

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

	public EiaTypeUtility update(EiaTypeUtility eiaTypeUtility)
			throws GHAEJBException;

}
