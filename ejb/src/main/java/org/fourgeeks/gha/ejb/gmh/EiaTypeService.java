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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.EiaTypeService")
public class EiaTypeService implements EiaTypeServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaTypeService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#buildFilters(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public Predicate buildFilters(EiaType eiaType, CriteriaBuilder cb, Root<EiaType> etype) {
		/*Brand brand = eiaType.getBrand();
		Manufacturer manufacturer = eiaType.getManufacturer();

		String filters = "";
		int varsAdded = 0;

		if (brand != null && brand.getId() > 0) {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			++varsAdded;
			filters += "brand='" + Long.toString(eiaType.getBrand().getId())
					+ "' ";
		}

		if (manufacturer != null && manufacturer.getId() > 0) {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			++varsAdded;
			filters += "manufacturer='"
					+ Long.toString(eiaType.getManufacturer().getId()) + "' ";
		}

		if (eiaType.getModel() != null && eiaType.getModel() != "") {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			++varsAdded;
			filters += " lower(model) like '%"
					+ eiaType.getModel().toLowerCase() + "%' ";
		}

		if (eiaType.getName() != null && eiaType.getName() != "") {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			varsAdded++;
			filters += "lower(name) like '%" + eiaType.getName().toLowerCase()
					+ "%' ";
		}

		if (eiaType.getCode() != null && eiaType.getCode() != "") {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			varsAdded++;
			filters += "lower(code) like '%" + eiaType.getCode().toLowerCase()
					+ "%' ";
		}

		return filters;*/
		Predicate criteria = cb.conjunction();
		
		if(eiaType.getBrand() != null){
			ParameterExpression<Brand> p = cb.parameter(Brand.class, "brand");
			criteria = cb.and(criteria, cb.equal(etype.get("brand"), p));
		}
		
		if(eiaType.getCode() != null){
			
		}
		
		if(eiaType.getDescription() != null){
			
		}
		
		if(eiaType.getEiaUmdns() != null){
			
		}
		
		if(eiaType.getManufacturer() != null){
			
		}
		
		if(eiaType.getMobility() != null){
			
		}
		
		if(eiaType.getModel() != null){
			
		}
		
		if(eiaType.getName() != null){
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			criteria = cb.and(criteria, cb.like(cb.lower(etype.<String>get("name")), p));
		}
		
		if(eiaType.getSubtype() != null){
			
		}
		
		if(eiaType.getType() != null){
			
		}
		
		if(eiaType.getUseDescription() != null){
			
		}
		
		return criteria;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#deleteEiaType(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			EiaType entity = em.find(EiaType.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatype", e);
			throw new EJBException("Error eliminando EiaType por id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#find(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType) throws EJBException {

		/*String query = "SELECT e from EiaType e ";
		String filters = buildFilters(eiaType);

		if (filters != "")
			query += " WHERE " + filters;
		query += " order by id";

		try {
			res = em.createQuery(query, EiaType.class).getResultList();
		} catch (NoResultException e) {
			logger.log(Level.INFO, "Find by EiaType: no results", e);
			res = null;
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error obteniendo buscando los eiaTypes por eiatype id="
							+ Long.toString(eiaType.getId()), ex);
			throw new EJBException(
					"Error obteniendo buscando los eiaTypes por eiatype id="
							+ Long.toString(eiaType.getId()));
		}*/
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EiaType> c = cb.createQuery(EiaType.class);
		Root<EiaType> eType = c.from(EiaType.class);
		c.select(eType);
		
		Predicate criteria = buildFilters(eiaType, cb, eType);
		c.where(criteria);
		
		TypedQuery<EiaType> q;
		
		if (criteria.getExpressions().size() <= 0) {
			q = em.createQuery(c);
		}else{
			c.where(criteria);
			q = em.createQuery(c);
			
			if (eiaType.getBrand() != null) {
				q.setParameter("brand", eiaType.getBrand());
			}

			if (eiaType.getCode() != null) {
			}

			if (eiaType.getDescription() != null) {
			}

			if (eiaType.getEiaUmdns() != null) {
			}

			if (eiaType.getManufacturer() != null) {
			}

			if (eiaType.getMobility() != null) {
			}

			if (eiaType.getModel() != null) {
			}

			if (eiaType.getName() != null) {
				q.setParameter("name", "%"+eiaType.getName()+"%");
			}

			if (eiaType.getSubtype() != null) {
			}

			if (eiaType.getType() != null) {
			}

			if (eiaType.getUseDescription() != null) {
			}
		}
		
		return q.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#find(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType, int offset, int size)
			throws EJBException {
		List<EiaType> res = null;

		/*String query = "SELECT e from EiaType e ";
		String filters = buildFilters(eiaType, null, null);

		if (filters != "")
			query += " WHERE " + filters;
		query += " order by id";

		try {
			res = em.createQuery(query, EiaType.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (NoResultException e) {
			logger.log(Level.INFO, "Find by EiaType: no results", e);
			res = null;
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error obteniendo buscando los eiaTypes por eiatype" , ex);
			throw new EJBException(
					"Error obteniendo buscando los eiaTypes por eiatype "
							+ ex.getCause().getMessage());
		}*/
		return res;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getEiaType(long)
	 */
	@Override
	public EiaType find(long Id) throws EJBException {
		try {
			return em.find(EiaType.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando Eiatype por Id ", e);
			throw new EJBException("Error buscando Eiatype por Id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAllEiaType()
	 */
	@Override
	public List<EiaType> getAll() throws EJBException {

		String query = "SELECT e from EiaType e order by id";
		List<EiaType> res = null;
		try {
			res = em.createQuery(query, EiaType.class).getResultList();
		} catch (NoResultException e) {
			logger.log(Level.INFO, "No results", e);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiatypes", ex);
			throw new EJBException("Error obteniendo todos los eiaTypes");
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAll(long, long)
	 */
	@Override
	public List<EiaType> getAll(int offset, int size) throws EJBException {
		List<EiaType> res = null;
		String query = "SELECT e from EiaType e order by id";
		try {
			res = em.createQuery(query, EiaType.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (NoResultException e) {
			logger.log(Level.INFO, "Get All: no results", e);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EitaTypes", ex);
			throw new EJBException(
					"Error obteniendo todos los eiaTypes paginado");
		}

		return res;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeService#saveEiaType(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public EiaType save(EiaType eiaType) throws EJBException {
		try {
			em.persist(eiaType);
			em.flush();
			return em.find(EiaType.class, eiaType.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiatype", e);
			throw new EJBException("Error guardando EiaType: "
					+ e.getCause().getMessage());
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
	public EiaType update(EiaType eiaType) throws EJBException {
		try {
			EiaType res = em.merge(eiaType);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiatype", e);
			throw new EJBException("Error actualizando EiaType " +e.getCause().getMessage());
		}
	}

}
