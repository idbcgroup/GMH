package org.fourgeeks.gha.ejb.gmh.eiatype;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;

/**
 * @author alacret
 * 
 */
@Stateless(name = "gmh.EiaTypeUtilityService")
public class EiaTypeUtilityService implements EiaTypeUtilityServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeUtilityService.class.getName());

	@Override
	public EiaTypeUtility save(EiaTypeUtility eiaTypeUtility)
			throws EJBException {
		try {
			em.persist(eiaTypeUtility);
			em.flush();
			return em.find(EiaTypeUtility.class, eiaTypeUtility.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaTypeUtility", e);
			String message = null;
			if (e.getCause() instanceof ConstraintViolationException) {
				message = "Error: Ya se ha agregado este Servicio a este Tipo de Equipo";
			}
			if (message == null)
				message = "Error guardando EiaTypeUtility: "
						+ e.getCause().getMessage();
			throw new EJBException(message);
		}
	}

	@Override
	public List<EiaTypeUtility> findByEiaType(String code)
			throws EJBException {
		TypedQuery<EiaTypeUtility> query = em.createNamedQuery(
				"EiaTypeUtility.findByEiaTypeId", EiaTypeUtility.class);
		query.setParameter("eiaType", new EiaType(code));
		return query.getResultList();
	}

	@Override
	public void delete(long id) throws EJBException {
		try {
			EiaTypeUtility entity = em.find(EiaTypeUtility.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete EiaTypeUtility", e);
			throw new EJBException("Error eliminando EiaTypeUtility por id "
					+ e.getCause().getMessage());
		}
	}

}
