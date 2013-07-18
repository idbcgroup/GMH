/**
 * 
 */
package org.fourgeeks.gha.webclient.client.buildinglocation;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("buildingLocation")
public interface GWTBuildingLocationService extends RemoteService {
	/**
	 * @return a List with all the Building Locations
	 * @throws EJBException
	 */
	public List<BuildingLocation> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size BuildingLocation starting from offset
	 * @throws EJBException
	 */
	public List<BuildingLocation> getAll(int offset, int size)
			throws EJBException;
}
