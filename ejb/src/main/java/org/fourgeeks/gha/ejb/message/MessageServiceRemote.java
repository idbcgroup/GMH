package org.fourgeeks.gha.ejb.message;

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
	 * @param Id
	 * @return the message
	 * @throws GHAEJBException
	 */
	public GHAMessage find(String Id) throws GHAEJBException;
}
