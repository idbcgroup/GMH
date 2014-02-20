package org.fourgeeks.gha.ejb;

import java.util.HashMap;

import javax.ejb.Remote;

/**
 * @author nelson
 */
@Remote
public interface PDTMessageProducerRemote {

	/**
	 * Send a message to the PDT Message Queue to be processed by the PDT
	 * Message Consumer
	 * 
	 * @param transactionCode
	 *            the transaction code ID
	 * @param params
	 *            the params of the mesage
	 */
	public void sendMessage(String transactionCode,
			HashMap<String, Object> params);
}
