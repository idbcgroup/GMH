/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
	public boolean delete(long Id)throws EJBException;
	/**
	 * @param eia
	 * @return a List with Eia using an Eia as a filter
	 */
	public List<Eia> find(Eia eia)throws EJBException;
	/**
	 * @param eiaType
	 * @return a List with Eia searching by EiaType
	 */
	public List<Eia> findByEiaType(EiaType eiaType)throws EJBException;
	/**
	 * @param Id
	 * @return Find an Eia By Id
	 */
	public Eia find(long Id)throws EJBException;
	/**
	 * @return
	 * a List with all the Eia
	 */
	public List<Eia> getAll()throws EJBException;
	/**
	 * @param offset
	 * @param size
	 * @return a List with size Eia starting from offset
	 */
	public List<Eia> getAll(int offset, int size)throws EJBException;
	/**
	 * @param eia
	 * Persist an Eia to database
	 */
	public Eia save(Eia eia) throws EJBException;
	/**
	 * @param Eia eia
	 * @return a boolean with the result of the operation
	 * 
	 */
	public Eia update(Eia eia)throws EJBException;
}
