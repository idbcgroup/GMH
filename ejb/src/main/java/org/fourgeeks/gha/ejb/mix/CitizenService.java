/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author emiliot
 *
 */
public class CitizenService implements CitizenServiceRemote {

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#find(org.fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public List<Citizen> find(Citizen Citizen) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#find(long)
	 */
	@Override
	public Citizen find(long Id) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#getAll()
	 */
	@Override
	public List<Citizen> getAll() throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#save(org.fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public Citizen save(Citizen Citizen) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#update(org.fourgeeks.gha.domain.mix.Citizen)
	 */
	@Override
	public Citizen update(Citizen Citizen) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

}
