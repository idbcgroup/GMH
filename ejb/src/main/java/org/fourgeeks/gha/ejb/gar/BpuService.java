/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless(name = "gar.BpuService")
public class BpuService extends GHAEJBExceptionImpl implements BpuServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(BpuService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.BpuServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Bpu entity = em.find(Bpu.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Bpu", e);
			throw super.generateGHAEJBException("bpu-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gar.BpuServiceRemote#find(org.fourgeeks.gha.domain
	 * .gar.Bpu)
	 */
	@Override
	public List<Bpu> find(Bpu bpu) throws GHAEJBException {
		try {
			return em.createNamedQuery("Bpu.findByBpu", Bpu.class)
					.setParameter("bpu", bpu).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding Bpu by bpu", ex);
			throw super.generateGHAEJBException("bpu-findByBpu-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.BpuServiceRemote#find(long)
	 */
	@Override
	public Bpu find(long Id) throws GHAEJBException {
		try {
			return em.find(Bpu.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Bpu", e);
			throw super.generateGHAEJBException("bpu-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gar.BpuServiceRemote#getAll()
	 */
	@Override
	public List<Bpu> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Bpu.getAll", Bpu.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Bpu", ex);
			throw super.generateGHAEJBException("bpu-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gar.BpuServiceRemote#save(org.fourgeeks.gha.domain
	 * .gar.Bpu)
	 */
	@Override
	public Bpu save(Bpu bpu) throws GHAEJBException {
		try {
			em.persist(bpu);
			em.flush();
			return em.find(Bpu.class, bpu.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Bpu ", e);
			throw super.generateGHAEJBException("bpu-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gar.BpuServiceRemote#update(org.fourgeeks.gha.domain
	 * .gar.Bpu)
	 */
	@Override
	public Bpu update(Bpu bpu) throws GHAEJBException {
		try {
			Bpu res = em.merge(bpu);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Bpu ", e);
			throw super.generateGHAEJBException("bpu-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
