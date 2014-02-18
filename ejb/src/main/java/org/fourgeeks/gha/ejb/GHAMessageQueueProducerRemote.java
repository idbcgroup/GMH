package org.fourgeeks.gha.ejb;

import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface GHAMessageQueueProducerRemote {

	public void sendMessage(Map<String, Object> message);
}
