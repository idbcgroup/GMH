package org.fourgeeks.gha.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Queue;

/**
 * Session Bean implementation class GHAMessageQueueProducer
 */
@Stateless
public class GHAMessageQueueProducer implements GHAMessageQueueProducerRemote {

	private final static Logger logger = Logger
			.getLogger(GHAMessageQueueProducer.class.getName());

	@Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:jboss/exported/jms/queue/ghaMessageQueue")
	private Queue queue;

	@Override
	public void sendMessage(String nameValue) {
		try {
			JMSContext context = connectionFactory.createContext();
			JMSProducer producer = context.createProducer();

			MapMessage message = context.createMapMessage();
			message.setString("name", nameValue);
			producer.send(queue, message);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: SendMessage", e);
		}
	}

}
