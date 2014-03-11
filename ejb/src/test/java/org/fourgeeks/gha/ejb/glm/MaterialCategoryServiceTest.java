package org.fourgeeks.gha.ejb.glm;

import java.util.List;

import javax.ejb.EJB;

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
import org.fourgeeks.gha.domain.enu.ServiceOrderState;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.LocationType;
import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
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
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
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
import org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderService;
import org.fourgeeks.gha.ejb.ess.MaintenanceServiceOrderServiceLocal;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserService;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BrandService;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceService;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.ejb.gmh.ManufacturerService;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIService;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.fourgeeks.gha.ejb.log.UILogService;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;
import org.fourgeeks.gha.ejb.log.UILogServiceRemote;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class MaterialCategoryServiceTest {
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
				.addClass(Material.class)
				.addClass(MaterialTypeEnum.class)
				.addClass(MaterialCategory.class)
				.addClass(MaterialCategoryServiceRemote.class)
				.addClass(MaterialCategoryService.class)
				.addClass(ServiceResourceCategory.class)
				.addClass(MaterialBrand.class)
				.addClass(MaterialBrandServiceRemote.class)
				.addClass(MaterialBrandService.class)
				.addClass(BrandServiceRemote.class)
				.addClass(BrandService.class)
				.addClass(ManufacturerServiceRemote.class)
				.addClass(ManufacturerService.class)
				.addClass(EiaMaintenanceService.class)
				.addClass(EiaMaintenanceServiceRemote.class)
				.addClass(MaintenanceServiceOrder.class)
				.addClass(MaintenanceServiceOrderServiceLocal.class)
				.addClass(MaintenanceServiceOrderService.class)
				.addClass(ServiceOrderState.class)
				.addClass(MaterialService.class)
				.addClass(MaterialServiceRemote.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/ear-1/ejb-1/MaterialCategoryService")
	MaterialCategoryServiceRemote service;

	@Before
	public void set() {
	}

	@Test
	public void test() {
		MaterialCategory category1 = new MaterialCategory();
		category1.setName("test-1");
		category1.setCode("T010101");
		MaterialCategory category2 = new MaterialCategory();
		category2.setName("test-2");
		category2.setCode("T010102");

		try {
			MaterialCategory test1 = service.save(category1);
			Assert.assertNotNull(test1);
			Assert.assertEquals(category1.getName(), test1.getName());
			Assert.assertEquals(category1.getCode(), test1.getCode());
			MaterialCategory test2 = service.save(category2);
			Assert.assertNotNull(test2);
			Assert.assertEquals(category2.getName(), test2.getName());
			Assert.assertEquals(category2.getCode(), test2.getCode());
		} catch (GHAEJBException e) {
			System.out.println("Error saving materialcategories");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			List<MaterialCategory> categories = service.getAll();
			Assert.assertNotNull(categories);
			categories = null;

			categories = service.getAll(1, 1);
			Assert.assertNotNull(categories);
			Assert.assertEquals(1, categories.size());
		} catch (GHAEJBException e) {
			System.out.println("Error getting materialcategories");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			MaterialCategory criteria = new MaterialCategory();
			criteria.setName("test-");

			List<MaterialCategory> categories = service.find(criteria);
			Assert.assertNotNull(categories);
			Assert.assertEquals(2, categories.size());

			for (MaterialCategory category : categories)
				Assert.assertEquals(true, category.getName().startsWith("test"));
		} catch (GHAEJBException e) {
			System.out
					.println("Error find materialcategories by materialcategory");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			MaterialCategory update = new MaterialCategory();
			update.setCode("010101");
			update.setName("test-name-change");
			MaterialCategory updateRes = service.update(update);
			Assert.assertNotNull(updateRes);
			Assert.assertEquals(update.getName(), updateRes.getName());
		} catch (GHAEJBException e) {
			System.out.println("Error getting materialcategories");
			Assert.fail(e.getCause().getMessage());
			unset();
		}
	}

	@After
	public void unset() {
		System.out.println("Beginning Unset");

		try {
			service.delete("T010101");
			service.delete("T010102");
		} catch (GHAEJBException e) {
			Assert.fail(e.getCause().getMessage());
		}
		System.out.println("Unset Done!");
	}
}
