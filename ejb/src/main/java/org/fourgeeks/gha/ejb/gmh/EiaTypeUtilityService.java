package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg, emiliot
 * 
 */
@Stateless
public class EiaTypeUtilityService extends GHAEJBExceptionImpl implements
		EiaTypeUtilityServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeUtilityService.class.getName());

	@Override
	public EiaTypeUtility save(EiaTypeUtility eiaTypeUtility)
			throws GHAEJBException {
		try {
			em.persist(eiaTypeUtility);
			em.flush();
			return em.find(EiaTypeUtility.class, eiaTypeUtility.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaTypeUtility", e);
			// String message = null;
			// if (e.getCause() instanceof ConstraintViolationException) {
			// message =
			// "Error: Ya se ha agregado este Servicio a este Tipo de Equipo";
			// }
			// if (message == null)
			// message = "Error guardando EiaTypeUtility: "
			// + e.getCause().getMessage();
			throw super.generateGHAEJBException("eiaTypeUtility-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<EiaTypeUtility> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaTypeUtility.findByEiaType",
							EiaTypeUtility.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeUtility", ex);
			throw super.generateGHAEJBException(
					"eiaTypeUtility-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public void delete(long id) throws GHAEJBException {
		try {
			EiaTypeUtility entity = em.find(EiaTypeUtility.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete EiaTypeUtility", e);
			throw super.generateGHAEJBException("eiaTypeUtility-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeUtilityServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.EiaTypeUtility)
	 */
	@Override
	public EiaTypeUtility update(EiaTypeUtility eiaTypeUtility)
			throws GHAEJBException {
		try {
			EiaTypeUtility res = em.merge(eiaTypeUtility);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: updating eiaTypeUtility", e);
			throw super.generateGHAEJBException("eiaTypeUtility-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
