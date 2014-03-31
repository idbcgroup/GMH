package org.fourgeeks.gha.ejb.msg;

import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.UILog;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;
import org.fourgeeks.gha.ejb.log.UILogServiceRemote;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author emiliot, jfuentes
 * 
 */

@RunWith(Arquillian.class)
public class MessageServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/MessageService!org.fourgeeks.gha.ejb.msg.MessageServiceLocal")
	MessageServiceLocal messageServiceLocal;

	@EJB(lookup = "java:global/test/MessageService!org.fourgeeks.gha.ejb.msg.MessageServiceRemote")
	MessageServiceRemote messageServiceRemote;

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceRemote")
	UILogServiceRemote uILogServiceRemote;

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceLocal")
	UILogServiceLocal uILogServiceLocal;

	/**
	 */
	@Before
	public void set() {

	}

	/**
	 * 
	 */
	@Test
	public void test() {
		assertNotNull(messageServiceLocal);
		assertNotNull(messageServiceRemote);

		final String code = "GHAMESSAGE-TESTCODE";
		GHAMessage ghaMessage = new GHAMessage(code, LanguageEnum.ES);
		ghaMessage.setMessageText("ghaMessage unit test");

		try {
			ghaMessage = messageServiceLocal.save(ghaMessage);
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(ghaMessage);

		GHAMessage result = null;
		try {
			result = messageServiceRemote.findAndLog(null, code);
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(result);

		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e2) {
			unset();
			Assert.fail(e2.getMessage());
		}

		try {
			final List<UILog> uiLogList = uILogServiceRemote.getAll();
			for (final UILog u : uiLogList) {
				uILogServiceLocal.delete(u);
			}
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}

		try {
			messageServiceLocal.delete(ghaMessage);
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 */
	@After
	public void unset() {

	}
}
