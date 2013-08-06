/**
 * 
 */
package org.fourgeeks.gha.webclient.client.rolebase;

import java.util.List;

import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.exceptions.EJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("roleBase")
public interface GWTRoleBaseService extends RemoteService {
	/**
	 * @return a List with all the BaseRoles
	 * @throws EJBException
	 */
	public List<RoleBase> getAll() throws EJBException;

	/**
	 * @param offset
	 * @param size
	 * @return a List with size BaseRoles starting from offset
	 * @throws EJBException
	 */
	public List<RoleBase> getAll(int offset, int size) throws EJBException;
}
