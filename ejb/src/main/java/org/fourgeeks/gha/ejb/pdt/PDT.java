package org.fourgeeks.gha.ejb.pdt;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.fourgeeks.gha.domain.TransactionParams;

/**
 * Message-Driven Bean implementation class for: PDT - a Queue Message Consumer
 */
@MessageDriven(mappedName = "java:jboss/exported/jms/queue/ghaMessageQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/ghaMessageQueue") })
public class PDT implements MessageListener {

	private final static Logger logger = Logger.getLogger(PDT.class.getName());

	@EJB(lookup = "java:global/ear-1/ejb-1/TransactionParamsService!"
			+ "org.fourgeeks.gha.ejb.pdt.TransactionParamsServiceLocal")
	private TransactionParamsServiceLocal transParamsService;

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@SuppressWarnings("unchecked")
	public void onMessage(Message message) {
		try {
			logger.log(Level.INFO, "ZZZ 1");
			ObjectMessage objectMessage = (ObjectMessage) message;
			logger.log(Level.INFO, "ZZZ 2");
			HashMap<String, Object> data = (HashMap<String, Object>) objectMessage
					.getObject();
			logger.log(Level.INFO, "ZZZ 3");

			String code = (String) data.get("code");
			logger.log(Level.INFO, "ZZZ 4");
			TransactionParams transactParams = transParamsService.find(code);
			logger.log(Level.INFO, "ZZZ 5");

			Context jndiContext = new InitialContext();
			logger.log(Level.INFO, "ZZZ 6");
			PDTProcessor processor = (PDTProcessor) jndiContext
					.lookup(transactParams.getJndiProcessorName());
			logger.log(Level.INFO, "ZZZ 7");

			processor.processMessage(data);
			logger.log(Level.INFO, "ZZZ 8");

		} catch (Exception e) {
			logger.log(Level.INFO, "Error al consumir el mensaje en PDT: ", e);
		}
	}
}
