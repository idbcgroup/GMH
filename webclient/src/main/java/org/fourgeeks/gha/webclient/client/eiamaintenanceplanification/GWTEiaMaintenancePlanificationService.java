package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationService;

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
			EiaMaintenancePlanificationService entity) throws GHAEJBException;

	/**
	 * @param entity
	 * @return the preventive maintenance planification associated with
	 *         maintenance planification
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenancePlanification getPreventiveMaintenancePlanification(
			EiaMaintenancePlanificationService entity) throws GHAEJBException;

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
}
