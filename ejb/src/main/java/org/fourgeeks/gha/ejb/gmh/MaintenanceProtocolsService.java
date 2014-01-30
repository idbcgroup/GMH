package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.GHAUtil;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class MaintenanceProtocolsService
 * 
 * @author naramirez
 */
@Stateless
public class MaintenanceProtocolsService extends GHAEJBExceptionService
		implements MaintenanceProtocolsServiceRemote {
	private final static Logger logger = Logger
			.getLogger(MaintenanceProtocolsService.class.getName());

	@PersistenceContext
	EntityManager em;

	@EJB
	MaintenancePlanServiceRemote planService;

	@EJB
	SubProtocolAndCheklistServiceLocal subProtocolService;

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
			// obtengo las listas de actividades de los planes
			TypedQuery<MaintenanceActivity> queryAct = em.createNamedQuery(
					"MaintenanceActivity.findByMaintenancePlan",
					MaintenanceActivity.class);

			List<MaintenanceActivity> activitiesTo = queryAct.setParameter(
					"plan", planTo).getResultList();

			List<MaintenanceActivity> activitiesFrom = queryAct.setParameter(
					"plan", planFrom).getResultList();

			// filtro la lista de actividades a copiar para descartar
			// concidencias
			List<AbstractEntity> entitiesToCopy = GHAUtil
					.binarySearchFilterEntity(activitiesFrom, activitiesTo);

			// obtengo el ultimo ordinal del protocolo del plan
			TypedQuery<Integer> queryOrd = em.createNamedQuery(
					"MaintenanceProtocols.getLastOrdinal", Integer.class);

			List<Integer> ordinalList = queryOrd.setParameter("plan", planTo)
					.getResultList();

			int ordinal = ordinalList.isEmpty() ? 0 : ordinalList.get(0);

			// agrego las actividades al plan deseado
			for (AbstractEntity entity : entitiesToCopy) {
				ordinal++;
				MaintenanceActivity activity = (MaintenanceActivity) entity;
				MaintenanceProtocols protocol = new MaintenanceProtocols(
						planTo, activity, ordinal);
				em.persist(protocol);
			}

			em.flush(); // sincronizo con la BD

		} catch (Exception e) {
			final String msgError = "Error: copying the activities of a MaintenancePlan to another MaintenancePlan";
			logger.log(Level.INFO, msgError, e);

			final String messageCode = "maintenanceProtocol-copyActivities-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#delete(java
	 * .util.List)
	 */
	@Override
	public void delete(List<MaintenanceProtocols> entities)
			throws GHAEJBException {
		try {
			if (entities.isEmpty())
				return;

			// elimino las activiades seleccionadas
			MaintenancePlan plan = entities.get(0).getMaintenancePlan();
			for (MaintenanceProtocols entity : entities) {
				MaintenanceProtocols ent = em.find(MaintenanceProtocols.class,
						entity.getId());
				em.remove(ent);
			}

			// actualizo el orden de las actividades restantes
			final List<MaintenanceProtocols> remainEntities = findByMaintenancePlan(plan);
			for (int i = 0, ord = 1, size = remainEntities.size(); i < size; i++, ord++) {
				MaintenanceProtocols entity = remainEntities.get(i);
				entity.setOrdinal(ord);
				em.merge(entity);
			}
			em.flush(); // sincronizo con la BD

		} catch (Exception e) {
			final String msgError = "ERROR: unable to delete the list of MaintenanceProtocol";
			logger.log(Level.INFO, msgError, e);

			final String messageCode = "maintenanceProtocol-delete-fail";
			throw super.generateGHAEJBException(messageCode,
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
			MaintenanceProtocols ent = em.find(MaintenanceProtocols.class, id);
			final MaintenancePlan plan = ent.getMaintenancePlan();

			em.remove(ent);

			// actualizo el orden de las actividades restantes
			final List<MaintenanceProtocols> remainEntities = findByMaintenancePlan(plan);
			for (int i = 0, ord = 1, size = remainEntities.size(); i < size; i++, ord++) {
				MaintenanceProtocols entity = remainEntities.get(i);
				entity.setOrdinal(ord);
				em.merge(entity);
			}
			em.flush(); // sincronizo con la BD

		} catch (Exception e) {
			final String msgError = "ERROR: unable to delete MaintenanceProtocol";
			logger.log(Level.INFO, msgError, e);

			final String messageCode = "maintenanceProtocol-delete-fail";
			throw super.generateGHAEJBException(messageCode,
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
			int deletedEntities = em.createQuery(stringQuery)
					.setParameter("plan", plan).executeUpdate();

			return deletedEntities;
		} catch (Exception e) {
			final String msgError = "ERROR: unable to delete MaintenanceProtocol by MaintenancePlan";
			logger.log(Level.INFO, msgError, e);

			final String messageCode = "maintenanceProtocol-delete-fail";
			throw super.generateGHAEJBException(messageCode,
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
			final TypedQuery<MaintenanceProtocols> query = em.createNamedQuery(
					"MaintenanceProtocols.findByMaintenancePlan",
					MaintenanceProtocols.class);

			return query.setParameter("plan", plan).getResultList();

		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenanceProtocols", e);
			throw super.generateGHAEJBException(
					"maintenanceProtocol-findByMaintenancePlan-fail",
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
				MaintenanceActivity mActivity = entity.getMaintenanceActivity();
				Activity activity = mActivity.getActivity();

				if (activity.getIsSubProtocol()) {
					numberSubProtocols++;
					numberSubProtocolActivities += subProtocolService
							.getSubProtocolActivitiesCount(activity);
				} else
					numberActivities++;
			}

			MaintenanceProtocolStadisticData data = new MaintenanceProtocolStadisticData();
			data.setEstimatedCost(totalCost);
			data.setCurrency(CurrencyTypeEnum.BS);
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
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#update(java
	 * .util.List)
	 */
	@Override
	public void update(List<MaintenanceProtocols> entities)
			throws GHAEJBException {
		try {
			if (entities == null)
				return;
			if (entities.isEmpty())
				return;

			for (MaintenanceProtocols entity : entities) {
				em.merge(entity);
			}
			em.flush();

		} catch (Exception e) {
			final String msgError = "ERROR: saving Maintenance Protocol ";
			logger.log(Level.INFO, msgError, e);

			final String messageCode = "maintenanceProtocol-save-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote#save(org.
	 * fourgeeks.gha.domain.gmh.MaintenanceProtocols)
	 */
	@Override
	public MaintenanceProtocols save(MaintenanceProtocols entity)
			throws GHAEJBException {
		try {

			em.persist(entity);
			em.flush();

			return em.find(MaintenanceProtocols.class, entity.getId());

		} catch (Exception e) {
			final String msgError = "ERROR: saving Maintenance Protocol ";
			logger.log(Level.INFO, msgError, e);

			final String messageCode = "maintenanceProtocol-save-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}
	}

}
