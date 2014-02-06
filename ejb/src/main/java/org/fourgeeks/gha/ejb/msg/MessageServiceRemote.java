package org.fourgeeks.gha.ejb.msg;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;

/**
 * @author emiliot
 * 
 */

@Remote
public interface MessageServiceRemote {
	/**
	 * @param id
	 * @return the message
	 * @throws GHAEJBException
	 */
	@Deprecated
	public GHAMessage find(String id) throws GHAEJBException;

	/**
	 * @param user
	 * @param id
	 * @return the message
	 * @throws GHAEJBException
	 */
	public GHAMessage find(String user, String id) throws GHAEJBException;

	/**
	 * @param messages
	 * @return a list with the gha messages according to the id's
	 * @throws GHAEJBException
	 */
	@Deprecated
	public List<GHAMessage> find(List<String> messages) throws GHAEJBException;

	/**
	 * @param user
	 * @param messages
	 * @return a list with the gha messages according to the id's
	 * @throws GHAEJBException
	 */
	public List<GHAMessage> find(String user, List<String> messages)
			throws GHAEJBException;
}
