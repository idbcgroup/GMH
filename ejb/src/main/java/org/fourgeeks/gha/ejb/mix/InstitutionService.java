/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot
 *
 */
public class InstitutionService implements InstitutionServiceRemote {

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#find(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public List<Institution> find(Institution institution) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#find(long)
	 */
	@Override
	public Institution find(long Id) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#getAll()
	 */
	@Override
	public List<Institution> getAll() throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#save(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public Institution save(Institution institution) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#update(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public Institution update(Institution institution) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

}
