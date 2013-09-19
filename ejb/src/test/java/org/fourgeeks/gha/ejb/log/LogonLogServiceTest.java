package org.fourgeeks.gha.ejb.log;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.domain.msg.Message;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author alacret
 * 
 */
@RunWith(Arquillian.class)
public class LogonLogServiceTest extends GhaServiceTest {

	@PersistenceContext
	EntityManager em;

	@EJB(name = "log.LogonLogService")
	LogonLogServiceRemote service;

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
	 * @throws EJBException
	 */
	@Test
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, EJBException {
		assertNotNull(em);
		assertNotNull(service);

		ux.begin();
		em.joinTransaction();

		try {
			Message find = em.find(Message.class, "LOGIN-001");
			Bpu find2 = em.find(Bpu.class, 1L);
			service.log(new LogonLog(find2, find, "192.168.1.101"));
			em.flush();
		} catch (Exception e) {
			ux.rollback();
			e.printStackTrace();
		}

		Bpu bpu = em.find(Bpu.class, 1L);
		List<LogonLog> logsByBpu = service.getLogsByBpu(bpu);
		assertNotNull(logsByBpu);
		assertEquals(logsByBpu.size(), 1);

		ux.commit();
	}
}
