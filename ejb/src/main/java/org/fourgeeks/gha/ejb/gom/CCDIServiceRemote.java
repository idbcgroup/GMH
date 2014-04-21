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
	 * @param levelDefinition
	 * @return a new CCDI Level Definition
	 * @throws GHAEJBException
	 */
	public CCDILevelDefinition createLevelDefinition(
			CCDILevelDefinition levelDefinition) throws GHAEJBException;

	/**
	 * This method should be used to create the categories inside the ccdi tree.
	 * If an attempt to create a category is made at the deepest level the
	 * method will throw an exception.
	 * 
	 * @param levelValue
	 * 
	 * @see CCDIServiceRemote#getNextElementCode(String)
	 * 
	 * @return the new CCDILevelValue
	 * @throws GHAEJBException
	 */
	public CCDILevelValue createLevelValue(CCDILevelValue levelValue)
			throws GHAEJBException;

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
	 * @return the CCDI definition associtated with this code
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
	 * This method returns the next identifier available for an item in a
	 * category. Calling this method with a levelvalue higher in the category
	 * hierarchy, rather than calling it with a category in the last category
	 * level, will make the method throw an exception
	 * 
	 * @param code
	 *            of the levelValue
	 * @return the code to identify a new element associated with the category
	 *         expressed by levelValue
	 * @throws GHAEJBException
	 */
	public String getNextElementCode(String code) throws GHAEJBException;
}
