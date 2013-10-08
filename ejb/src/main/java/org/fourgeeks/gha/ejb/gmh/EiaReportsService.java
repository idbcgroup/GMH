package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;

@Stateless(name = "gmh.EiaReportsService")
public class EiaReportsService implements EiaReportsServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaReportsService.class.getName());

	@Override
	public List<Eia> findByEiaIds(List<Long> eiaIds) throws GHAEJBException {
		List<Eia> resultList = null;

		String query = "SELECT eia FROM Eia eia WHERE id in :eiaIdList ORDER BY id";
		logger.info("ejecutando el query");

		try {
			resultList = em.createQuery(query, Eia.class)
					.setParameter("eiaIdList", eiaIds).getResultList();

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
