package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot
 * 
 */

@Stateless
public class EiaTypeMaterialService extends GHAEJBExceptionImpl implements
		EiaTypeMaterialServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeMaterialService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaterialServiceRemote#findByEiaType(
	 * org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeMaterial> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaTypeMaterial.findByEiaType",
							EiaTypeMaterial.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeMaterial", ex);
			throw super.generateGHAEJBException(
					"eiaTypeMaterial-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaterialServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.EiaTypeMaterial)
	 */
	@Override
	public EiaTypeMaterial save(EiaTypeMaterial eiaTypeMaterial)
			throws GHAEJBException {
		try {
			em.persist(eiaTypeMaterial);
			em.flush();
			return em.find(EiaTypeMaterial.class, eiaTypeMaterial.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaTypeMaterial", e);
			// String message = null;
			// if (e.getCause() instanceof ConstraintViolationException) {
			// message =
			// "Error: Ya se ha agregado ese Material a este Tipo de Equipo";
			// }
			// if (message == null)
			// message = "Error guardando EiaTypeMaterial: "
			// + e.getCause().getMessage();
			throw super.generateGHAEJBException("eiaTypeMaterial-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaterialServiceRemote#delete(long)
	 */
	@Override
	public void delete(long id) throws GHAEJBException {
		try {
			EiaTypeMaterial entity = em.find(EiaTypeMaterial.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypematerial", e);
			throw super.generateGHAEJBException("eiaTypeMaterial-delete-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaterialServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.EiaTypeMaterial)
	 */
	@Override
	public EiaTypeMaterial update(EiaTypeMaterial eiaTypeMaterial)
			throws GHAEJBException {
		try {
			EiaTypeMaterial res = em.merge(eiaTypeMaterial);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: updating eiaTypeMaterial", e);
			throw super.generateGHAEJBException("eiaTypeMaterial-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
