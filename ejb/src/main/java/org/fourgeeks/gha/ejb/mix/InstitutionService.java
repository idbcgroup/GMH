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
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot
 *
 */

@Stateless(name = "mix.InstitutionService")
public class InstitutionService implements InstitutionServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(InstitutionService.class
			.getName());
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			Institution entity = em.find(Institution.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Institution", e);
			throw new EJBException("ERROR: unable to delete Institution "
					+ e.getCause().getMessage());
		}
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
		try {
			return em.find(Institution.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Institution", e);
			throw new EJBException("ERROR: finding Institution "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#getAll()
	 */
	@Override
	public List<Institution> getAll() throws EJBException {
		try {
			return em.createNamedQuery("Institution.getAll", Institution.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Institution", ex);
			throw new EJBException("Error obteniendo todas las Institution"
					+ ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#save(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public Institution save(Institution institution) throws EJBException {
		try {
			em.persist(institution);
			em.flush();
			return em.find(Institution.class, institution.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Institution ", e);
			throw new EJBException("ERROR: saving Institution "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote#update(org.fourgeeks.gha.domain.mix.Institution)
	 */
	@Override
	public Institution update(Institution institution) throws EJBException {
		try {
			Institution res = em.merge(institution);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update Institution ", e);
			throw new EJBException("ERROR: no se puede actualizar el Institution "
					+ e.getCause().getMessage());
		}
	}

}