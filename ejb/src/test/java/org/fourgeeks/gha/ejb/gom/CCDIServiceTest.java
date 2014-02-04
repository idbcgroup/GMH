package org.fourgeeks.gha.ejb.gom;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.enu.CodeTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.gom.Concept;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * @author emiliot
 * 
 */

// @RunWith(Arquillian.class)
public class CCDIServiceTest {
	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(AbstractEntity.class)
				.addClass(CodeTypeEnum.class)
				.addClass(LanguageEnum.class)
				.addClass(RuntimeParameters.class)
				.addClass(org.fourgeeks.gha.domain.gom.Concept.class)
				.addClass(CCDIDefinition.class)
				.addClass(CCDILevelDefinition.class)
				.addClass(CCDILevelValue.class)
				.addClass(CCDIServiceRemote.class)
				.addClass(CCDIService.class)
				.addClass(CCDIEndValueActionEnum.class)
				.addClass(CCDIValueStatusEnum.class)
				.addClass(CCDIValueTypeEnum.class)
				.addClass(CCDIStatusEnum.class)
				.addClass(GHAMessage.class)
				.addClass(GHAMessageId.class)
				.addClass(GHAMessageType.class)
				.addClass(GHAEJBExceptionService.class)
				.addClass(GHAEJBException.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/test/CCDIService")
	CCDIServiceRemote ccdiService;

	/**
	 */
	@Before
	public void set() {
	}

	/**
	 */
	// @Test
	public void test() {
		System.out.println("TESTING CCDI SERVICE\n\n\n");

		try {
			ccdiService.createCCDIDefinition("MATERIAL-test", "Material", 10,
					5, "active", new Concept(), "alphanumeric", false, "");
			ccdiService.createCCDIDefinition("FARMACO-test", "Farmaco", 10, 5,
					"active", new Concept(), "numeric", false, "");
		} catch (GHAEJBException e) {
			System.out.println(e.getCause());
			unset();
			Assert.fail("failing creating ccdi definitions");
		}

		CCDIDefinition material = null, farmaco = null;
		try {
			material = ccdiService.findCCDIDefinitionByCode("MATERIAL-test");
			farmaco = ccdiService.findCCDIDefinitionByCode("FARMACO-test");
		} catch (GHAEJBException e) {
			System.out.println(e.getCause());
			unset();
			Assert.fail("failing creating ccdi definitions");
		}

		Assert.assertNotNull(material);
		Assert.assertNotNull(farmaco);
		Assert.assertEquals("MATERIAL-test", material.getCode());
		Assert.assertEquals("FARMACO-test", farmaco.getCode());

	}

	@After
	public void unset() {
		try {
			ccdiService.delete("MATERIAL-test");
			ccdiService.delete("FARMACO-test");
		} catch (GHAEJBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
