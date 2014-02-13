package org.fourgeeks.gha.webclient.server.obu;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.ejb.gar.JobServiceRemote;
import org.fourgeeks.gha.webclient.client.obu.GWTJobService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author naramirez
 * 
 */
@WebServlet(urlPatterns = { "/webclient/job" })
public class GWTJobServiceImpl extends RemoteServiceServlet implements
		GWTJobService {
	private static final long serialVersionUID = 1L;

	@EJB(lookup = "java:global/ear-1/ejb-1/JobService")
	JobServiceRemote service;

	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	@Override
	public List<Job> find(Job entity) throws GHAEJBException {
		return service.find(entity);
	}

	@Override
	public Job find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	@Override
	public List<Job> getAll() throws GHAEJBException {
		return service.getAll();
	}

	@Override
	public Job save(Job entity) throws GHAEJBException {
		return service.save(entity);
	}

	@Override
	public Job update(Job entity) throws GHAEJBException {
		return service.update(entity);
	}

}