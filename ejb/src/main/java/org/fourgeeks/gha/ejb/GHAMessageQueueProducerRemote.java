package org.fourgeeks.gha.ejb;

import javax.ejb.Remote;

/**
 * @author nelson
 */
@Remote
public interface GHAMessageQueueProducerRemote {

	/**
	 * @param nameValue
	 */
	public void sendMessage(String nameValue);
}
