package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("eiaDamageReport")
public interface GWTEiaDamageReportService extends RemoteService {

	public boolean delete(long Id) throws GHAEJBException;

	public List<GlaLog> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	public List<GlaLog> getAll() throws GHAEJBException;

	public GlaLog save(GlaLog glaLog)
			throws GHAEJBException;

	public GlaLog update(GlaLog glaLog)
			throws GHAEJBException;

}
