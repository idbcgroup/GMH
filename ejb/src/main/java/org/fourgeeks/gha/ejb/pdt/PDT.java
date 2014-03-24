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
	@Override
	@SuppressWarnings("unchecked")
	public void onMessage(final Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			HashMap<String, Object> params = (HashMap<String, Object>) objectMessage
					.getObject();

			String code = (String) params.get("code");
			TransactionParams transactParams = transParamsService.find(code);

			Context jndiContext = new InitialContext();
			PDTProcessor processor = (PDTProcessor) jndiContext
					.lookup(transactParams.getJndiProcessorName());

			processor.processMessage(params);

			message.acknowledge();

		} catch (Exception e) {
			logger.log(Level.INFO, "Error al consumir el mensaje en PDT: ", e);
		}
	}
}
