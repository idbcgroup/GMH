package org.fourgeeks.gha.ejb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: GHAMessageConsumer
 */
@MessageDriven(mappedName = "java:jboss/exported/jms/queue/ghaMessageQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/ghaMessageQueue") })
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
			MapMessage data = (MapMessage) message;
			logger.info("GHAMessageQueueConsumer - mensaje: name = "
					+ data.getStringProperty("name"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
