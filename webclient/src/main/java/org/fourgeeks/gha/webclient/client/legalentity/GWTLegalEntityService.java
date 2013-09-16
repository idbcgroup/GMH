/**
 * 
 */
package org.fourgeeks.gha.webclient.client.legalentity;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
