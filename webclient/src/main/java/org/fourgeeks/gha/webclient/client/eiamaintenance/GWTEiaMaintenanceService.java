package org.fourgeeks.gha.webclient.client.eiamaintenance;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author naramirez
 */
@RemoteServiceRelativePath("EiaMaintenance")
public interface GWTEiaMaintenanceService extends RemoteService {
	/**
	 * @param eiaType
	 * @return A list of maintenance planifications (corrective and preventive)
	 *         for the eias of a eiaType
	 * @throws GHAEJBException
	 */
	public List<EiaMaintenance> find(EiaType eiaType) throws GHAEJBException;

	/**
	 * Save a new corrective maintenace planification and its maintenance
	 * planification in the database
	 * 
	 * @param entity
	 *            the corrective maintenace planification to save
	 * @return the saved corrective maintenace planification
	 * @throws GHAEJBException
	 */
	public EiaCorrectiveMaintenance saveCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException;

	/**
	 * Save a new preventive maintenace planification and its maintenance
	 * planification in the database
	 * 
	 * @param entity
	 *            the preventive maintenace planification to save
	 * @return the saved preventive maintenace planification
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenance savePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException;

	/**
	 * Update a preventive maintenace planification instance and its related
	 * maintenance planification
	 * 
	 * @param entity
	 * @return the updated preventive maintenace planification instance
	 * @throws GHAEJBException
	 */
	public EiaPreventiveMaintenance updatePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException;

	/**
	 * Update a corrective maintenace planification instance and its related
	 * maintenance planification
	 * 
	 * @param entity
	 * @return the updated corrective maintenace planification instance
	 * @throws GHAEJBException
	 */
	public EiaCorrectiveMaintenance updateCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException;
}
