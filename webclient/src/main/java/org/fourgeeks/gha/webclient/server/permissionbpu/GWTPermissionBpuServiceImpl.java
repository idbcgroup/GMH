package org.fourgeeks.gha.webclient.server.permissionbpu;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.ui.PermissionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.ess.ui.PermissionBpuServiceRemote;
import org.fourgeeks.gha.webclient.client.permissionbpu.GWTPermissionBpuService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/permissionbpu" })
public class GWTPermissionBpuServiceImpl extends RemoteServiceServlet
		implements GWTPermissionBpuService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/AppFormViewFunctionBpuService")
	PermissionBpuServiceRemote service;

	@Override
	public void delete(PermissionBpu bpuFunction) throws GHAEJBException {
		service.delete(bpuFunction);
	}

	@Override
	public List<PermissionBpu> getPermissionsByBpu(Bpu bpu)
			throws GHAEJBException {
		return service.getPermissionByBpu(bpu);
	}

	@Override
	public PermissionBpu save(PermissionBpu bpuFunction) throws GHAEJBException {
		return service.save(bpuFunction);
	}

}
