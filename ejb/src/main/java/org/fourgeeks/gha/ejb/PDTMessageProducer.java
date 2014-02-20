package org.fourgeeks.gha.ejb;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

/**
 * Session Bean implementation class GHAMessageQueueProducer
 */
@Stateless
public class PDTMessageProducer implements PDTMessageProducerRemote {

	private final static Logger logger = Logger
			.getLogger(PDTMessageProducer.class.getName());

	@Resource(lookup = "java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "java:jboss/exported/jms/queue/ghaMessageQueue")
	private Queue queue;

	@Override
	public void sendMessage(String transactionCode,
			HashMap<String, Object> params) {
		try {
			Connection connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(queue);

			params.put("code", transactionCode);
			ObjectMessage message = session.createObjectMessage();
			message.setObject(params);

			producer.send(message);

			connection.close();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: SendMessage", e);
		}
	}

}
