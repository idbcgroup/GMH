package org.fourgeeks.gha.ejb.gom;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.Concept;

/**
 * @author emiliot
 * 
 */

@Remote
public interface CCDIServiceRemote {

	/**
	 * This method creates a CCDI definition, the params are used to configurate
	 * such definition
	 * 
	 * @param code
	 * @param name
	 * @param length
	 * @param levels
	 * @param status
	 * @param concept
	 * @param type
	 * @param addVerify
	 * @param verificationMethod
	 * @return the code of the CCDI definition
	 * @throws GHAEJBException
	 */
	public String createCCDIDefinition(String code, String name, int length,
			int levels, String status, Concept concept, String type,
			boolean addVerify, String verificationMethod)
			throws GHAEJBException;

	/**
	 * This method defines a new CCDI Level definition, the params are used to
	 * configurate such definition.
	 * 
	 * @param definition
	 * @param level
	 * @param name
	 * @param length
	 * @param valueType
	 * @param fixedValue
	 * @param initialValue
	 * @param incValue
	 * @param separator
	 * @param valueAtEndAction
	 * @return the code of the CCDI level Definition
	 * @throws GHAEJBException
	 */
	public String createCCDILevelDefinition(String definition, int level,
			String name, int length, String valueType, String fixedValue,
			int initialValue, int incValue, String separator,
			String valueAtEndAction) throws GHAEJBException;

	/**
	 * This method delete a CCDI definition plus all definition levels and
	 * values
	 * 
	 * @param code
	 * @throws GHAEJBException
	 */
	public void delete(String code) throws GHAEJBException;

	/**
	 * @param code
	 * @return
	 * @throws GHAEJBException
	 */
	public CCDIDefinition findCCDIDefinitionByCode(String code)
			throws GHAEJBException;

	/**
	 * @param code
	 *            CCDILevelDefinitionCode
	 * @return the next value available for a given level
	 * @throws GHAEJBException
	 */
	public String getNextCCDILevelValue(String code) throws GHAEJBException;
}
