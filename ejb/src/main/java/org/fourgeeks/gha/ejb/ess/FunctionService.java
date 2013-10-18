package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret
 * 
 */
@Stateless(name = "ess.FunctionService")
public class FunctionService implements FunctionServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private GHAEJBException exception = new GHAEJBException();

	private final static Logger logger = Logger.getLogger(FunctionService.class
			.getName());

	@Override
	public List<Function> getAll() throws GHAEJBException {
		try {
			TypedQuery<Function> query = em.createNamedQuery("Function.getAl",
					Function.class);
			return query.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error obteniendo todos los function", e);
			try {
				exception.setGhaMessage(em.find(GHAMessage.class,
						new GHAMessageId("function-getAll-fail",
								RuntimeParameters.getLang())));
			} catch (Exception e1) {
				exception.setGhaMessage(new GHAMessage(RuntimeParameters
						.getLang(), "generic-error-msg",
						"Error de sistema, intente más tarde."));
			}
			throw exception;
		}
	}

}
