/**
 * 
 */
package org.fourgeeks.gha.webclient.client.bpu;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("bpu")
public interface GWTBpuService extends RemoteService{
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param bpu
	 * @return the list of bpus
	 * @throws EJBException
	 */
	public List<Bpu> find(Bpu bpu) throws EJBException;

	/**
	 * @param Id
	 * @return the bpu
	 * @throws EJBException
	 */
	public Bpu find(long Id) throws EJBException;
	
	/**
	 * @return the list of bpus
	 * @throws EJBException
	 */
	public List<Bpu> getAll() throws EJBException;

	/**
	 * @param bpu
	 * @return the saved bpu
	 * @throws EJBException
	 */
	public Bpu save(Bpu bpu) throws EJBException;

	/**
	 * @param bpu
	 * @return the updated bpu
	 * @throws EJBException
	 */
	public Bpu update(Bpu bpu) throws EJBException;
}
