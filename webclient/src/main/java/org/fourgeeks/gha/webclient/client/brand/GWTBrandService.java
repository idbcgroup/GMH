/**
 * 
 */
package org.fourgeeks.gha.webclient.client.brand;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("brand")
public interface GWTBrandService extends RemoteService {
	/**
	 * @return a List with all the Brands
	 */
	public List<Brand> getAll();

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Brands starting from offset
	 */
	public List<Brand> getAll(int offset, int size);
}
