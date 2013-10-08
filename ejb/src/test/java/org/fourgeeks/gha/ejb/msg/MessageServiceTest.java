package org.fourgeeks.gha.ejb.msg;

import static junit.framework.Assert.assertNotNull;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.fourgeeks.gha.ejb.message.MessageServiceRemote;
import org.junit.Test;

/**
 * @author emiliot
 * 
 */
public class MessageServiceTest extends GhaServiceTest {
	@PersistenceContext
	EntityManager em;

	@EJB(name = "msg.messageService")
	MessageServiceRemote service;

	@Inject
	UserTransaction ux;

	/**
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 * @throws GHAEJBException
	 */
	@Test
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException,
			GHAEJBException {
		assertNotNull(em);
		assertNotNull(service);

		ux.begin();
		em.joinTransaction();

		try {
			em.persist(new GHAMessage(LanguageEnum.ES, "test-code", "test msg"));
			GHAMessage msg = service.find("test-code");
			assertNotNull(msg);
			assert (msg.getText().equals("test msg"));
			em.flush();
		} catch (Exception e) {
			ux.rollback();
			e.printStackTrace();
		}

		ux.commit();
	}
}
