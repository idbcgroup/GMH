/**
 * 
 */
package org.fourgeeks.gha.webclient.client.baserole;

import java.util.List;

import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.domain.exceptions.EJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("baseRole")
public interface GWTBaseRoleService extends RemoteService {
	/**
	 * @return a List with all the BaseRoles
	 * @throws EJBException
	 */
	public List<BaseRole> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size BaseRoles starting from offset
	 * @throws EJBException
	 */
	public List<BaseRole> getAll(int offset, int size) throws EJBException;
}
