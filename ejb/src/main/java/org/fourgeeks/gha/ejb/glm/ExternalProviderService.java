/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.enu.ProviderPreferenceEnum;
import org.fourgeeks.gha.domain.enu.ProviderQualEnum;
import org.fourgeeks.gha.domain.enu.ProviderRepresentEnum;
import org.fourgeeks.gha.domain.enu.ProviderResourceTypeEnum;
import org.fourgeeks.gha.domain.enu.ProviderServicesEnum;
import org.fourgeeks.gha.domain.enu.ProviderTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg
 * 
 */
@Stateless
public class ExternalProviderService extends GHAEJBExceptionImpl implements
		ExternalProviderServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ExternalProviderService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote#buildFilters(
	 * org.fourgeeks.gha.domain.glm.ExternalProvider,
	 * javax.persistence.criteria.CriteriaBuilder,
	 * javax.persistence.criteria.Root)
	 */
	@Override
	public Predicate buildFilters(ExternalProvider entity, CriteriaBuilder cb,
			Root<ExternalProvider> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			criteria = cb.and(criteria, cb.equal(root.<String> get("code"), p));
		}
		if (entity.getInstitution() != null) {
			ParameterExpression<Institution> p = cb.parameter(
					Institution.class, "institution");
			criteria = cb.and(criteria,
					cb.equal(root.<Institution> get("institution"), p));
		}
		if (entity.getPreference() != null) {
			ParameterExpression<ProviderPreferenceEnum> p = cb.parameter(
					ProviderPreferenceEnum.class, "preference");
			criteria = cb.and(criteria, cb.equal(
					root.<ProviderPreferenceEnum> get("preference"), p));

		}
		// if (entity.getPrimaryBrand() != null) {
		// ParameterExpression<Brand> p = cb.parameter(Brand.class,
		// "primaryBrand");
		// criteria = cb.and(criteria,
		// cb.equal(root.<Brand> get("primaryBrand"), p));
		// }
		// if (entity.getPrimaryManufacturer() != null) {
		// ParameterExpression<Manufacturer> p = cb.parameter(
		// Manufacturer.class, "primaryManufacturer");
		// criteria = cb
		// .and(criteria, cb.equal(
		// root.<Manufacturer> get("primaryManufacturer"), p));
		// }
		if (entity.getQualification() != null) {
			ParameterExpression<ProviderQualEnum> p = cb.parameter(
					ProviderQualEnum.class, "qualification");
			criteria = cb.and(criteria,
					cb.equal(root.<ProviderQualEnum> get("qualification"), p));
		}
		if (entity.getResourceType() != null) {
			ParameterExpression<ProviderResourceTypeEnum> p = cb.parameter(
					ProviderResourceTypeEnum.class, "resourcetype");
			criteria = cb.and(criteria, cb.equal(
					root.<ProviderResourceTypeEnum> get("resourcetype"), p));
		}
		if (entity.getServices() != null) {
			ParameterExpression<ProviderServicesEnum> p = cb.parameter(
					ProviderServicesEnum.class, "services");
			criteria = cb.and(criteria,
					cb.equal(root.<ProviderServicesEnum> get("services"), p));
		}
		if (entity.getType() != null) {
			ParameterExpression<ProviderTypeEnum> p = cb.parameter(
					ProviderTypeEnum.class, "type");
			criteria = cb.and(criteria,
					cb.equal(root.<ProviderTypeEnum> get("type"), p));
		}
		if (entity.getWhoRepresent() != null) {
			ParameterExpression<ProviderRepresentEnum> p = cb.parameter(
					ProviderRepresentEnum.class, "whorepresent");
			criteria = cb.and(criteria, cb.equal(
					root.<ProviderRepresentEnum> get("whorepresent"), p));
		}
		return criteria;
	}

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			ExternalProvider entity = em.find(ExternalProvider.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete ExternalProvider",
					e);
			throw super.generateGHAEJBException("externalProvider-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<ExternalProvider> find(ExternalProvider entity)
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ExternalProvider> cQuery = cb
					.createQuery(ExternalProvider.class);
			Root<ExternalProvider> root = cQuery.from(ExternalProvider.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));

			Predicate criteria = buildFilters(entity, cb, root);
			cQuery.where(criteria);

			TypedQuery<ExternalProvider> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (entity.getCode() != null) {
					q.setParameter("code", entity.getCode());
				}
				if (entity.getInstitution() != null) {
					q.setParameter("institution", entity.getInstitution());
				}
				if (entity.getPreference() != null) {
					q.setParameter("preference", entity.getPreference());
				}
				// if (entity.getPrimaryBrand() != null) {
				// q.setParameter("primaryBrand", entity.getPrimaryBrand());
				// }
				// if (entity.getPrimaryManufacturer() != null) {
				// q.setParameter("primaryManufacturer",
				// entity.getPrimaryManufacturer());
				// }
				if (entity.getQualification() != null) {
					q.setParameter("qualification", entity.getQualification());
				}
				if (entity.getResourceType() != null) {
					q.setParameter("resourcetype", entity.getResourceType());
				}
				if (entity.getServices() != null) {
					q.setParameter("services", entity.getServices());
				}
				if (entity.getType() != null) {
					q.setParameter("type", entity.getType());
				}
				if (entity.getWhoRepresent() != null) {
					q.setParameter("whorepresent", entity.getWhoRepresent());
				}
			}

			return q.getResultList();
		} catch (Exception e) {
			logger.log(
					Level.SEVERE,
					"Error obteniendo buscando los ExternalProviders por externalProvider",
					e);
			throw super.generateGHAEJBException(
					"externalProvider-findByExternalProvider-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public ExternalProvider find(long Id) throws GHAEJBException {
		try {
			return em.find(ExternalProvider.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding ExternalProvider", e);
			throw super.generateGHAEJBException("externalProvider-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<ExternalProvider> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("ExternalProvider.getAll",
					ExternalProvider.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all ExternalProviders",
					ex);
			throw super.generateGHAEJBException("externalProvider-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public ExternalProvider save(ExternalProvider entity)
			throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(ExternalProvider.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving ExternalProvider " + entity.toString(), e);
			throw super.generateGHAEJBException("externalProvider-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public ExternalProvider update(ExternalProvider entity)
			throws GHAEJBException {
		try {
			ExternalProvider res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update ExternalProvider "
					+ entity.toString(), e);
			throw super.generateGHAEJBException("externalProvider-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}