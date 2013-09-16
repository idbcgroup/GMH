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
import org.fourgeeks.gha.domain.mix.LegalEntity;

/**
 * @author emiliot
 *
 */
@Stateless(name = "mix.LegalEntityService")
public class LegalEntityService implements LegalEntityServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(LegalEntityService.class
			.getName());
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			LegalEntity entity = em.find(LegalEntity.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete LegalEntity", e);
			throw new EJBException("ERROR: unable to delete LegalEntity "
					+ e.getCause().getMessage());
		}
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
		try {
			return em.find(LegalEntity.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding LegalEntity", e);
			throw new EJBException("ERROR: finding LegalEntity "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#getAll()
	 */
	@Override
	public List<LegalEntity> getAll() throws EJBException {
		try {
			return em.createNamedQuery("LegalEntity.getAll", LegalEntity.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all LegalEntity", ex);
			throw new EJBException("Error obteniendo todas las LegalEntity"
					+ ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#save(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity save(LegalEntity legalEntity) throws EJBException {
		try {
			em.persist(legalEntity);
			em.flush();
			return em.find(LegalEntity.class, legalEntity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving LegalEntity ", e);
			throw new EJBException("ERROR: saving LegalEntity "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote#update(org.fourgeeks.gha.domain.mix.LegalEntity)
	 */
	@Override
	public LegalEntity update(LegalEntity legalEntity) throws EJBException {
		try {
			LegalEntity res = em.merge(legalEntity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update LegalEntity ", e);
			throw new EJBException("ERROR: no se puede actualizar el LegalEntity "
					+ e.getCause().getMessage());
		}
	}

}
