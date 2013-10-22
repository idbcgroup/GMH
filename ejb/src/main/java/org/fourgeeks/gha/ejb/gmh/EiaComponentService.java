/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless(name = "gmh.EiaComponentService")
public class EiaComponentService extends GHAEJBExceptionImpl implements
		EiaComponentServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaComponentService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			EiaComponent entity = em.find(EiaComponent.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiaComponent", e);
			throw super.generateGHAEJBException("eiaComponent-delete-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#find(long)
	 */
	@Override
	public EiaComponent find(long Id) throws GHAEJBException {
		try {
			return em.find(EiaComponent.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando EiaComponent", e);
			throw super.generateGHAEJBException("eiaComponent-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#findByParentEia(org
	 * .fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<EiaComponent> findByParentEia(Eia eia) throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaComponent.findByParentEia",
							EiaComponent.class).setParameter("parentEia", eia)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all EiaComponents", ex);
			throw super.generateGHAEJBException(
					"eiaComponent-findByParentEia-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#getAll()
	 */
	@Override
	public List<EiaComponent> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaComponent.getAll",
					EiaComponent.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EiaComponents", ex);
			throw super.generateGHAEJBException("eiaComponent-getAll-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#getAll(int, int)
	 */
	@Override
	public List<EiaComponent> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaComponent.getAll", EiaComponent.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EiaComponents", ex);
			throw super.generateGHAEJBException("eiaComponent-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#save(org.fourgeeks
	 * .gha.ejb.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent save(EiaComponent eiaComponent) throws GHAEJBException {
		try {
			em.persist(eiaComponent);
			em.flush();
			return em.find(EiaComponent.class, eiaComponent.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaComponent", e);
			throw super.generateGHAEJBException("eiaComponent-save-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#update(org.fourgeeks
	 * .gha.ejb.gmh.EiaComponent)
	 */
	@Override
	public EiaComponent update(EiaComponent eiaComponent)
			throws GHAEJBException {
		try {
			EiaComponent res = em.merge(eiaComponent);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiacomponent", e);
			throw super.generateGHAEJBException("eiaComponent-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
