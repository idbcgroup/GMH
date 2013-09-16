/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Stateless;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.LegalEntity;

/**
 * @author emiliot
 *
 */
@Stateless(name = "mix.LegalEntity")
public class LegalEntityService implements LegalEntityServiceRemote {

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#find(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public List<LegalEntity> find(LegalEntity legalEntity) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#find(long)
	 */
	@Override
	public LegalEntity find(long Id) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#getAll()
	 */
	@Override
	public List<LegalEntity> getAll() throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#save(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity save(LegalEntity legalEntity) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#update(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity update(LegalEntity legalEntity) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

}
