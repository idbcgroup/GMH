/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("eia")
public interface GWTEiaService extends RemoteService {
	/**
	 * @param eia
	 * Persist an Equipment to database
	 */
	public void save(Eia eia);
	/**
	 * @param Id
	 * @return Find an Eia By Id
	 */
	public Eia find(long Id);
	/**
	 * @param eia
	 * @return a List with Eia using an Eia as a filter
	 */
	public List<Eia> find(Eia eia);
	/**
	 * @param eiaType
	 * @return a List with Eia searching by EiaType
	 */
	public List<Eia> find(EiaType eiaType);
	/**
	 * @return
	 * a List with all the Eia
	 */
	public List<Eia> getAll();
	/**
	 * @param offset
	 * @param size
	 * @return a List with size Eia starting from offset
	 */
	public List<Eia> getAll(int offset, int size);
	/**
	 * @param Id
	 * Delete an entity from database using its id
	 */
	public void delete(long Id);
}
