package org.fourgeeks.gha.webclient.server.obu;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.gar.ObuServiceRemote;
import org.fourgeeks.gha.webclient.client.obu.GWTObuService;

/**
 * @author alacret
 * 
 */
public class GwtObuServiceImpl implements GWTObuService {

	@EJB(name = "gmh.ObuService")
	ObuServiceRemote service;

	@Override
	public List<Obu> getAll() throws EJBException {
		return service.getAll();
	}

	@Override
	public List<Obu> getAll(int offset, int size) throws EJBException {
		// TODO
		return service.getAll();
	}
}