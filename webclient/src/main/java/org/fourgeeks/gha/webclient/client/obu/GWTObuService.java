/**
 * 
 */
package org.fourgeeks.gha.webclient.client.obu;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Obu;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author alacret
 * 
 */
@RemoteServiceRelativePath("obu")
public interface GWTObuService extends RemoteService {
	/**
	 * @return a List with all the Obus
	 * @throws EJBException
	 */
	public List<Obu> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Obus starting from offset
	 * @throws EJBException
	 */
	public List<Obu> getAll(int offset, int size) throws EJBException;
}
