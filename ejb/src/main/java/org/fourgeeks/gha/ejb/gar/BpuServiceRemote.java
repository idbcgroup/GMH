/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author emiliot
 * 
 */
@Remote
public interface BpuServiceRemote {
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
