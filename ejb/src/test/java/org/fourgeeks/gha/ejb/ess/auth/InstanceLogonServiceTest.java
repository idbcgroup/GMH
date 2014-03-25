package org.fourgeeks.gha.ejb.ess.auth;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.ess.auth.InstanceLogon;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.mix.BpaServiceRemote;
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
public class InstanceLogonServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/InstanceLogonService")
	InstanceLogonServiceRemote service;

	@EJB(lookup = "java:global/test/BpaService")
	BpaServiceRemote bpaService;

	private Bpa bpa;

	/**
	 * 
	 */
	@Before
	public void set() {
		// final Bpa localBpa = new Bpa();
		// try {
		// bpa = bpaService.save(localBpa);
		// } catch (final GHAEJBException e) {
		// Assert.fail("error creating the bpa");
		// }

	}

	/**
	 * @throws GHAEJBException
	 */
	@Test
	public void test() throws GHAEJBException {
		Assert.assertNotNull(service);

		InstanceLogon entity = new InstanceLogon();
		entity = service.save(entity);

		Assert.assertNotNull(entity);
		final List<InstanceLogon> all = service.getAll();

		Assert.assertTrue(all != null && all.size() >= 1);

		// entity.setBpa(bpa);
		// entity = service.update(entity);
		// final long id = entity.getId();
		// service.delete(id);

	}

	/**
	 * 
	 */
	@After
	public void unset() {
		// try {
		// bpaService.delete(bpa.getId());
		// } catch (final GHAEJBException e) {
		// Assert.fail("error deleting the bpa");
		// }
	}
}
