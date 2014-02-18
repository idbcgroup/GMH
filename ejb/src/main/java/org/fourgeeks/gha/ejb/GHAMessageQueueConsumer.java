package org.fourgeeks.gha.ejb;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: GHAMessageConsumer
 */
@MessageDriven(mappedName = "java:jboss/exported/jms/queue/ghaMessageQueue")
public class GHAMessageQueueConsumer implements MessageListener {

	private final static Logger logger = Logger
			.getLogger(GHAMessageQueueConsumer.class.getName());

	/**
	 * Default constructor.
	 */
	public GHAMessageQueueConsumer() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@SuppressWarnings("unchecked")
	public void onMessage(Message message) {
		try {
			Map<String, Object> data = message.getBody(Map.class);
			Object object = data.get("var1");
			logger.info("var1 = " + object.toString());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
