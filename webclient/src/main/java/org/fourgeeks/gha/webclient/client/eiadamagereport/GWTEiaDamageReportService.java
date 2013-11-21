package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("eiaDamageReport")
public interface GWTEiaDamageReportService extends RemoteService {

	public boolean delete(long Id) throws GHAEJBException;

	public List<EiaDamageReport> findByEiaType(EiaType eiaType)
			throws GHAEJBException;

	public List<EiaDamageReport> getAll() throws GHAEJBException;

	public EiaDamageReport save(EiaDamageReport eiaDamageReport)
			throws GHAEJBException;

	public EiaDamageReport update(EiaDamageReport eiaDamageReport)
			throws GHAEJBException;

}
