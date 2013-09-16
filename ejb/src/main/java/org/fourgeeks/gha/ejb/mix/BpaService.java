/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Bpa;

/**
 * @author emiliot
 *
 */

@Stateless(name = "mix.BpaService")
public class BpaService implements BpaServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(BpaService.class
			.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			Bpa entity = em.find(Bpa.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Bpa", e);
			throw new EJBException("ERROR: unable to delete Bpa "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#find(org.fourgeeks.gha.domain.mix.Bpa)
	 */
	@Override
	public List<Bpa> find(Bpa bpa) throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#find(long)
	 */
	@Override
	public Bpa find(long Id) throws EJBException {
		try {
			return em.find(Bpa.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Bpa", e);
			throw new EJBException("ERROR: finding Bpa "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#getAll()
	 */
	@Override
	public List<Bpa> getAll() throws EJBException {
		try {
			return em.createNamedQuery("Bpa.getAll", Bpa.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Bpa", ex);
			throw new EJBException("Error obteniendo todas las Bpa"
					+ ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#save(org.fourgeeks.gha.domain.mix.Bpa)
	 */
	@Override
	public Bpa save(Bpa bpa) throws EJBException {
		try {
			em.persist(bpa);
			em.flush();
			return em.find(Bpa.class, bpa.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Bpa ", e);
			throw new EJBException("ERROR: saving Bpa "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.BpaServiceRemote#update(org.fourgeeks.gha.domain.mix.Bpa)
	 */
	@Override
	public Bpa update(Bpa bpa) throws EJBException {
		try {
			Bpa res = em.merge(bpa);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update Bpa ", e);
			throw new EJBException("ERROR: no se puede actualizar el Bpa "
					+ e.getCause().getMessage());
		}
	}

}
