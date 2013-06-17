/**
 * 
 */
package org.fourgeeks.gha.webclient.client.manufacturer;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Manufacturer;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("brand")
public interface GWTManufacturerService extends RemoteService {
	/**
	 * @return a List with all the Manufacturers
	 */
	public List<Manufacturer> getAll();

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Manufacturers starting from offset
	 */
	public List<Manufacturer> getAll(int offset, int size);
}
