package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class MaintenanceProtocolsService
 * 
 * @author naramirez
 */
@Stateless
public class MaintenanceProtocolsService extends GHAEJBExceptionImpl implements
		MaintenanceProtocolsServiceRemote {
	private final static Logger logger = Logger
			.getLogger(MaintenanceProtocolsService.class.getName());

	@PersistenceContext
	EntityManager em;

	@EJB
	MaintenanceSubProtocolServiceLocal subProtocolService;

	@EJB
	MaintenancePlanServiceRemote planService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#copyActivities
	 * (org.fourgeeks.gha.domain.gmh.MaintenancePlan,
	 * org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void copyActivities(MaintenancePlan planFrom, MaintenancePlan planTo)
			throws GHAEJBException {
		try {
			List<MaintenanceProtocols> protocolForm = findByMaintenancePlan(planFrom);
			List<MaintenanceProtocols> protocolTo = findByMaintenancePlan(planTo);

			// busco el ordinal de la ultima actividad del plan
			int maxOrdinal = Integer.MIN_VALUE;
			for (MaintenanceProtocols entity : protocolTo)
				if (entity.getOrdinal() > maxOrdinal)
					maxOrdinal = entity.getOrdinal();

			// obtengo la lista de activiades que voy a copiar y la ordeno
			List<MaintenanceActivity> activities = new ArrayList<MaintenanceActivity>();
			for (MaintenanceProtocols entity : protocolForm)
				activities.add(entity.getMaintenanceActivity());
			Collections.sort(activities);

			// verifico que la actividad no este ya agregada y la agrego
			for (MaintenanceProtocols entity : protocolForm) {
				MaintenanceActivity activity = entity.getMaintenanceActivity();
				int result = Collections.binarySearch(activities, activity);

				if (result == 0) {
					MaintenanceProtocols protocol = new MaintenanceProtocols();
					protocol.setMaintenanceActivity(activity);
					protocol.setMaintenancePlan(planTo);
					protocol.setOrdinal(++maxOrdinal);

					em.persist(protocol);
				}
			}

			em.flush();

		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenanceProtocols", e);
			throw super.generateGHAEJBException(
					"MaintenanceProtocols-findByMaintenancePlan-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#delete(long)
	 */
	@Override
	public void delete(long id) throws GHAEJBException {
		try {
			MaintenanceProtocols entity = em.find(MaintenanceProtocols.class,
					id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete MaintenanceProtocol", e);
			throw super.generateGHAEJBException(
					"aintenanceProtocol-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#
	 * deleteByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public int deleteByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException {
		try {
			String stringQuery = "DELETE FROM MaintenanceProtocols mp WHERE mp.maintenancePlan = :plan";
			int entitiesDeleted = em.createQuery(stringQuery)
					.setParameter("plan", plan).executeUpdate();

			return entitiesDeleted;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"ERROR: unable to delete MaintenanceProtocol by MaintenancePlan",
					e);
			throw super.generateGHAEJBException(
					"maintenanceProtocol-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#
	 * findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenanceProtocols> findByMaintenancePlan(MaintenancePlan plan)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceProtocols.findByMaintenancePlan",
							MaintenanceProtocols.class)
					.setParameter("maintenancePlan", plan).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenanceProtocols", e);
			throw super.generateGHAEJBException(
					"MaintenanceProtocols-findByMaintenancePlan-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#getStadisticInfo
	 * (org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenanceProtocolStadisticData getStadisticInfo(
			MaintenancePlan mantenancePlan) throws GHAEJBException {
		try {
			long totalDuration, numberActivities, numberSubProtocols, numberSubProtocolActivities;
			totalDuration = numberActivities = numberSubProtocols = numberSubProtocolActivities = 0;
			BigDecimal totalCost;

			MaintenancePlanStadisticData planData = planService
					.getStadisticInfo(mantenancePlan);

			totalDuration += planData.getEstimatedDuration();
			totalCost = planData.getEstimatedCost();

			List<MaintenanceProtocols> protocol = findByMaintenancePlan(mantenancePlan);
			for (MaintenanceProtocols entity : protocol) {
				MaintenanceActivity activity = entity.getMaintenanceActivity();

				if (activity.getIsSubProtocol()) {
					numberSubProtocols++;
					numberSubProtocolActivities += subProtocolService
							.getSubProtocolActivitiesCount(activity);
				} else
					numberActivities++;
			}

			MaintenanceProtocolStadisticData data = new MaintenanceProtocolStadisticData();
			data.setEstimatedCost(totalCost);
			data.setEstimatedDuration(totalDuration);
			data.setPot(TimePeriodEnum.DAYS);
			data.setNumberActivities(numberActivities);
			data.setNumberSubProtocols(numberSubProtocols);
			data.setNumberSubProtocolsActivities(numberSubProtocolActivities);

			return data;

		} catch (Exception e) {
			String stringMsg = "ERROR: geting stadistic information from the MaintenanceProtocol";
			logger.log(Level.INFO, stringMsg, e);

			String messageCode = "maintenanceProtocol-getStadisticInfo-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#save(org.
	 * fourgeeks.gha.domain.gmh.MaintenanceActivity,
	 * org.fourgeeks.gha.domain.gmh.MaintenancePlan, int)
	 */
	@Override
	public MaintenanceProtocols save(MaintenanceActivity activity,
			MaintenancePlan plan, int ordinal) throws GHAEJBException {
		try {
			MaintenanceProtocols protocol = new MaintenanceProtocols();
			protocol.setMaintenanceActivity(activity);
			protocol.setMaintenancePlan(plan);
			protocol.setOrdinal(ordinal);

			em.persist(protocol);
			em.flush();

			return em.find(MaintenanceProtocols.class, protocol.getId());

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceProtocol ", e);
			throw super.generateGHAEJBException(
					"maintenanceProtocol-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
