package org.fourgeeks.gha.ejb.msg;

import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author emiliot
 * 
 */

@RunWith(Arquillian.class)
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
			String codes[] = { "test-code", "test-code2" };
			String msgs[] = { "test msg", "test msg 2" };
			em.persist(new GHAMessage(LanguageEnum.ES, codes[0], msgs[0]));
			em.persist(new GHAMessage(LanguageEnum.ES, codes[1], msgs[1]));
			em.flush();

			GHAMessage msg = service.find(codes[0]);
			assertNotNull(msg);
			assert (msg.getText().equals(msgs[0]));

			ArrayList<String> param = new ArrayList<String>();
			for (String s : codes)
				param.add(s);

			List<GHAMessage> test = service.find(param);
			assertNotNull(test);
			int i = 0;
			for (GHAMessage message : test) {
				assert (message.getText().equals(msgs[i++]));
			}
		} catch (Exception e) {
			ux.rollback();
			e.printStackTrace();
		}

		ux.commit();
	}
}