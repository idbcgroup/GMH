package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("EiaMaintenancePlanification")
public interface GWTEiaMaintenancePlanificationService extends RemoteService {
	/**
	 * @param eiaType
	 * @return A list of maintenance planifications (corrective and preventive)
	 *         for the eias of a eiaType
	 * @throws GHAEJBException
	 */
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException;

	/**
	 * @param entity
	 * @return the corrective maintenance planification associated with
	 *         maintenance planification
	 * @throws GHAEJBException
	 */
	public EiaCorrectiveMaintenancePlanification getCorrectiveMaintenancePlanification(
			EiaMaintenancePlanification entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the preventive maintenance planification associated with
	 *         maintenance planification
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenancePlanification getPreventiveMaintenancePlanification(
			EiaMaintenancePlanification entity) throws GHAEJBException;

	/**
	 * Save a new corrective maintenace planification and its maintenance
	 * planification in the database
	 * 
	 * @param entity
	 *            the corrective maintenace planification to save
	 * @return the saved corrective maintenace planification
	 * @throws GHAEJBException
	 */
	public EiaCorrectiveMaintenancePlanification saveCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity)
			throws GHAEJBException;

	/**
	 * Save a new preventive maintenace planification and its maintenance
	 * planification in the database
	 * 
	 * @param entity
	 *            the preventive maintenace planification to save
	 * @return the saved preventive maintenace planification
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenancePlanification savePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity)
			throws GHAEJBException;

	/**
	 * Update a preventive maintenace planification instance and its related
	 * maintenance planification
	 * 
	 * @param entity
	 * @return the updated preventive maintenace planification instance
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenancePlanification updatePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity)
			throws GHAEJBException;

	/**
	 * Update a corrective maintenace planification instance and its related
	 * maintenance planification
	 * 
	 * @param entity
	 * @return the updated corrective maintenace planification instance
	 * @throws GHAEJBException
	 */
	public EiaCorrectiveMaintenancePlanification updateCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity)
			throws GHAEJBException;
}
