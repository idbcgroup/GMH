/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Bpi;

/**
 * @author emiliot
 *
 */

@Remote
public interface BpiServiceRemote {
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
