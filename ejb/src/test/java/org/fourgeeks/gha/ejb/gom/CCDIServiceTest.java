package org.fourgeeks.gha.ejb.gom;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.HasKey;
import org.fourgeeks.gha.domain.enu.BpiInstitutionRelationTypeEnum;
import org.fourgeeks.gha.domain.enu.BpiOriginEnum;
import org.fourgeeks.gha.domain.enu.BpiRiskEnum;
import org.fourgeeks.gha.domain.enu.BpiTypeEnum;
import org.fourgeeks.gha.domain.enu.CCDICodeTypeEnum;
import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.MaintenanceCancelationCause;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.ProviderPreferenceEnum;
import org.fourgeeks.gha.domain.enu.ProviderQualEnum;
import org.fourgeeks.gha.domain.enu.ProviderRepresentEnum;
import org.fourgeeks.gha.domain.enu.ProviderResourceTypeEnum;
import org.fourgeeks.gha.domain.enu.ProviderServicesEnum;
import org.fourgeeks.gha.domain.enu.ProviderTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.LocationType;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.ess.ui.App;
import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.ess.ui.Module;
import org.fourgeeks.gha.domain.ess.ui.View;
import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.FacilityCategory;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.gar.JobCategory;
import org.fourgeeks.gha.domain.gar.JobPosition;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.logs.GHALog;
import org.fourgeeks.gha.domain.logs.UILog;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserService;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BrandService;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceService;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.ejb.log.UILogService;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;
import org.fourgeeks.gha.ejb.log.UILogServiceRemote;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
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
				.addClass(AbstractCodeEntity.class)
				.addClass(View.class)
				.addClass(Bpu.class)
				.addClass(Institution.class)
				.addClass(LegalEntity.class)
				.addClass(Citizen.class)
				.addClass(Job.class)
				.addClass(JobPosition.class)
				.addClass(BuildingLocation.class)
				.addClass(LocationType.class)
				.addClass(LocationLevelEnum.class)
				.addClass(WorkingArea.class)
				.addClass(Facility.class)
				.addClass(Bpi.class)
				.addClass(BpiTypeEnum.class)
				.addClass(GHALog.class)
				.addClass(UILog.class)
				.addClass(FacilityCategory.class)
				.addClass(UILogServiceLocal.class)
				.addClass(ServiceResourceCategory.class)
				.addClass(ServiceAndResource.class)
				.addClass(AppView.class)
				.addClass(ViewFunction.class)
				.addClass(FunctionBpu.class)
				.addClass(Function.class)
				.addClass(App.class)
				.addClass(Module.class)
				.addClass(BpiInstitutionRelationTypeEnum.class)
				.addClass(BpiOriginEnum.class)
				.addClass(Role.class)
				.addClass(JobCategory.class)
				.addClass(BpiRiskEnum.class)
				.addClass(GenderTypeEnum.class)
				.addClass(DocumentTypeEnum.class)
				.addClass(UILogServiceRemote.class)
				.addClass(UILogService.class)
				.addClass(CCDICodeTypeEnum.class)
				.addClass(LanguageEnum.class)
				.addClass(RuntimeParameters.class)
				.addClass(org.fourgeeks.gha.domain.gom.Concept.class)
				.addClass(CCDIDefinition.class)
				.addClass(CCDILevelDefinition.class)
				.addClass(Obu.class)
				.addClass(CCDILevelValue.class)
				.addClass(CCDIServiceRemote.class)
				.addClass(CCDIServiceLocal.class)
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
				.addClass(EiaCorrectiveMaintenance.class)
				.addClass(SSOUser.class)
				.addClass(SSOUserService.class)
				.addClass(SSOUserServiceRemote.class)
				.addClass(EiaMaintenance.class)
				.addClass(UserLogonStatusEnum.class)
				.addClass(MaintenanceCancelationCause.class)
				.addClass(MaintenancePlanificationState.class)
				.addClass(Bsp.class)
				.addClass(TimePeriodEnum.class)
				.addClass(EiaStateEnum.class)
				.addClass(EiaMaintenanceState.class)
				.addClass(EiaDamageReport.class)
				.addClass(Eia.class)
				.addClass(ExternalProvider.class)
				.addClass(ProviderPreferenceEnum.class)
				.addClass(ProviderResourceTypeEnum.class)
				.addClass(EiaDamageStatusEnum.class)
				.addClass(CurrencyTypeEnum.class)
				.addClass(EiaDamagePriorityEnum.class)
				.addClass(DepreciationMethodEnum.class)
				.addClass(EiaType.class)
				.addClass(ProviderServicesEnum.class)
				.addClass(ProviderQualEnum.class)
				.addClass(WarrantySinceEnum.class)
				.addClass(EiaMaintenancePlanification.class)
				.addClass(ProviderRepresentEnum.class)
				.addClass(ProviderTypeEnum.class)
				.addClass(Brand.class)
				.addClass(BrandService.class)
				.addClass(BrandServiceRemote.class)
				.addClass(Manufacturer.class)
				.addClass(HasKey.class)
				.addClass(EiaTypeMaintenancePlan.class)
				.addClass(MaintenancePlan.class)
				.addClass(EiaMobilityEnum.class)
				.addClass(MaintenancePlanType.class)
				.addClass(MaintenancePlanCancelationOption.class)
				.addClass(EiaSubTypeEnum.class)
				.addClass(EiaTypeCategory.class)
				.addClass(EiaTypeEnum.class)
				.addClass(MaintenancePlanState.class)
				.addClass(EiaMaintenanceService.class)
				.addClass(EiaMaintenanceServiceRemote.class)
				.addClass(EiaPreventiveMaintenance.class)
				.addClass(EiaCorrectiveMaintenance.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/ear-1/ejb-1/CCDIService!org.fourgeeks.gha.ejb.gom.CCDIServiceRemote")
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
		}

		CCDILevelValue suppliesValue = new CCDILevelValue(null, null,
				"SUMINISTROS", null, 1, "1", CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue pharmacsValue = new CCDILevelValue(null, null,
				"FARMACOS", null, 1, "4", CCDIValueStatusEnum.ACTIVE);

		try {
			suppliesValue = ccdiService.createCCDILevelValue(type,
					materialValue, suppliesValue);
			Assert.assertNotNull(suppliesValue);
			Assert.assertEquals("T01", suppliesValue.getCode());

			pharmacsValue = ccdiService.createCCDILevelValue(type,
					materialValue, pharmacsValue);
			Assert.assertNotNull(pharmacsValue);
			Assert.assertEquals("T04", pharmacsValue.getCode());
		} catch (Exception e1) {
			System.out.println("error creating ccdilevelValue in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		CCDILevelValue needlesValue = new CCDILevelValue(null, null, "AGUJAS",
				null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue syringeValue = new CCDILevelValue(null, null,
				"INYECTADORAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue antiBiotics = new CCDILevelValue(null, null,
				"ANTIBIOTICOS", null, 1, null, CCDIValueStatusEnum.ACTIVE);

		try {
			needlesValue = ccdiService.createCCDILevelValue(family,
					suppliesValue, needlesValue);
			Assert.assertNotNull(needlesValue);
			Assert.assertEquals("T0101", needlesValue.getCode());

			syringeValue = ccdiService.createCCDILevelValue(family,
					suppliesValue, syringeValue);
			Assert.assertNotNull(syringeValue);
			Assert.assertEquals("T0102", syringeValue.getCode());

			antiBiotics = ccdiService.createCCDILevelValue(family,
					pharmacsValue, antiBiotics);
			Assert.assertNotNull(antiBiotics);
			Assert.assertEquals("T0401", antiBiotics.getCode());
		} catch (Exception e1) {
			System.out.println("error creating ccdilevelValue in test\n");
			Assert.fail(e1.getCause().getMessage());
		}

		CCDILevelValue hypodermic = new CCDILevelValue(null, null,
				"HIPODERMICAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue puncture = new CCDILevelValue(null, null, "PUNCION",
				null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue insuline = new CCDILevelValue(null, null, "INSULINA",
				null, 1, null, CCDIValueStatusEnum.ACTIVE);
		CCDILevelValue penicilline = new CCDILevelValue(null, null,
				"PENICILINA", null, 1, null, CCDIValueStatusEnum.ACTIVE);

		try {
			hypodermic = ccdiService.createCCDILevelValue(subFamily,
					needlesValue, hypodermic);
			Assert.assertNotNull(hypodermic);
			Assert.assertEquals("T010101", hypodermic.getCode());

			puncture = ccdiService.createCCDILevelValue(subFamily,
					needlesValue, puncture);
			Assert.assertNotNull(puncture);
			Assert.assertEquals("T010102", puncture.getCode());

			insuline = ccdiService.createCCDILevelValue(subFamily,
					syringeValue, insuline);
			Assert.assertNotNull(insuline);
			Assert.assertEquals("T010201", insuline.getCode());

			penicilline = ccdiService.createCCDILevelValue(subFamily,
					antiBiotics, penicilline);
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

			String farm01 = ccdiService.getNextElementCode(pharmacsValue
					.getCode());
			Assert.assertNotNull(farm01);
			Assert.assertEquals("T04XXXX0001", farm01);

		} catch (GHAEJBException e) {
			System.out.println("error getting ccdilevelValue in test\n");
			Assert.fail(e.getCause().getMessage());
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
