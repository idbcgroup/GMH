package org.fourgeeks.gha.webclient.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface GWTUserService extends RemoteService {
	public boolean test();
}
