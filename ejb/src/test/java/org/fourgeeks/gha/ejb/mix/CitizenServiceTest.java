package org.fourgeeks.gha.ejb.mix;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceLocal;
import org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceRemote;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class CitizenServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/SubProtocolAndCheklistService!"
			+ "org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceRemote")
	private SubProtocolAndCheklistServiceRemote serviceRemote;

	@EJB(lookup = "java:global/test/SubProtocolAndCheklistService!"
			+ "org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceLocal")
	private SubProtocolAndCheklistServiceLocal serviceLocal;

	private Activity activity;
	private Activity parentActivity;
	private SubProtocolAndChecklist subProtocol;

	private void deleteListTest() {
		final int itemsExpected = 3;

		try {
			subProtocol = new SubProtocolAndChecklist();
			subProtocol.setActivity(activity);
			subProtocol.setParentActivity(parentActivity);
			subProtocol.setOrdinal(4);
			subProtocol = serviceRemote.save(subProtocol);

			final List<SubProtocolAndChecklist> list = new ArrayList<SubProtocolAndChecklist>();
			list.add(subProtocol);

			serviceRemote.delete(list);
			Assert.assertEquals(itemsExpected, serviceRemote.getAll().size());
		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	/** */
	@Before
	public void set() {
		activity = new Activity();
		activity.setId(1);

		parentActivity = new Activity();
		parentActivity.setId(7);

		subProtocol = new SubProtocolAndChecklist();
		subProtocol.setActivity(activity);
		subProtocol.setParentActivity(parentActivity);
		subProtocol.setOrdinal(4);
	}

	/** */
	@Test
	public void test() {
		final String sep = "\n---------------------------------------\n";

		System.out.println("TESTING CITIZEN SERVICE\n");

		System.out.println(sep + "deleteListTest" + sep);
		deleteListTest();
	}

	/** */
	@After
	public void unset() {

	}
}
