package org.fourgeeks.gha.ejb.ess.auth;

import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.ejb.helpers.BpuHelper;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.ejb.mix.CitizenServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;
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
public class FunctionBpuServiceTest extends GHAArquillianBaseServiceTest {
	private static final String TESTCODE = "TESTCODE" + Math.random() / 10;

	@EJB(lookup = "java:global/test/FunctionBpuService")
	FunctionBpuServiceRemote service;

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

	@EJB(lookup = "java:global/test/FunctionService")
	FunctionServiceRemote functionService;

	private Bpu bpu;
	private BpuHelper bpuHelper;
	private Function function;

	/**
	 * 
	 */
	@Before
	public void set() {
		bpuHelper = new BpuHelper(legalEntityServiceRemote,
				citizenServiceRemote, institutionServiceRemote,
				bpuServiceRemote, bpiServiceRemote);
		bpu = bpuHelper.createBpu();

		function = new Function(TESTCODE, null);

		try {
			function = functionService.save(function);
		} catch (final GHAEJBException e) {
			Assert.fail("error saving function");
		}

	}

	/**
	 * 
	 */
	@Test
	public void test() {
		System.out.println("\n TESTING FUNCTION BPU SERVICE\n\n");

		Assert.assertNotNull(service);
		FunctionBpu save = null;
		try {
			save = service.save(new FunctionBpu(bpu, function));
			Assert.assertEquals(TESTCODE + bpu.getId(), save.getCode());
		} catch (final GHAEJBException e) {
			Assert.fail("failing in creating functionBpu");
		}

		try {
			final List<Function> permissionByBpu = service
					.getFunctionByBpu(bpu);
			Assert.assertTrue(permissionByBpu.size() > 0);
		} catch (final GHAEJBException e) {
			Assert.fail("failing in listing functionBpus");
		}

		try {
			service.delete(save);
		} catch (final GHAEJBException e) {
			Assert.fail("failing in deleting functionBpu");
		}

	}

	/**
	 * 
	 */
	@After
	public void unset() {
		bpuHelper.removeBpu();

		try {
			functionService.delete(function);
		} catch (final GHAEJBException e) {
			Assert.fail("error saving function");
		}
	}
}