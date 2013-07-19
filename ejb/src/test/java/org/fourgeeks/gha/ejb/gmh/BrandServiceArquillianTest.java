package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Viviana Torres
 * 
 */
// @RunWith(Arquillian.class)
public class BrandServiceArquillianTest {
	/*
	 * @Deployment public static WebArchive createDeployment() { WebArchive jar
	 * = DefaultDeployment.deployment(); System.out.println(jar.toString(true));
	 * return jar;
	 * 
	 * }
	 * 
	 * @Inject BrandServiceRemote brandServiceRemote;
	 * 
	 * @Test public void testCanPersistBrandObject() {
	 * System.out.println("TESTING");
	 * 
	 * List<Brand> brands = null; try { brands = brandServiceRemote.getAll(); }
	 * catch (EJBException e) { System.out.println("ERROR " +
	 * e.getLocalizedMessage()); } Assert.assertNotNull(brands);
	 * Assert.assertTrue(brands.size() > 0);
	 * 
	 * }
	 */
}