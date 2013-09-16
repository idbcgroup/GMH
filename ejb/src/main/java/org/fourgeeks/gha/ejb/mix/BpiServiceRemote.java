/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.Bpi;

/**
 * @author emiliot
 *
 */

@Remote
public interface BpiServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * @param bpi
	 * @return the list of bpis
	 * @throws EJBException
	 */
	public List<Bpi> find(Bpi bpi) throws EJBException;

	/**
	 * @param Id
	 * @return the bpi
	 * @throws EJBException
	 */
	public Bpi find(long Id) throws EJBException;
	
	/**
	 * @return the list of bpis
	 * @throws EJBException
	 */
	public List<Bpi> getAll() throws EJBException;

	/**
	 * @param bpi
	 * @return the saved bpi
	 * @throws EJBException
	 */
	public Bpi save(Bpi bpi) throws EJBException;

	/**
	 * @param bpi
	 * @return the updated bpi
	 * @throws EJBException
	 */
	public Bpi update(Bpi bpi) throws EJBException;
}
