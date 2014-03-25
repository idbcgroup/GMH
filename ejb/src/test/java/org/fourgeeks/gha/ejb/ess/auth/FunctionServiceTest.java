package org.fourgeeks.gha.ejb.ess.auth;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author alacret
 * 
 */
@RunWith(Arquillian.class)
public class FunctionServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/FunctionService")
	FunctionServiceRemote functionService;

	/**
	 * 
	 */
	@Test
	public void test() {
		final String code = "TESTCODE" + Math.random() / 10;
		Assert.assertNotNull(functionService);

		final Function originalPermission = new Function();
		originalPermission.setCode(code);

		Function newPermission = null;
		try {
			newPermission = functionService.save(originalPermission);
		} catch (final GHAEJBException e) {
			Assert.fail("error saving the function");
		}

		Assert.assertEquals(originalPermission.getCode(),
				newPermission.getCode());

		try {
			functionService.delete(newPermission);
		} catch (final GHAEJBException e) {
			Assert.fail("error deleting the function");
		}
	}

}