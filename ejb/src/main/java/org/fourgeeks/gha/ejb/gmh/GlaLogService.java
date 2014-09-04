package org.fourgeeks.gha.ejb.gmh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;
import org.fourgeeks.gha.ejb.pdt.PDTMessageProducerLocal;

/**
 * Session Bean implementation class GlaLogService
 */
@Stateless
public class GlaLogService extends GHAEJBExceptionService implements
		GlaLogServiceRemote {
	@PersistenceContext
	private EntityManager em;

	@EJB
	private PDTMessageProducerLocal pdtProducerService;

	private final static Logger logger = Logger.getLogger(GlaLogService.class
			.getName());

	@Override
	public boolean delete(long Id) throws GHAEJBException {
		try {
			GlaLog entity = em.find(GlaLog.class, Id);
			em.remove(entity);
			return true;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiaDamageReport = "
					+ GlaLog.class.getName() + " with id=" + Long.toString(Id),
					e);
			throw super.generateGHAEJBException("eia-delete-fail", em);
		}
	}

	@Override
	public List<GlaLog> findByEiaType(EiaType eiaType) throws GHAEJBException {
		try {
			ArrayList<EiaStateEnum> stateList = new ArrayList<EiaStateEnum>();
			stateList.add(EiaStateEnum.DAMAGED);
			stateList.add(EiaStateEnum.MAINTENANCE);

			String stringQuery = "SELECT edr, eia FROM GlaLog edr RIGHT JOIN edr.eia eia "
					+ "WHERE eia.eiaType = :eiaType AND eia.state IN :eiaStates order by edr.id";
			List<?> resultList = em.createQuery(stringQuery)
					.setParameter("eiaType", eiaType)
					.setParameter("eiaStates", stateList).getResultList();

			List<GlaLog> edrList = toEiaDamageReportList(resultList);
			return edrList;
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding eiaDamageReport by eiatype",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail", em);
		}
	}

	@Override
	public List<GlaLog> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("GlaLog.getAll", GlaLog.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiaDamageReports",
					ex);
			throw super.generateGHAEJBException("eia-getAll-fail", em);
		}
	}

	@Override
	public GlaLog save(GlaLog glaLog) throws GHAEJBException {

		try {
			// guardando el damageReport en BD
			em.persist(glaLog);
			em.flush();

			// definiendo parametros para el PDT
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("eiaDamageReport", glaLog);
			// enviando el mensaje a la cola de mensajes del PDT
			pdtProducerService.sendMessage("corrective-maintenance", params);

			return em.find(GlaLog.class, glaLog.getId());
		} catch (Exception e) {

			logger.log(Level.INFO, "ERROR: saving eiaDamageReport ", e);
			throw super.generateGHAEJBException("eia-save-fail", em);

		}
	}

	private List<GlaLog> toEiaDamageReportList(List<?> resultList) {
		ArrayList<GlaLog> edrList = new ArrayList<GlaLog>();

		for (Object elem : resultList) {
			Object[] aux = (Object[]) elem;

			GlaLog edrFinal = new GlaLog();

			if (aux[0] != null) {
				GlaLog edr = (GlaLog) aux[0];
				edrFinal.setDamageMotive(edr.getDamageMotive());
				edrFinal.setDamageStatus(edr.getDamageStatus());
				edrFinal.setDateTimestamp(edr.getDateTimestamp());
				edrFinal.setId(edr.getId());
				edrFinal.setPriority(edr.getPriority());
				edrFinal.setUserWhoRegistered(edr.getUserWhoRegistered());

			}

			edrList.add(edrFinal);
		}
		return edrList;
	}

	@Override
	public GlaLog update(GlaLog glaLog) throws GHAEJBException {
		try {
			GlaLog res = em.merge(glaLog);
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiaDamageReport ",
					e);
			throw super.generateGHAEJBException("eia-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
