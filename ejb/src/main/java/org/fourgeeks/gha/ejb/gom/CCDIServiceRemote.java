package org.fourgeeks.gha.ejb.gom;

import javax.ejb.Remote;

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
	 */
	public String CCDICreateDefinition(String code, String name, int length,
			int levels, String status, Concept concept, String type,
			boolean addVerify, String verificationMethod);

	/**
	 * This method defines a new CCDI Level definition, the params are used to
	 * configurate such definition. Aditionally this method creates an entry
	 * into the CCDILevelValues table in order to keep track of the values
	 * generated
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
	 */
	public String CCDICreateLevelDefinition(String definition, int level,
			String name, int length, String valueType, String fixedValue,
			int initialValue, int incValue, String separator,
			String valueAtEndAction);

	/**
	 * @param code
	 * @return the next value available for a given category
	 */
	public String CCDIGetNextValue(String code);
}
