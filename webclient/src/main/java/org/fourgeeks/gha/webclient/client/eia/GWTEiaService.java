/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	 * @param Id
	 * @return a boolean with the result of the operation
	 */
	public boolean delete(long Id)throws GHAEJBException;
	/**
	 * @param eia
	 * @return a List with Eia using an Eia as a filter
	 */
	public List<Eia> find(Eia eia)throws GHAEJBException;
	/**
	 * @param eiaType
	 * @return a List with Eia searching by EiaType
	 */
	public List<Eia> findByEiaType(EiaType eiaType)throws GHAEJBException;
	/**
	 * @param Id
	 * @return Find an Eia By Id
	 */
	public Eia find(long Id)throws GHAEJBException;
	/**
	 * @return
	 * a List with all the Eia
	 */
	public List<Eia> getAll()throws GHAEJBException;
	/**
	 * @param offset
	 * @param size
	 * @return a List with size Eia starting from offset
	 */
	public List<Eia> getAll(int offset, int size)throws GHAEJBException;
	/**
	 * @param eia
	 * Persist an Eia to database
	 */
	public Eia save(Eia eia) throws GHAEJBException;
	/**
	 * @param Eia eia
	 * @return a boolean with the result of the operation
	 * 
	 */
	public Eia update(Eia eia)throws GHAEJBException;
}
