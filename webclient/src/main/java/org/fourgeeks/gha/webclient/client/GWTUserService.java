package org.fourgeeks.gha.webclient.client;

import org.fourgeeks.gha.domain.mix.LegalEntity;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface GWTUserService extends RemoteService {
	public boolean test();
	
	public boolean isLogged();

	public LegalEntity test2();
}
