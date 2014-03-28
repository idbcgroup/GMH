package org.fourgeeks.gha.ejb.ess.ui;

import javax.ejb.EJB;

import junit.framework.Assert;

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
public class ViewFunctionServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/ViewFunctionService")
	ViewFunctionServiceRemote service;

	/**
	 * 
	 */
	@Test
	public void test() {
		System.out.println("\n TESTING VIEW FUNCTION SERVICE\n");

		Assert.assertNotNull(service);

		try {
			Assert.assertNotNull(service.getAll());
		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}

	}

}