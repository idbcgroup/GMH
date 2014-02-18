package org.fourgeeks.gha.ejb;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 * Session Bean implementation class GHAMessageQueueProducer
 */
@Stateless
public class GHAMessageQueueProducer implements GHAMessageQueueProducerRemote {

	private final static Logger logger = Logger
			.getLogger(GHAMessageQueueProducer.class.getName());

	@Resource(lookup = "jms/javaee7/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(lookup = "jms/javaee7/Queue")
	private Queue queue;

	@Override
	public void sendMessage(Map<String, Object> message) {
		try {
			JMSContext context = connectionFactory.createContext();
			JMSProducer producer = context.createProducer();
			producer.send(queue, message);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: SendMessage", e);
		}
	}

}
