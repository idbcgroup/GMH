package org.fourgeeks.gha.ejb.log;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.UILog;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.ejb.helpers.BpuHelper;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.ejb.mix.CitizenServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;
import org.fourgeeks.gha.ejb.msg.MessageServiceLocal;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author alacret
 * 
 */
@RunWith(Arquillian.class)
public class UILogServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceRemote")
	UILogServiceRemote uILogServiceRemote;

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceLocal")
	UILogServiceLocal uILogServiceLocal;

	@EJB(lookup = "java:global/test/MessageService!org.fourgeeks.gha.ejb.msg.MessageServiceLocal")
	MessageServiceLocal messageServiceLocal;

	@EJB(lookup = "java:global/test/LegalEntityService")
	LegalEntityServiceRemote legalEntityServiceRemote;

	@EJB(lookup = "java:global/test/CitizenService")
	CitizenServiceRemote citizenServiceRemote;

	@EJB(lookup = "java:global/test/InstitutionService")
	InstitutionServiceRemote institutionServiceRemote;

	@EJB(lookup = "java:global/test/BpuService")
	BpuServiceRemote bpuServiceRemote;

	@EJB(lookup = "java:global/test/BpiService")
	BpiServiceRemote bpiServiceRemote;

	private GHAMessage ghaMessage;
	private Bpu bpu;
	private BpuHelper bpuHelper;

	/**
	 * 
	 */
	@Before
	public void set() {
		bpuHelper = new BpuHelper(legalEntityServiceRemote,
				citizenServiceRemote, institutionServiceRemote,
				bpuServiceRemote, bpiServiceRemote);
		bpu = bpuHelper.createBpu();

		try {
			ghaMessage = messageServiceLocal.save(new GHAMessage(
					LanguageEnum.ES, "msg-test" + Math.random(),
					"Mensaje de prueba"));
		} catch (final GHAEJBException e2) {
			Assert.fail("failing creating the ghamessage");
		}
	}

	/**
	 * 
	 */
	@Test
	public void test() {
		Assert.assertNotNull(uILogServiceRemote);
		Assert.assertNotNull(uILogServiceLocal);
		Assert.assertNotNull(ghaMessage);

		List<UILog> all = null;
		try {
			all = uILogServiceRemote.getAll();
		} catch (final Exception e) {
			unset();
			Assert.fail("failing retriving all ui logs" + e.getMessage());
		}

		try {
			for (final UILog uiLog2 : all)
				uILogServiceLocal.delete(uiLog2);
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}

		try {
			final UILog uiLog = new UILog();
			uiLog.setBpu(bpu);
			uiLog.setMessage(ghaMessage);
			uILogServiceRemote.log(uiLog);
		} catch (final GHAEJBException e1) {
			unset();
			Assert.fail("failing creating a uilog");
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			all = uILogServiceRemote.getAll();
		} catch (final Exception e) {
			unset();
			Assert.fail("failing retriving all ui logs" + e.getMessage());
		}
		Assert.assertEquals(1, all.size());

		try {
			for (final UILog uiLog2 : all)
				uILogServiceLocal.delete(uiLog2);
		} catch (final Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * 
	 */
	@After
	public void unset() {
		try {
			messageServiceLocal.delete(ghaMessage);
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		bpuHelper.removeBpu();
	}
}
