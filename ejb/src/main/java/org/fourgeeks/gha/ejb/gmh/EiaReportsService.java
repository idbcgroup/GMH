package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;

@Stateless(name = "gmh.EiaReportsService")
public class EiaReportsService implements EiaReportsServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaReportsService.class.getName());

	@Override
	public List<Eia> findAll(List<Long> facilityIds, List<Long> workAreaIds,
			EiaStateEnum eiaState, boolean orderByUbicEiaType)
			throws GHAEJBException {

		String query = "SELECT eia FROM Eia eia "
				+ "WHERE facility.id IN :facilityIds OR workingArea.id IN :workAreaIds ";

		if (eiaState != null)
			query += "AND state = :eiaState";

		query += orderByUbicEiaType ? " ORDER BY facility.name, workingArea.name, eiaType.name"
				: " ORDER BY eiaType.name, facility.name, workingArea.name";

		logger.info("ejecutando el query");

		List<Eia> resultList = null;
		try {
			TypedQuery<Eia> typedQuery = em.createQuery(query, Eia.class)
					.setParameter("facilityIds", facilityIds)
					.setParameter("workAreaIds", workAreaIds);

			if (eiaState != null)
				typedQuery.setParameter("eiaState", eiaState);

			resultList = typedQuery.getResultList();

			logger.info("Consulta ejecutada con exito: " + resultList.size()
					+ " equipos");

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error buscando EIAs por conjunto de IDs",
					ex);
			throw new EJBException("Error buscando EIAs por conjunto de IDs: "
					+ ex.getCause().getMessage());
		}

		return resultList;
	}

	@Override
	public List<Eia> find(List<Long> eiaIds, boolean orderByUbicEiaType)
			throws GHAEJBException {
		String query = "SELECT eia FROM Eia eia WHERE id IN :eiaIds";

		query += orderByUbicEiaType ? " ORDER BY facility.name, workingArea.name, eiaType.name"
				: " ORDER BY eiaType.name, facility.name, workingArea.name";

		logger.info("ejecutando el query");

		List<Eia> resultList = null;
		try {
			resultList = em.createQuery(query, Eia.class)
					.setParameter("eiaIds", eiaIds).getResultList();

			logger.info("Consulta ejecutada con exito: " + resultList.size()
					+ " equipos");

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error buscando EIAs por conjunto de IDs",
					ex);
			throw new EJBException("Error buscando EIAs por conjunto de IDs: "
					+ ex.getCause().getMessage());
		}

		return resultList;
	}

}
