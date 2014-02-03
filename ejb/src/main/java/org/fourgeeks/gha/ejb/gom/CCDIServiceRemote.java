package org.fourgeeks.gha.ejb.gom;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;

/**
 * @author emiliot
 * 
 */

@Remote
public interface CCDIServiceRemote {

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
