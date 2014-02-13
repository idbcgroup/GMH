package org.fourgeeks.gha.ejb.gmh;

import java.sql.Date;
import java.sql.Timestamp;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

/**
 * @author naramirez
 */
@Local
public interface EiaMaintenancePlanificationServiceLocal {

	/**
	 * Return the number of effectued preventive maintenance planifications for
	 * the given {@link MaintenancePlan}
	 * 
	 * @param maintenancePlan
	 *            the given {@link MaintenancePlan}
	 * @return the number of effectued preventive maintenance planifications
	 * @throws GHAEJBException
	 */
	public Long getEffectuatedPlanificationsCount(
			MaintenancePlan maintenancePlan) throws GHAEJBException;

	/**
	 * Return the number of {@link MaintenancePlan} entities that are associated
	 * to this {@link MaintenancePlan}
	 * 
	 * @param maintenancePlan
	 *            the given {@link MaintenancePlan}
	 * @return the number of {@link MaintenancePlan}
	 * @throws GHAEJBException
	 */
	public Long getPlanificationsCount(MaintenancePlan maintenancePlan)
			throws GHAEJBException;

	/**
	 * Return the most recent {@link Date} of a effectuated preventive
	 * planification associated to the given {@link MaintenancePlan}
	 * 
	 * @param maintenancePlan
	 * @return the most recent {@link Date} or <code>null</code> if no
	 *         planification has been effectuated
	 * @throws GHAEJBException
	 */
	public Timestamp getLastEffectuatedPlanificationDate(
			MaintenancePlan maintenancePlan) throws GHAEJBException;
}
