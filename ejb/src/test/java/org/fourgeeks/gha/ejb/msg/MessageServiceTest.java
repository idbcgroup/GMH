package org.fourgeeks.gha.ejb.msg;

import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.ejb.GhaServiceTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author emiliot, jfuentes
 * 
 */

@RunWith(Arquillian.class)
public class MessageServiceTest extends GhaServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				//Clases
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}


	@EJB(name = "msg.messageService")
	MessageServiceLocal messageServiceLocal;


	@EJB(name = "msg.messageService")
	MessageServiceRemote messageServiceRemote;


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
	public void test() {
		assertNotNull(messageServiceLocal);
		assertNotNull(messageServiceRemote);

		GHAMessageType ghaMessageType = new GHAMessageType();
		ghaMessageType.setTypeName("SAY");

		GHAMessage ghaMessage = new GHAMessage("unit-test-message",LanguageEnum.ES);
		ghaMessage.setText("ghaMessage unit test");
		ghaMessage.setType(ghaMessageType);


		try {
			final String codes[] = { "test-code", "test-code2" };
			final String msgs[] = { "test msg", "test msg 2" };

			final GHAMessage msg = messageServiceRemote.find(codes[0]);
			assertNotNull(msg);
			assert (msg.getText().equals(msgs[0]));

			final ArrayList<String> param = new ArrayList<String>();
			for (final String s : codes)
				param.add(s);

			final List<GHAMessage> test = messageServiceRemote.find(param);
			assertNotNull(test);
			int i = 0;
			for (final GHAMessage message : test) {
				assert (message.getText().equals(msgs[i++]));
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}
}
