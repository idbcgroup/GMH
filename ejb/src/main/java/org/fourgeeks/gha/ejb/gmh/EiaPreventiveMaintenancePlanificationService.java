package org.fourgeeks.gha.ejb.gmh;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class
 * EiaPreventiveMaintenancePlanificationService
 */
@Stateless(name = "gmh.EiaPreventiveMaintenancePlanificationService")
public class EiaPreventiveMaintenancePlanificationService extends
		GHAEJBExceptionImpl implements
		EiaPreventiveMaintenancePlanificationServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaPreventiveMaintenancePlanificationService.class
					.getName());

	public List<EiaPreventiveMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {
		try {
			ArrayList<EiaStateEnum> stateList = new ArrayList<EiaStateEnum>();
			stateList.add(EiaStateEnum.DAMAGED);
			stateList.add(EiaStateEnum.MAINTENANCE);

			String stringQuery = "SELECT pmp FROM EiaPreventiveMaintenancePlanification pmp JOIN pmp.plan plan WHERE plan.eiaType = :eiaType order by pmp.id";
			List<EiaPreventiveMaintenancePlanification> resultList = em
					.createQuery(stringQuery,
							EiaPreventiveMaintenancePlanification.class)
					.setParameter("eiaType", eiaType).getResultList();

			return resultList;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding EiaPreventiveMaintenancePlanification by eiatype",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public EiaPreventiveMaintenancePlanification save(
			EiaPreventiveMaintenancePlanification preventivePlanif)
			throws GHAEJBException {
		try {
			em.persist(preventivePlanif.getPlanification());
			em.persist(preventivePlanif);
			// TODO agregar en tabla log: se creo un mantenimiento preventivo
			em.flush();

			return em.find(EiaPreventiveMaintenancePlanification.class,
					preventivePlanif.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving EiaPreventiveMaintenancePlanification ", e);
			throw super.generateGHAEJBException("eia-save-fail",
					RuntimeParameters.getLang(), em);

		}

	}
}
