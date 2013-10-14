package org.fourgeeks.gha.ejb.gmh;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

@Stateless(name = "gmh.EiaReportsService")
public class EiaReportsService implements EiaReportsServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaReportsService.class.getName());

	/**
	 * Devuelve un predicado para una lista de estados de equipos
	 * 
	 * @param eiaRoot
	 *            la entidad {@link Eia} sobre la que se le va a plicar el
	 *            predicado
	 * @param eiaStates
	 *            Lista de {@link EiaStateEnum} con los estados de equipos
	 *            deseados. puede ser vacia
	 * @return {@link Predicate} para la lista o para todos los estados de
	 *         equipos, en caso de que la lista este vacia
	 */
	private Predicate buildEiaStatePredic(Root<Eia> eiaRoot,
			List<EiaStateEnum> eiaStates) {

		if (eiaStates.isEmpty())
			return eiaRoot.get("state").in(EiaStateEnum.valuesList());

		return eiaRoot.get("state").in(eiaStates);
	}

	/**
	 * Devuelve un predicado para un estado de equipo
	 * 
	 * @param eiaRoot
	 *            la entidad eia sobre la que se le va a plicar el predicado
	 * @param eiaState
	 *            el estado del equipo
	 * @return El predicado deseado
	 */
	private Predicate buildEiaStatePredic(Root<Eia> eiaRoot,
			EiaStateEnum eiaState) {
		ArrayList<EiaStateEnum> list = new ArrayList<EiaStateEnum>();
		if (eiaState != null)
			list.add(eiaState);

		return buildEiaStatePredic(eiaRoot, list);
	}

	/**
	 * Devuelve un predicado con los IDs de la lista
	 * 
	 * @param listIds
	 *            lista de IDs, puede ser null
	 * @param from
	 *            la entidad sobre la que va a actuar el predicado
	 * @return El predicado deseado
	 */
	private Predicate buildPredicList(List<Long> listIds, From<Eia, ?> from) {
		CriteriaBuilder builder = em.getCriteriaBuilder();

		if (listIds != null) {
			Predicate[] predicArray = new Predicate[listIds.size()];
			for (int i = 0, size = listIds.size(); i < size; i++)
				predicArray[i] = builder.equal(from.get("id"), listIds.get(i));
			return builder.or(predicArray);
		}

		return builder.conjunction();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findEias(java.util.
	 * List, boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Eia> findEias(List<Long> eiaIds, boolean orderByUbicEiaType)
			throws GHAEJBException {

		// CONSTRUYENDO QUERY
		// creando los builders para construir el query
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Eia> cQuery = builder.createQuery(Eia.class);
		// definiendo joins
		Root<Eia> eiaRoot = cQuery.from(Eia.class);
		Join<Eia, EiaType> eiaTypeJoin = eiaRoot.join("eiaType");
		Join<Eia, WorkingArea> workAreaJoin = eiaRoot.join("workingArea",
				JoinType.LEFT);
		Join<Eia, Facility> facilityJoin = eiaRoot.join("facility",
				JoinType.LEFT);

		// creando el predicando de la consulta
		Predicate eiaIdsPred = buildPredicList(eiaIds, eiaRoot);

		// creando el query
		CriteriaQuery<Eia> typeQuery = cQuery.select(eiaRoot).where(eiaIdsPred);

		// creando el order by
		if (orderByUbicEiaType)
			typeQuery.orderBy(getListOrderByName(facilityJoin, workAreaJoin,
					eiaTypeJoin));
		else
			typeQuery.orderBy(getListOrderByName(eiaTypeJoin, facilityJoin,
					workAreaJoin));

		// EJECUTANDO QUERY
		try {
			return em.createQuery(cQuery).getResultList();

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error buscando EIAs por conjunto de IDs",
					ex);
			throw new EJBException("Error buscando EIAs por conjunto de IDs: "
					+ ex.getCause().getMessage());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findAllEias(java.util
	 * .List, java.util.List, org.fourgeeks.gha.domain.enu.EiaStateEnum,
	 * boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Eia> findAllEias(List<Long> facilityIds,
			List<Long> workAreaIds, EiaStateEnum eiaState,
			boolean orderByUbicEiaType) throws GHAEJBException {

		// CONSTRUYENDO QUERY
		// creando los builders para construir el query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Eia> criteriaQuery = cb.createQuery(Eia.class);

		// definiendo joins
		Root<Eia> eiaRoot = criteriaQuery.from(Eia.class);
		Join<Eia, EiaType> eiaTypeJoin = eiaRoot.join("eiaType");
		Join<Eia, WorkingArea> workAreaJoin = eiaRoot.join("workingArea",
				JoinType.LEFT);
		Join<Eia, Facility> facilityJoin = eiaRoot.join("facility",
				JoinType.LEFT);

		// creando predicandos para la consulta
		Predicate facilityIdsPred = buildPredicList(facilityIds, facilityJoin);
		Predicate workAreaIdsPred = buildPredicList(workAreaIds, workAreaJoin);
		Predicate eiaStatePred = buildEiaStatePredic(eiaRoot, eiaState);

		// creando del query
		CriteriaQuery<Eia> typeQuery = criteriaQuery.select(eiaRoot).where(
				facilityIdsPred, workAreaIdsPred, eiaStatePred);

		// creando el orden by
		if (orderByUbicEiaType)
			typeQuery.orderBy(getListOrderByName(facilityJoin, workAreaJoin,
					eiaTypeJoin));
		else
			typeQuery.orderBy(getListOrderByName(eiaTypeJoin, facilityJoin,
					workAreaJoin));

		// EJECUTANDO QUERY
		try {
			return em.createQuery(typeQuery).getResultList();

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error buscando EIAs por conjunto de IDs",
					ex);
			throw new EJBException("Error buscando EIAs por conjunto de IDs: "
					+ ex.getCause().getMessage());
		}

		return null;
	}

	/**
	 * Devuelve lista de ordenadores por nombre en base a las entidades pasadas
	 * por parametros
	 * 
	 * @param entities
	 *            entidades que van a ser utilizadas en el ORDER BY
	 * @return lista de ordenadores para la consulta
	 */
	private List<Order> getListOrderByName(From<Eia, ?>... entities) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		List<Order> listOrder = new ArrayList<Order>();

		for (From<Eia, ?> entity : entities)
			listOrder.add(builder.asc(entity.<String> get("name")));

		return listOrder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaReportsServiceRemote#findEiaTypes(java.util
	 * .List)
	 */
	@Override
	public List<EiaType> findEiaTypes(List<String> eiaTypeCodes)
			throws GHAEJBException {
		try {
			if (eiaTypeCodes == null) {
				String stringQuery = "SELECT e from EiaType e order by e.name";
				return em.createQuery(stringQuery, EiaType.class)
						.getResultList();
			} else {
				String queryString = "SELECT e from EiaType e where e.code in :codeList order by e.name";
				return em.createQuery(queryString, EiaType.class)
						.setParameter("codeList", eiaTypeCodes).getResultList();
			}
		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving eiaTypes", ex);
			throw new GHAEJBException("Error obteniendo todos los eiaTypes");
		}

		return null;
	}
}
