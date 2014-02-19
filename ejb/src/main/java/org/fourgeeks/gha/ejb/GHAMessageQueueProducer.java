package org.fourgeeks.gha.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * Session Bean implementation class GHAMessageQueueProducer
 */
@Stateless
public class GHAMessageQueueProducer implements GHAMessageQueueProducerRemote {

	private final static Logger logger = Logger
			.getLogger(GHAMessageQueueProducer.class.getName());

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:jboss/exported/jms/queue/ghaMessageQueue")
	private Queue queue;

	@Override
	public void sendMessage(String nameValue) {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			MessageProducer producer = session.createProducer(queue);

			MapMessage message = session.createMapMessage();
			message.setString("name", nameValue);

			producer.send(message);

			connection.close();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: SendMessage", e);
		}
	}

}
