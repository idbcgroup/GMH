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

	private Predicate buildEiaStatePredic(Root<Eia> eiaRoot,
			List<EiaStateEnum> eiaStates) {

		if (eiaStates.isEmpty())
			return eiaRoot.get("state").in(EiaStateEnum.valuesList());

		return eiaRoot.get("state").in(eiaStates);
	}

	private Predicate buildEiaStatePredic(Root<Eia> eiaRoot,
			EiaStateEnum eiaState) {
		ArrayList<EiaStateEnum> list = new ArrayList<EiaStateEnum>();
		if (eiaState != null)
			list.add(eiaState);

		return buildEiaStatePredic(eiaRoot, list);
	}

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

	@Override
	@SuppressWarnings("unchecked")
	public List<Eia> find(List<Long> eiaIds, boolean orderByUbicEiaType)
			throws GHAEJBException {

		// CONSTRUYENDO QUERY
		// creando los builders para construir el query
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Eia> cQuery = builder.createQuery(Eia.class);
		// definiendo joins
		Root<Eia> eiaRoot = cQuery.from(Eia.class);
		Join<Eia, EiaType> eiaTypeJoin = eiaRoot.join("eiaType");
		Join<Eia, WorkingArea> workAreaJoin = eiaRoot.join("workingArea", JoinType.LEFT);
		Join<Eia, Facility> facilityJoin = eiaRoot.join("facility",	JoinType.LEFT);

		// creando el predicando de la consulta
		Predicate eiaIdsPred = buildPredicList(eiaIds, eiaRoot);

		// creando el query
		CriteriaQuery<Eia> typeQuery = cQuery.select(eiaRoot).where(eiaIdsPred);

		// creando el order by
		if (orderByUbicEiaType)
			typeQuery.orderBy(getListOrderByName(facilityJoin, workAreaJoin, eiaTypeJoin));
		else
			typeQuery.orderBy(getListOrderByName(eiaTypeJoin, facilityJoin, workAreaJoin));

		// EJECUTANDO QUERY
		try {
			return em.createQuery(cQuery).getResultList();

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error buscando EIAs por conjunto de IDs", ex);
			throw new EJBException("Error buscando EIAs por conjunto de IDs: "
					+ ex.getCause().getMessage());
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Eia> findAll(List<Long> facilityIds, List<Long> workAreaIds,
			EiaStateEnum eiaState, boolean orderByUbicEiaType)
			throws GHAEJBException {

		// CONSTRUYENDO QUERY
		// creando los builders para construir el query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Eia> criteriaQuery = cb.createQuery(Eia.class);

		// definiendo joins
		Root<Eia> eiaRoot = criteriaQuery.from(Eia.class);
		Join<Eia, EiaType> eiaTypeJoin = eiaRoot.join("eiaType");
		Join<Eia, WorkingArea> workAreaJoin = eiaRoot.join("workingArea", JoinType.LEFT);
		Join<Eia, Facility> facilityJoin = eiaRoot.join("facility", JoinType.LEFT);

		// creando predicandos para la consulta
		Predicate facilityIdsPred = buildPredicList(facilityIds, facilityJoin);
		Predicate workAreaIdsPred = buildPredicList(workAreaIds, workAreaJoin);
		Predicate eiaStatePred = buildEiaStatePredic(eiaRoot, eiaState);

		// creando del query
		CriteriaQuery<Eia> typeQuery = criteriaQuery.select(eiaRoot).where(facilityIdsPred, 
				workAreaIdsPred, eiaStatePred);

		// creando el orden by
		if (orderByUbicEiaType)
			typeQuery.orderBy(getListOrderByName(facilityJoin, workAreaJoin, eiaTypeJoin));
		else
			typeQuery.orderBy(getListOrderByName(eiaTypeJoin, facilityJoin, workAreaJoin));

		// EJECUTANDO QUERY
		try {
			return em.createQuery(typeQuery).getResultList();

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error buscando EIAs por conjunto de IDs", ex);
			throw new EJBException("Error buscando EIAs por conjunto de IDs: "
					+ ex.getCause().getMessage());
		}

		return null;
	}

	private List<Order> getListOrderByName(From<Eia, ?>... entities) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		List<Order> listOrder = new ArrayList<Order>();

		for (From<Eia, ?> entity : entities)
			listOrder.add(builder.asc(entity.<String> get("name")));

		return listOrder;
	}
}
