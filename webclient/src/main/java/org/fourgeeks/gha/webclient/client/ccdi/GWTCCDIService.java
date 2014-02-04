package org.fourgeeks.gha.webclient.client.ccdi;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */

@RemoteServiceRelativePath("ccdi")
public interface GWTCCDIService extends RemoteService {
	/**
	 * @param definition
	 * @return a new CCDI definition
	 * @throws GHAEJBException
	 */
	public CCDIDefinition createCCDIDefinition(CCDIDefinition definition)
			throws GHAEJBException;

	/**
	 * @param definition
	 * @param levelDefinition
	 * @return a new CCDI Level Definition
	 * @throws GHAEJBException
	 */
	public CCDILevelDefinition createCCDILevelDefinition(
			CCDIDefinition definition, CCDILevelDefinition levelDefinition)
			throws GHAEJBException;

	/**
	 * @param levelDefinition
	 * @param parentValue
	 * @param levelValue
	 * @return the new CCDILevelValue
	 * @throws GHAEJBException
	 */
	public CCDILevelValue createCCDILevelValue(
			CCDILevelDefinition levelDefinition, CCDILevelValue parentValue,
			CCDILevelValue levelValue) throws GHAEJBException;

	/**
	 * This method delete a CCDI definition plus all definition levels and
	 * values
	 * 
	 * @param code
	 * @throws GHAEJBException
	 */
	public void deleteByCode(String code) throws GHAEJBException;

	/**
	 * @param code
	 * @return
	 * @throws GHAEJBException
	 */
	public CCDIDefinition findCCDIDefinitionByCode(String code)
			throws GHAEJBException;

	/**
	 * @param definition
	 * @param level
	 * @return the definition for the level
	 * @throws GHAEJBException
	 */
	public CCDILevelDefinition findCCDILevelDefinitionByLevel(
			CCDIDefinition definition, int level) throws GHAEJBException;

	/**
	 * @param code
	 *            CCDILevelDefinitionCode
	 * @return the next value available for a given level
	 * @throws GHAEJBException
	 */
	public String getNextCCDILevelValue(String code) throws GHAEJBException;
}
