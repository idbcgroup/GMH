/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.EiaTypeService")
public class EiaTypeService implements EiaTypeServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final Logger logger = Logger.getLogger(EiaTypeService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeService#saveEiaType(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public void save(EiaType eiaType) {
		try {
			em.persist(eiaType);
		} catch (Exception e) {
			logger.info("ERROR: saving object " + eiaType.toString());
			e.printStackTrace();
			// TODO: send exception to webClient
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getEiaType(long)
	 */
	@Override
	public EiaType find(long Id) {
		try {
			return em.find(EiaType.class, Id);
		} catch (Exception e) {
			// TODO: send exception to webClient
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#deleteEiaType(long)
	 */
	@Override
	public void delete(long Id) {
		try {
			EiaType entity = em.find(EiaType.class, Id);
			em.remove(entity);

			logger.info("Deleted: " + entity.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR: unable to delete object="+EiaType.class.getName() +" with id="
					+ Long.toString(Id));
			// TODO: send exception to webClient
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeService#updateEiaType(org.fourgeeks.
	 * gha.domain.gmh.EiaType)
	 */
	@Override
	public void update(EiaType eiaType) {
		try {
			em.merge(eiaType);
		} catch (Exception e) {
			logger.info("ERROR: unable to update object " + eiaType.toString());
			e.printStackTrace();
			// TODO: send exception to webClient
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAllEiaType()
	 */
	@Override
	public List<EiaType> getAll() {
		String query = "SELECT e from EiaType e order by id";
		List<EiaType> res = null;
		try {
			res = em.createQuery(query, EiaType.class).getResultList();
			logger.info("Get all eiatypes");
		} catch (NoResultException e) {
			logger.info("No results");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiatypes", ex);

			// TODO: send exception to webClient
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAll(long, long)
	 */
	@Override
	public List<EiaType> getAll(int offset, int size) {
		List<EiaType> res = null;
		String query = "SELECT e from EiaType e order by id";
		try {
			res = em.createQuery(query, EiaType.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (NoResultException e) {
			logger.info("Get All: no results");
			res = null;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EitaTypes", ex);
			res = null;
		}

		return res;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#find(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType, int offset, int size) {
		List<EiaType> res = null;

		String query = "SELECT e from EiaType e ";
		String filters = buildFilters(eiaType);
		
		if(filters != "")query += " WHERE " +filters;
		query += " order by id";

		try {
			res = em.createQuery(query, EiaType.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (NoResultException e) {
			logger.info("Find by EiaType: no results");
			res = null;
		} catch (Exception ex) {
			ex.printStackTrace();
			res = null;
		}
		return res;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#find(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType) {
		List<EiaType> res = null;

		String query = "SELECT e from EiaType e ";
		String filters = buildFilters(eiaType);
		
		if(filters != "")query += " WHERE " +filters;
		query += " order by id";

		try {
			res = em.createQuery(query, EiaType.class).getResultList();
		} catch (NoResultException e) {
			logger.info("Find by EiaType: no results");
			res = null;
		} catch (Exception ex) {
			ex.printStackTrace();
			res = null;
		}
		return res;

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#buildFilters(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public String buildFilters(EiaType eiaType) {
		Brand brand = eiaType.getBrand();
		Manufacturer manufacturer = eiaType.getManufacturer();
		
		String filters = "";
		int varsAdded = 0;
		
		if (brand != null && brand.getId() > 0) {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			++varsAdded;
			filters += "brand='" + Long.toString(eiaType.getBrand().getId())
					+ "' ";
		}

		if (manufacturer != null && manufacturer.getId() > 0) {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			++varsAdded;
			filters += "manufacturer='"
					+ Long.toString(eiaType.getManufacturer().getId()) + "' ";
		}

		if (eiaType.getModel() != null && eiaType.getModel() != "") {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			++varsAdded;
			filters += " lower(model) like '%" + eiaType.getModel().toLowerCase() + "%' ";
		}

		if (eiaType.getName() != null && eiaType.getName() != "") {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			varsAdded++;
			filters += "lower(name) like '%" + eiaType.getName().toLowerCase() + "%' ";
		}

		if (eiaType.getCode() != null && eiaType.getCode() != "") {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			varsAdded++;
			filters += "lower(code) like '%" + eiaType.getCode().toLowerCase() + "%' ";
		}
		
		return filters;
	}

}
