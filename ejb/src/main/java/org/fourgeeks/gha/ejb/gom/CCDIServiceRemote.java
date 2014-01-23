package org.fourgeeks.gha.ejb.gom;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	public String CCDICreateDefinition(String code, String name, int length,
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
	public String CCDICreateLevelDefinition(String definition, int level,
			String name, int length, String valueType, String fixedValue,
			int initialValue, int incValue, String separator,
			String valueAtEndAction) throws GHAEJBException;

	/**
	 * This method creates an entry into the ccdilevelvalues table in order to
	 * keep track of the consecutives generated for the level
	 * 
	 * @param levelCode
	 * @param name
	 * @param nextValue
	 * @param status
	 * @throws GHAEJBException
	 */
	public void CCDICreateLevelValue(String levelCode, String name,
			String nextValue, String status) throws GHAEJBException;

	/**
	 * @param code
	 *            CCDILevelDefinitionCode
	 * @return the next value available for a given level
	 * @throws GHAEJBException
	 */
	public String CCDIGetNextValue(String code) throws GHAEJBException;
}
