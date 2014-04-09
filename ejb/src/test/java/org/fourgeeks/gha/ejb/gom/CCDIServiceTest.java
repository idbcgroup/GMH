package org.fourgeeks.gha.ejb.gom;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.CCDICodeTypeEnum;
import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author emiliot
 * 
 */

@RunWith(Arquillian.class)
public class CCDIServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/CCDIService!org.fourgeeks.gha.ejb.gom.CCDIServiceRemote")
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
				"TIPO-test", 1, CCDIValueTypeEnum.FIXED, 1, 1, "",
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
					.createLevelDefinition(materiales);
			Assert.assertNotNull(materiales2);
			Assert.assertEquals("MATERIALES-test", materiales2.getName());

			CCDILevelDefinition type2 = ccdiService.createLevelDefinition(type);
			Assert.assertNotNull(type2);
			Assert.assertEquals("TIPO-test", type2.getName());

			CCDILevelDefinition family2 = ccdiService
					.createLevelDefinition(family);
			Assert.assertNotNull(family2);
			Assert.assertEquals("FAMILIA-test", family2.getName());

			CCDILevelDefinition subFamily2 = ccdiService
					.createLevelDefinition(subFamily);
			Assert.assertNotNull(subFamily2);
			Assert.assertEquals("SUB FAMILIA-test", subFamily2.getName());

			CCDILevelDefinition element2 = ccdiService
					.createLevelDefinition(element);
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

		CCDILevelValue materialValue = new CCDILevelValue(materiales, null,
				"MATERIAL-test", null, 0, "T0", CCDIValueStatusEnum.ACTIVE);

		try {
			materialValue = ccdiService.createLevelValue(materialValue);
			Assert.assertNotNull(materialValue);
			Assert.assertEquals("T0", materialValue.getCode());
		} catch (Exception e1) {
			System.out.println("error creating ccdilevelValue in test\n");
		}

		CCDILevelValue suppliesValue = new CCDILevelValue(type, materialValue,
				"SUMINISTROS", null, 1, "1", CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue pharmacsValue = new CCDILevelValue(type, materialValue,
				"FARMACOS", null, 1, "4", CCDIValueStatusEnum.ACTIVE);

		try {
			suppliesValue = ccdiService.createLevelValue(suppliesValue);
			Assert.assertNotNull(suppliesValue);
			Assert.assertEquals("T01", suppliesValue.getCode());

			pharmacsValue = ccdiService.createLevelValue(pharmacsValue);
			Assert.assertNotNull(pharmacsValue);
			Assert.assertEquals("T04", pharmacsValue.getCode());
		} catch (Exception e1) {
			System.out.println("error creating ccdilevelValue in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		CCDILevelValue needlesValue = new CCDILevelValue(family, suppliesValue,
				"AGUJAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue syringeValue = new CCDILevelValue(family, suppliesValue,
				"INYECTADORAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue antiBiotics = new CCDILevelValue(family, pharmacsValue,
				"ANTIBIOTICOS", null, 1, null, CCDIValueStatusEnum.ACTIVE);

		try {
			needlesValue = ccdiService.createLevelValue(needlesValue);
			Assert.assertNotNull(needlesValue);
			Assert.assertEquals("T0101", needlesValue.getCode());

			syringeValue = ccdiService.createLevelValue(syringeValue);
			Assert.assertNotNull(syringeValue);
			Assert.assertEquals("T0102", syringeValue.getCode());

			antiBiotics = ccdiService.createLevelValue(antiBiotics);
			Assert.assertNotNull(antiBiotics);
			Assert.assertEquals("T0401", antiBiotics.getCode());
		} catch (Exception e1) {
			System.out.println("error creating ccdilevelValue in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		CCDILevelValue hypodermic = new CCDILevelValue(subFamily, needlesValue,
				"HIPODERMICAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue puncture = new CCDILevelValue(subFamily, needlesValue,
				"PUNCION", null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue insuline = new CCDILevelValue(subFamily, syringeValue,
				"INSULINA", null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue penicilline = new CCDILevelValue(subFamily, antiBiotics,
				"PENICILINA", null, 1, null, CCDIValueStatusEnum.ACTIVE);

		try {
			hypodermic = ccdiService.createLevelValue(hypodermic);
			Assert.assertNotNull(hypodermic);
			Assert.assertEquals("T010101", hypodermic.getCode());

			puncture = ccdiService.createLevelValue(puncture);
			Assert.assertNotNull(puncture);
			Assert.assertEquals("T010102", puncture.getCode());

			insuline = ccdiService.createLevelValue(insuline);
			Assert.assertNotNull(insuline);
			Assert.assertEquals("T010201", insuline.getCode());

			penicilline = ccdiService.createLevelValue(penicilline);
			Assert.assertNotNull(penicilline);
			Assert.assertEquals("T040101", penicilline.getCode());
		} catch (Exception e1) {
			System.out.println("error creating ccdilevelValue in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		try {
			String hypodermic1 = ccdiService.getNextElementCode("T010101");
			Assert.assertNotNull(hypodermic1);
			Assert.assertEquals("T0101010001", hypodermic1);

			String hypodermic2 = ccdiService.getNextElementCode("T010101");
			Assert.assertNotNull(hypodermic2);
			Assert.assertEquals("T0101010002", hypodermic2);

			String hypodermic3 = ccdiService.getNextElementCode("T010101");
			Assert.assertNotNull(hypodermic3);
			Assert.assertEquals("T0101010003", hypodermic3);

			String hypodermic4 = ccdiService.getNextElementCode("T010101");
			Assert.assertNotNull(hypodermic4);
			Assert.assertEquals("T0101010004", hypodermic4);

		} catch (GHAEJBException e) {
			System.out.println("error getting ccdilevelValue in test\n");
			Assert.fail(e.getCause().getMessage());
		}

		try {
			String farm01 = ccdiService.getNextElementCode(pharmacsValue
					.getCode());
			Assert.fail("No Exception thrown trying to create new element in an invalid category");
		} catch (GHAEJBException ex) {
			System.out.println("exception thrown good job!");
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
