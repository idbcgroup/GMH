/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Bpi;

/**
 * @author emiliot
 *
 */
public class BpiService implements BpiServiceRemote {

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#find(org.fourgeeks.gha.domain.mix.Bpi)
	 */
	@Override
	public List<Bpi> find(Bpi bpi) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#find(long)
	 */
	@Override
	public Bpi find(long Id) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#getAll()
	 */
	@Override
	public List<Bpi> getAll() throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#save(org.fourgeeks.gha.domain.mix.Bpi)
	 */
	@Override
	public Bpi save(Bpi bpi) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpiServiceRemote#update(org.fourgeeks.gha.domain.mix.Bpi)
	 */
	@Override
	public Bpi update(Bpi bpi) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

}
