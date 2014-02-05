package org.fourgeeks.gha.ejb.msg;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;

/**
 * @author alacret
 * 
 */

@Local
public interface MessageServiceLocal {

	/**
	 * @param ghaMessage
	 *            the ghamessague to save
	 * @return a ghamessage saved
	 * @throws GHAEJBException
	 */
	public GHAMessage save(GHAMessage ghaMessage) throws GHAEJBException;

	/**
	 * @param ghaMessage
	 *            the ghamessague to delete
	 * @throws GHAEJBException
	 */
	public void delete(GHAMessage ghaMessage) throws GHAEJBException;
}
