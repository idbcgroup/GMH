package org.fourgeeks.gha.webclient.server.appformviewfunctionbpu;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.ess.AppFormViewFunctionBpuServiceRemote;
import org.fourgeeks.gha.webclient.client.appformviewfunctionbpu.GWTAppFormViewFunctionBpuService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/appFormViewFunctionBpu" })
public class GWTAppFormViewFunctionServiceBpuImpl extends RemoteServiceServlet
		implements GWTAppFormViewFunctionBpuService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/AppFormViewFunctionBpuService")
	AppFormViewFunctionBpuServiceRemote service;

	@Override
	public AppFormViewFunctionBpu save(AppFormViewFunctionBpu bpuFunction)
			throws GHAEJBException {
		return service.save(bpuFunction);
	}

	@Override
	public void delete(AppFormViewFunctionBpu bpuFunction)
			throws GHAEJBException {
		service.delete(bpuFunction);
	}

	@Override
	public List<AppFormViewFunctionBpu> getFunctionsByBpu(Bpu bpu)
			throws GHAEJBException {
		return service.getFunctionsByBpu(bpu);
	}

}
