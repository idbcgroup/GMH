/**
 * 
 */
package org.fourgeeks.gha.webclient.client.rolebase;

import java.util.List;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.EJBException;

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
	 * @throws EJBException
	 */
	public List<Role> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size Roles starting from offset
	 * @throws EJBException
	 */
	public List<Role> getAll(int offset, int size) throws EJBException;
}
