package org.fourgeeks.gha.webclient.client.message;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */
@RemoteServiceRelativePath("messageService")
public interface GWTMessageService extends RemoteService {
	/**
	 * @param Id
	 * @return the message
	 * @throws GHAEJBException
	 */
	public GHAMessage find(String Id) throws GHAEJBException;

	/**
	 * @param messages
	 * @return a list with the gha messages according to the id's
	 * @throws GHAEJBException
	 */
	public List<GHAMessage> find(List<String> messages) throws GHAEJBException;
}
