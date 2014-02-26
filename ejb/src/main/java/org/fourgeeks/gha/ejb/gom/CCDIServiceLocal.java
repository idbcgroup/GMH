/**
 * 
 */
package org.fourgeeks.gha.ejb.gom;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;

/**
 * @author emiliot
 * 
 */
@Local
public interface CCDIServiceLocal {
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
	 * This method should be used to create the categories inside the ccdi tree.
	 * If an attempt to create a category is made in the deepest level the
	 * method will throw an exception.
	 * 
	 * @see CCDIServiceRemote#getNextElementCode(String)
	 * 
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
	 *            of the levelValue
	 * @return the code to identify a new element associated with the category
	 *         expressed by levelValue
	 * @throws GHAEJBException
	 */
	public String getNextElementCode(String code) throws GHAEJBException;
}