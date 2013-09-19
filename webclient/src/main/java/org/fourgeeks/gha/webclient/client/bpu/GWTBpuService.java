/**
 * 
 */
package org.fourgeeks.gha.webclient.client.bpu;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param bpu
	 * @return the list of bpus
	 * @throws GHAEJBException
	 */
	public List<Bpu> find(Bpu bpu) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the bpu
	 * @throws GHAEJBException
	 */
	public Bpu find(long Id) throws GHAEJBException;
	
	/**
	 * @return the list of bpus
	 * @throws GHAEJBException
	 */
	public List<Bpu> getAll() throws GHAEJBException;

	/**
	 * @param bpu
	 * @return the saved bpu
	 * @throws GHAEJBException
	 */
	public Bpu save(Bpu bpu) throws GHAEJBException;

	/**
	 * @param bpu
	 * @return the updated bpu
	 * @throws GHAEJBException
	 */
	public Bpu update(Bpu bpu) throws GHAEJBException;
}
