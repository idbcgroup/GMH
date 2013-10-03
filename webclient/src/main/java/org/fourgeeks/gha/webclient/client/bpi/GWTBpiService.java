/**
 * 
 */
package org.fourgeeks.gha.webclient.client.bpi;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Bpi;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */
@RemoteServiceRelativePath("bpi")
public interface GWTBpiService extends RemoteService{
	/**
	 * @param Id
	 * @throws GHAEJBException
	 */
	public void delete(long Id) throws GHAEJBException;

	/**
	 * @param bpi
	 * @return the list of bpis
	 * @throws GHAEJBException
	 */
	public List<Bpi> find(Bpi bpi) throws GHAEJBException;

	/**
	 * @param Id
	 * @return the bpi
	 * @throws GHAEJBException
	 */
	public Bpi find(long Id) throws GHAEJBException;
	
	/**
	 * @return the list of bpis
	 * @throws GHAEJBException
	 */
	public List<Bpi> getAll() throws GHAEJBException;

	/**
	 * @param bpi
	 * @return the saved bpi
	 * @throws GHAEJBException
	 */
	public Bpi save(Bpi bpi) throws GHAEJBException;

	/**
	 * @param bpi
	 * @return the updated bpi
	 * @throws GHAEJBException
	 */
	public Bpi update(Bpi bpi) throws GHAEJBException;
}
