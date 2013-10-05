/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.LegalEntity;

/**
 * @author emiliot
 *
 */

@Remote
public interface LegalEntityServiceRemote {
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param legalEntity
	 * @return the list of legalEntities
	 * @throws GHAEJBException
	 */
	public List<LegalEntity> find(LegalEntity legalEntity) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the legalEntity
	 * @throws GHAEJBException
	 */
	public LegalEntity find(long Id) throws GHAEJBException;
	
	/**
	 * @return the list of legalEntities
	 * @throws GHAEJBException
	 */
	public List<LegalEntity> getAll() throws GHAEJBException;

	/**
	 * @param legalEntity
	 * @return the saved legalEntity
	 * @throws GHAEJBException
	 */
	public LegalEntity save(LegalEntity legalEntity) throws GHAEJBException;

	/**
	 * @param legalEntity
	 * @return the updated legalEntity
	 * @throws GHAEJBException
	 */
	public LegalEntity update(LegalEntity legalEntity) throws GHAEJBException;
}
