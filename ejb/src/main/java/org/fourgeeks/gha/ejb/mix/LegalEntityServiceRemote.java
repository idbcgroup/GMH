/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.LegalEntity;

/**
 * @author emiliot
 *
 */

@Remote
public interface LegalEntityServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param legalEntity
	 * @return the list of legalEntities
	 * @throws EJBException
	 */
	public List<LegalEntity> find(LegalEntity legalEntity) throws EJBException;

	/**
	 * @param Id
	 * @return the legalEntity
	 * @throws EJBException
	 */
	public LegalEntity find(long Id) throws EJBException;
	
	/**
	 * @return the list of legalEntities
	 * @throws EJBException
	 */
	public List<LegalEntity> getAll() throws EJBException;

	/**
	 * @param legalEntity
	 * @return the saved legalEntity
	 * @throws EJBException
	 */
	public LegalEntity save(LegalEntity legalEntity) throws EJBException;

	/**
	 * @param legalEntity
	 * @return the updated legalEntity
	 * @throws EJBException
	 */
	public LegalEntity update(LegalEntity legalEntity) throws EJBException;
}
