/**
 * 
 */
package org.fourgeeks.gha.webclient.client.bpa;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Bpa;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("bpa")
public interface GWTBpaService extends RemoteService{
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param bpa
	 * @return the list of bpas
	 * @throws EJBException
	 */
	public List<Bpa> find(Bpa bpa) throws EJBException;

	/**
	 * @param Id
	 * @return the bpa
	 * @throws EJBException
	 */
	public Bpa find(long Id) throws EJBException;
	
	/**
	 * @return the list of bpas
	 * @throws EJBException
	 */
	public List<Bpa> getAll() throws EJBException;

	/**
	 * @param bpa
	 * @return the saved bpa
	 * @throws EJBException
	 */
	public Bpa save(Bpa bpa) throws EJBException;

	/**
	 * @param bpa
	 * @return the updated bpa
	 * @throws EJBException
	 */
	public Bpa update(Bpa bpa) throws EJBException;

}
