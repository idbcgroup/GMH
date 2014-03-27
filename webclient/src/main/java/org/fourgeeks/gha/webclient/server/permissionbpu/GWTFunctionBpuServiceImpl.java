package org.fourgeeks.gha.webclient.server.permissionbpu;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.ess.auth.FunctionBpuServiceRemote;
import org.fourgeeks.gha.webclient.client.permissionbpu.GWTFunctionBpuService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/functionbpu" })
public class GWTFunctionBpuServiceImpl extends RemoteServiceServlet implements
		GWTFunctionBpuService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/FunctionBpuService")
	FunctionBpuServiceRemote service;

	@Override
	public void delete(final FunctionBpu bpuFunction) throws GHAEJBException {
		service.delete(bpuFunction);
	}

	@Override
	public List<Function> getPermissionsByBpu(final Bpu bpu)
			throws GHAEJBException {
		return service.getFunctionByBpu(bpu);
	}

	@Override
	public FunctionBpu save(final FunctionBpu bpuFunction)
			throws GHAEJBException {
		return service.save(bpuFunction);
	}

}