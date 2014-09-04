package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author emiliot
 * 
 */

@Stateless
public class EiaTypeCategoryService extends GHAEJBExceptionService implements
		EiaTypeCategoryServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeCategoryService.class.getName());

	@Override
	public void delete(final ServiceResourceCategory entity) throws GHAEJBException {
		if (entity == null) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypecategory");
			throw super.generateGHAEJBException("eiaTypeCategory-delete-fail",
					em);
		}
		try {
			final ServiceResourceCategory entity2 = em.find(ServiceResourceCategory.class,
					entity.getCode());
			em.remove(entity2);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatypecategory", e);
			throw super.generateGHAEJBException("eiaTypeCategory-delete-fail",
					em);
		}
	}

	@Override
	public List<ServiceResourceCategory> getAll() throws GHAEJBException {
		try {
			List<ServiceResourceCategory> res = em.createNamedQuery(
					"ServiceResourceCategory.getAll", ServiceResourceCategory.class)
					.getResultList();
			return res;
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiatypes", ex);
			throw super.generateGHAEJBException("eiatypecategory-getall-fail",
					em);
		}
	}

	@Override
	public ServiceResourceCategory save(final ServiceResourceCategory serviceResourceCategory)
			throws GHAEJBException {
		try {
			if (serviceResourceCategory == null)
				throw super.generateGHAEJBException("object-null", em);
			em.persist(serviceResourceCategory);
			em.flush();
			return em.find(ServiceResourceCategory.class, serviceResourceCategory.getCode());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiatypeCategory", e);
			throw super
					.generateGHAEJBException("eiatypeCategory-save-fail", em);
		}
	}
}
