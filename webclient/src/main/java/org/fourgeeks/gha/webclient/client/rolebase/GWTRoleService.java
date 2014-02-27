/**
 * 
 */
package org.fourgeeks.gha.webclient.client.rolebase;

import java.util.List;

import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("role")
public interface GWTRoleService extends RemoteService {
	/**
	 * @return a List with all the Roles
	 * @throws GHAEJBException
	 */
	public List<Role> getAll() throws GHAEJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Roles starting from offset
	 * @throws GHAEJBException
	 */
	public List<Role> getAll(int offset, int size) throws GHAEJBException;
}
