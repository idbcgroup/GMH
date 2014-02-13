/**
 * 
 */
package org.fourgeeks.gha.webclient.server.maintenanceplanification;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.webclient.client.eiamaintenance.GWTEiaMaintenanceService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot, naramirez
 * 
 */
@WebServlet(urlPatterns = { "/webclient/EiaMaintenance" })
public class GWTEiaMaintenanceServiceImpl extends RemoteServiceServlet
		implements GWTEiaMaintenanceService {

	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/EiaMaintenanceService")
	EiaMaintenanceServiceRemote serviceRemote;

	@Override
	public List<EiaMaintenance> find(EiaType eiaType) throws GHAEJBException {

		List<EiaMaintenance> resultList = serviceRemote.find(eiaType);
		return resultList;
	}

	@Override
	public EiaCorrectiveMaintenance saveCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException {

		EiaCorrectiveMaintenance savedEntity = serviceRemote
				.saveCorrectiveMaintenance(entity);
		return savedEntity;
	}

	@Override
	public EiaPreventiveMaintenance savePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException {

		EiaPreventiveMaintenance savedEntity = serviceRemote
				.savePreventiveMaintenance(entity);
		return savedEntity;
	}

	@Override
	public EiaCorrectiveMaintenance updateCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException {

		EiaCorrectiveMaintenance updatedEntity = serviceRemote
				.updateCorrectiveMaintenance(entity);
		return updatedEntity;
	}

	@Override
	public EiaPreventiveMaintenance updatePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException {

		EiaPreventiveMaintenance updatedEntity = serviceRemote
				.updatePreventiveMaintenance(entity);
		return updatedEntity;
	}
}
