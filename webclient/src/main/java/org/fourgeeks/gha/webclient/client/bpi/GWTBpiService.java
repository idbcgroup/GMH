/**
 * 
 */
package org.fourgeeks.gha.webclient.client.bpi;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
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
