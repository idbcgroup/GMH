/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "gmh.EiaTypeComponentService")
public class EiaTypeComponentService extends GHAEJBExceptionImpl implements
		EiaTypeComponentServiceRemote, EiaTypeComponentServiceLocal {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeComponentService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			EiaTypeComponent entity = em.find(EiaTypeComponent.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypecomponent",
					e);
			throw super.generateGHAEJBException("eiaTypeComponent-delete-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(long)
	 */
	@Override
	public EiaTypeComponent find(long Id) throws GHAEJBException {
		try {
			return em.find(EiaTypeComponent.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando EiatypeComponent por Id ", e);
			throw super.generateGHAEJBException("eiaTypeComponent-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#findByParentEiaType
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EiaTypeComponent> findByParentEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaTypeComponent.findByParentEiaType",
							EiaTypeComponent.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeComponents",
					ex);
			throw super.generateGHAEJBException(
					"eiaTypeComponent-findByParentEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll()
	 */
	@Override
	public List<EiaTypeComponent> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaTypeComponent.getAll",
					EiaTypeComponent.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EiaTypeComponents",
					ex);
			throw super.generateGHAEJBException("eiaTypeComponent-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<EiaTypeComponent> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaTypeComponent.getAll",
							EiaTypeComponent.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EiaTypeComponents",
					ex);
			throw super.generateGHAEJBException("eiaTypeComponent-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#save(org.fourgeeks
	 * .gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException {
		try {
			em.persist(eiaTypeComponent);
			em.flush();
			return em.find(EiaTypeComponent.class, eiaTypeComponent.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaTypeComponent", e);
			// String message = e.getMessage(); null;
			// if (e.getCause() instanceof ConstraintViolationException) {
			// message =
			// "Error: Ya se ha agregado ese Componente a este Tipo de Equipo";
			// }
			// if (message == null)
			// message = "Error guardando EiaTypeComponent: "
			// + e.getCause().getMessage();
			throw super.generateGHAEJBException("eiaTypeComponent-save-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#update(org.fourgeeks
	 * .gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent)
			throws GHAEJBException {
		try {
			EiaTypeComponent res = em.merge(eiaTypeComponent);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiatypecomponent",
					e);
			throw super.generateGHAEJBException("eiaTypeComponent-update-fail",
					RuntimeParameters.getLang(), em);
		}

	}

}
