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
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class EiaDamageReportService
 */
@Stateless
public class EiaDamageReportService extends GHAEJBExceptionService implements
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
			throw super.generateGHAEJBException("eia-delete-fail", em);
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
			throw super.generateGHAEJBException("eia-findByEiaType-fail", em);
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
			throw super.generateGHAEJBException("eia-getAll-fail", em);
		}
	}

	@Override
	public EiaDamageReport save(EiaDamageReport eiaDamageReport)
			throws GHAEJBException {

		try {
			// TODO pasar esto al PDT
			// Desabilitando el equipo. Cambiando su estado a DAÑADO:
			// Eia eia = eiaDamageReport.getEia();
			// eia.setState(EiaStateEnum.DAMAGED);

			// Creando un nuevo mantenimiento correctivo:
			// EiaCorrectiveMaintenance cmp = new EiaCorrectiveMaintenance();
			// cmp.setDamageReport(eiaDamageReport);
			// cmp.setDescription(eiaDamageReport.getDamageMotive());
			//
			// em.merge(eia);
			// em.persist(cmp);

			em.persist(eiaDamageReport);
			em.flush();

			return em.find(EiaDamageReport.class, eiaDamageReport.getId());
		} catch (Exception e) {

			logger.log(Level.INFO, "ERROR: saving eiaDamageReport ", e);
			throw super.generateGHAEJBException("eia-save-fail", em);

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
				edrFinal.setDateTimestamp(edr.getDateTimestamp());
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
