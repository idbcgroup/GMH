package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

@Stateless(name = "gmh.EiaTypeMaterialCategory")
public class EiaTypeMaterialCategoryService extends GHAEJBExceptionImpl
		implements EiaTypeMaterialCategoryServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeMaterialCategoryService.class.getName());

	@Override
	public List<EiaTypeMaterialCategory> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaTypeMaterialCategory.findByEiaType",
							EiaTypeMaterialCategory.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeMaterial", ex);
			throw super.generateGHAEJBException(
					"eiaTypeMaterial-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public EiaTypeMaterialCategory save(EiaTypeMaterialCategory eiaTypeMaterial)
			throws GHAEJBException {
		try {
			em.persist(eiaTypeMaterial);
			em.flush();
			return em.find(EiaTypeMaterialCategory.class,
					eiaTypeMaterial.getId());
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

	@Override
	public void delete(long id) throws GHAEJBException {
		try {
			EiaTypeMaterialCategory entity = em.find(
					EiaTypeMaterialCategory.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypematerial", e);
			throw super.generateGHAEJBException("eiaTypeMaterial-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}