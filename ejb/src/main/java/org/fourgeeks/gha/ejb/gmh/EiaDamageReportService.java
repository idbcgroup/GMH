package org.fourgeeks.gha.ejb.gmh;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class EiaDamageReportService
 */
@Stateless(name = "gmh.EiaDamageReportService")
public class EiaDamageReportService extends GHAEJBExceptionImpl implements
		EiaDamageReportServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaDamageReportService.class.getName());

	@Override
	public boolean delete(long Id) throws GHAEJBException {
		try {
			EiaDamageReport entity = em.find(EiaDamageReport.class, Id);
			em.remove(entity);
			return true;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete eiaDamageReport = "
							+ EiaDamageReport.class.getName() + " with id="
							+ Long.toString(Id), e);
			throw super.generateGHAEJBException("eia-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<EiaDamageReport> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			ArrayList<EiaStateEnum> stateList = new ArrayList<EiaStateEnum>();
			stateList.add(EiaStateEnum.DAMAGED);
			stateList.add(EiaStateEnum.MAINTENANCE);

			String stringQuery = "SELECT edr, eia FROM EiaDamageReport edr RIGHT JOIN edr.eia eia WHERE eia.eiaType = :eiaType AND eia.state IN :eiaStates order by edr.id";
			List<?> resultList = em.createQuery(stringQuery)
					.setParameter("eiaType", eiaType)
					.setParameter("eiaStates", stateList).getResultList();

			List<EiaDamageReport> edrList = toEiaDamageReportList(resultList);
			return edrList;
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding eiaDamageReport by eiatype",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<EiaDamageReport> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaDamageReport.getAll",
					EiaDamageReport.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiaDamageReports",
					ex);
			throw super.generateGHAEJBException("eia-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	private EiaCorrectiveMaintenancePlanification getEiaCorrectiveMaintenancePlanification(
			Eia eia) {
		EiaCorrectiveMaintenancePlanification planification = new EiaCorrectiveMaintenancePlanification();
		EiaMaintenancePlanification maintenancePlanification = new EiaMaintenancePlanification();
		maintenancePlanification.setEia(eia);
		java.util.Date date = new java.util.Date();
		maintenancePlanification.setScheduledDate(new Date(date.getTime()));
		planification.setPlanification(maintenancePlanification);
		maintenancePlanification
				.setType(MaintenancePlanificationType.CORRECTIVE);

		return planification;
	}

	@Override
	public EiaDamageReport save(EiaDamageReport eiaDamageReport)
			throws GHAEJBException {
		Eia eia = eiaDamageReport.getEia();
		EiaCorrectiveMaintenancePlanification cmp = getEiaCorrectiveMaintenancePlanification(eia);

		try {
			em.merge(eia);
			em.persist(eiaDamageReport);
			em.persist(cmp.getPlanification());
			em.persist(cmp);
			// TODO agregar en tabla log: se creo un mantenimiento correctivo
			em.flush();

			return em.find(EiaDamageReport.class, eiaDamageReport.getId());
		} catch (Exception e) {

			logger.log(Level.INFO, "ERROR: saving eiaDamageReport ", e);
			throw super.generateGHAEJBException("eia-save-fail",
					RuntimeParameters.getLang(), em);

		}
	}

	private List<EiaDamageReport> toEiaDamageReportList(List<?> resultList) {
		ArrayList<EiaDamageReport> edrList = new ArrayList<EiaDamageReport>();

		for (Object elem : resultList) {
			Object[] aux = (Object[]) elem;

			EiaDamageReport edrFinal = new EiaDamageReport();
			edrFinal.setEia((Eia) aux[1]);
			if (aux[0] != null) {
				EiaDamageReport edr = (EiaDamageReport) aux[0];
				edrFinal.setDamageMotive(edr.getDamageMotive());
				edrFinal.setDamageStatus(edr.getDamageStatus());
				edrFinal.setDateTimeDamage(edr.getDateTimeDamage());
				edrFinal.setId(edr.getId());
				edrFinal.setPriority(edr.getPriority());
				edrFinal.setUserWhoRegistered(edr.getUserWhoRegistered());
				edrFinal.setUserWhoReported(edr.getUserWhoReported());
			}

			edrList.add(edrFinal);
		}
		return edrList;
	}

	@Override
	public EiaDamageReport update(EiaDamageReport eiaDamageReport)
			throws GHAEJBException {
		try {
			EiaDamageReport res = em.merge(eiaDamageReport);
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiaDamageReport ",
					e);
			throw super.generateGHAEJBException("eia-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
