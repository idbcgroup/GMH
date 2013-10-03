/**
 * 
 */
package org.fourgeeks.gha.webclient.client.legalentity;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.LegalEntity;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("legalEntity")
public interface GWTLegalEntityService extends RemoteService{
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
