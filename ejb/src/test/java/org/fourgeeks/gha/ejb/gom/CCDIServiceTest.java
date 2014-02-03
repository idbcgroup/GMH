package org.fourgeeks.gha.ejb.gom;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CCDICodeTypeEnum;
import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author emiliot
 * 
 */

@RunWith(Arquillian.class)
public class CCDIServiceTest {
	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(AbstractEntity.class)
				.addClass(CCDICodeTypeEnum.class)
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
	@Test
	public void test() {
		System.out.println("TESTING CCDI SERVICE\n\n\n");

		CCDIDefinition material = null;
		try {
			material = new CCDIDefinition("MATERIAL-test", "MATERIAL", 11, 5,
					CCDIStatusEnum.ACTIVE, null, CCDICodeTypeEnum.ALPHANUMERIC,
					false, "");

			CCDIDefinition material2 = ccdiService
					.createCCDIDefinition(material);
			Assert.assertNotNull(material2);
			Assert.assertEquals("MATERIAL-test", material2.getCode());

		} catch (Exception e1) {
			System.out.println("error creating test ccdi definition in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		material = null;
		try {
			material = ccdiService.findCCDIDefinitionByCode("MATERIAL-test");
		} catch (Exception e1) {
			System.out
					.println("error getting ccdidefinition by code in test\n");
			Assert.fail(e1.getCause().getMessage());
		}
		Assert.assertNotNull(material);
		Assert.assertEquals("MATERIAL-test", material.getCode());

		CCDILevelDefinition materiales = new CCDILevelDefinition(material, 0,
				"MATERIALES-test", 2, CCDIValueTypeEnum.FIXED, 0, 0, "",
				CCDIEndValueActionEnum.RESTART);
		CCDILevelDefinition type = new CCDILevelDefinition(material, 1,
				"TIPO-test", 1, CCDIValueTypeEnum.FIXED, 0, 0, "",
				CCDIEndValueActionEnum.RESTART);
		CCDILevelDefinition family = new CCDILevelDefinition(material, 2,
				"FAMILIA-test", 2, CCDIValueTypeEnum.VARIABLE, 1, 1, "",
				CCDIEndValueActionEnum.RESTART);
		CCDILevelDefinition subFamily = new CCDILevelDefinition(material, 3,
				"SUB FAMILIA-test", 2, CCDIValueTypeEnum.VARIABLE, 1, 1, "",
				CCDIEndValueActionEnum.RESTART);
		CCDILevelDefinition element = new CCDILevelDefinition(material, 4,
				"ELEMENTOS-test", 4, CCDIValueTypeEnum.VARIABLE, 1, 1, "",
				CCDIEndValueActionEnum.RESTART);

		try {
			CCDILevelDefinition materiales2 = ccdiService
					.createCCDILevelDefinition(material, materiales);
			Assert.assertNotNull(materiales2);
			Assert.assertEquals("MATERIALES-test", materiales2.getName());

			CCDILevelDefinition type2 = ccdiService.createCCDILevelDefinition(
					material, type);
			Assert.assertNotNull(type2);
			Assert.assertEquals("TIPO-test", type2.getName());

			CCDILevelDefinition family2 = ccdiService
					.createCCDILevelDefinition(material, family);
			Assert.assertNotNull(family2);
			Assert.assertEquals("FAMILIA-test", family2.getName());

			CCDILevelDefinition subFamily2 = ccdiService
					.createCCDILevelDefinition(material, subFamily);
			Assert.assertNotNull(subFamily2);
			Assert.assertEquals("SUB FAMILIA-test", subFamily2.getName());

			CCDILevelDefinition element2 = ccdiService
					.createCCDILevelDefinition(material, element);
			Assert.assertNotNull(element2);
			Assert.assertEquals("ELEMENTOS-test", element2.getName());

		} catch (Exception e1) {
			System.out
					.println("error creating test ccdi level definition in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		materiales = null;
		type = null;
		family = null;
		subFamily = null;
		element = null;

		try {
			materiales = ccdiService
					.findCCDILevelDefinitionByLevel(material, 0);
			Assert.assertNotNull(materiales);
			Assert.assertEquals("MATERIALES-test", materiales.getName());

			type = ccdiService.findCCDILevelDefinitionByLevel(material, 1);
			Assert.assertNotNull(type);
			Assert.assertEquals("TIPO-test", type.getName());

			family = ccdiService.findCCDILevelDefinitionByLevel(material, 2);
			Assert.assertNotNull(family);
			Assert.assertEquals("FAMILIA-test", family.getName());

			subFamily = ccdiService.findCCDILevelDefinitionByLevel(material, 3);
			Assert.assertNotNull(subFamily);
			Assert.assertEquals("SUB FAMILIA-test", subFamily.getName());

			element = ccdiService.findCCDILevelDefinitionByLevel(material, 4);
			Assert.assertNotNull(element);
			Assert.assertEquals("ELEMENTOS-test", element.getName());

		} catch (Exception e1) {
			System.out.println("error getting ccdi level definition in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		CCDILevelValue materialValue = new CCDILevelValue(null, null,
				"MATERIAL-test", null, 0, "T0", CCDIValueStatusEnum.ACTIVE);

		try {
			materialValue = ccdiService.createCCDILevelValue(materiales, null,
					materialValue);
			Assert.assertNotNull(materialValue);
			Assert.assertEquals("T0", materialValue.getCode());
		} catch (Exception e1) {
			System.out.println("error creating ccdilevelValue in test\n");
			Assert.fail(e1.getCause().getMessage());
		}
	}

	@After
	public void unset() {
		try {
			ccdiService.deleteByCode("MATERIAL-test");
		} catch (GHAEJBException e) {
			Assert.fail(e.getCause().getMessage());
		}
	}
}
