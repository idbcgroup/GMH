/**
 * 
 */
package org.fourgeeks.gha.webclient.client.manufacturer;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("manufacturer")
public interface GWTManufacturerService extends RemoteService {
	/**
	 * @return a List with all the Manufacturers
	 * @throws EJBException 
	 */
	public List<Manufacturer> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Manufacturers starting from offset
	 * @throws EJBException 
	 */
	public List<Manufacturer> getAll(int offset, int size) throws EJBException;
}
