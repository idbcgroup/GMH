package org.fourgeeks.gha.ejb.ess;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.HasKey;
import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
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
import org.fourgeeks.gha.domain.enu.MaintenancePlanStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.enu.ProviderPreferenceEnum;
import org.fourgeeks.gha.domain.enu.ProviderQualEnum;
import org.fourgeeks.gha.domain.enu.ProviderRepresentEnum;
import org.fourgeeks.gha.domain.enu.ProviderResourceTypeEnum;
import org.fourgeeks.gha.domain.enu.ProviderServicesEnum;
import org.fourgeeks.gha.domain.enu.ProviderTypeEnum;
import org.fourgeeks.gha.domain.enu.ServiceAndResourceType;
import org.fourgeeks.gha.domain.enu.ServiceOrderState;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.LocationType;
import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.ess.ui.Module;
import org.fourgeeks.gha.domain.ess.ui.View;
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
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocolStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.gom.Concept;
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
import org.fourgeeks.gha.ejb.ess.auth.RoleService;
import org.fourgeeks.gha.ejb.ess.auth.RoleServiceRemote;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserService;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.gar.BpuService;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.ejb.gar.ObuService;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.ejb.glm.ExternalProviderService;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaDamageReportService;
import org.fourgeeks.gha.ejb.gmh.EiaDamageReportServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationService;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceLocal;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceService;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaService;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentService;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceLocal;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanService;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeService;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.ejb.gmh.MaintenanceActivityService;
import org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote;
import org.fourgeeks.gha.ejb.gmh.MaintenancePlanService;
import org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote;
import org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsService;
import org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolsServiceRemote;
import org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistService;
import org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceLocal;
import org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIService;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.fourgeeks.gha.ejb.log.UILogService;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;
import org.fourgeeks.gha.ejb.log.UILogServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionService;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityService;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;
import org.fourgeeks.gha.ejb.pdt.PDTMessageProducer;
import org.fourgeeks.gha.ejb.pdt.PDTMessageProducerLocal;
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
 * @author naramirez
 */
@RunWith(Arquillian.class)
public class MaintenanceServiceOrderServiceTest {
	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(AbstractEntity.class)
				.addClass(AbstractCodeEntity.class)
				.addClass(Bpi.class)
				.addClass(BpiOriginEnum.class)
				.addClass(BpiRiskEnum.class)
				.addClass(BpiInstitutionRelationTypeEnum.class)
				.addClass(BpiTypeEnum.class)
				.addClass(Brand.class)
				.addClass(BuildingLocation.class)
				.addClass(Bpu.class)
				.addClass(Citizen.class)
				.addClass(CurrencyTypeEnum.class)
				.addClass(DepreciationMethodEnum.class)
				.addClass(DocumentTypeEnum.class)
				.addClass(Eia.class)
				.addClass(EiaStateEnum.class)
				.addClass(EiaDamageReport.class)
				.addClass(EiaMaintenancePlanification.class)
				.addClass(EiaDamageStatusEnum.class)
				.addClass(EiaMobilityEnum.class)
				.addClass(EiaTypeEnum.class)
				.addClass(EiaSubTypeEnum.class)
				.addClass(EiaTypeCategory.class)
				.addClass(EiaType.class)
				.addClass(EiaTypeComponent.class)
				.addClass(EiaTypeMaintenancePlan.class)
				.addClass(EiaTypeComponentService.class)
				.addClass(EiaTypeComponentServiceLocal.class)
				.addClass(EiaTypeComponentServiceRemote.class)
				.addClass(EiaTypeService.class)
				.addClass(EiaTypeServiceRemote.class)
				.addClass(EiaDamagePriorityEnum.class)
				.addClass(EiaService.class)
				.addClass(ExternalProvider.class)
				.addClass(ExternalProviderService.class)
				.addClass(ExternalProviderServiceRemote.class)
				.addClass(EiaServiceRemote.class)
				.addClass(EiaPreventiveMaintenance.class)
				.addClass(ExternalProvider.class)
				.addClass(Facility.class)
				.addClass(FacilityCategory.class)
				.addClass(GenderTypeEnum.class)
				.addClass(GHAEJBException.class)
				.addClass(GHAMessage.class)
				.addClass(GHAMessageId.class)
				.addClass(GHAEJBExceptionService.class)
				.addClass(HasKey.class)
				.addClass(Institution.class)
				.addClass(InstitutionService.class)
				.addClass(InstitutionServiceRemote.class)
				.addClass(Job.class)
				.addClass(JobCategory.class)
				.addClass(JobPosition.class)
				.addClass(LegalEntity.class)
				.addClass(LegalEntityService.class)
				.addClass(LegalEntityServiceRemote.class)
				.addClass(LocationLevelEnum.class)
				.addClass(LocationType.class)
				.addClass(LanguageEnum.class)
				.addClass(MaintenancePlan.class)
				.addClass(MaintenancePlanStatus.class)
				.addClass(MaintenancePlanCancelationOption.class)
				.addClass(MaintenancePlanState.class)
				.addClass(MaintenancePlanType.class)
				.addClass(EiaMaintenanceState.class)
				.addClass(MaintenancePlanificationType.class)
				.addClass(MaintenanceProtocols.class)
				.addClass(ActivityState.class)
				.addClass(ActivityCategoryEnum.class)
				.addClass(Manufacturer.class)
				.addClass(ActivitySubCategoryEnum.class)
				.addClass(Activity.class)
				.addClass(RequiredResources.class)
				.addClass(Module.class)
				.addClass(Obu.class)
				.addClass(ObuService.class)
				.addClass(ObuServiceRemote.class)
				.addClass(ProviderResourceTypeEnum.class)
				.addClass(ProviderServicesEnum.class)
				.addClass(ProviderPreferenceEnum.class)
				.addClass(ProviderQualEnum.class)
				.addClass(ProviderTypeEnum.class)
				.addClass(ProviderRepresentEnum.class)
				.addClass(RoleService.class)
				.addClass(RoleServiceRemote.class)
				.addClass(RuntimeParameters.class)
				.addClass(ServiceAndResource.class)
				.addClass(ServiceResourceCategory.class)
				.addClass(TimePeriodEnum.class)
				.addClass(View.class)
				.addClass(WorkingArea.class)
				.addClass(WarrantySinceEnum.class)
				.addClass(GHAMessageType.class)
				.addClass(Activity.class)
				.addClass(Bsp.class)
				.addClass(SubProtocolAndChecklist.class)
				.addClass(MaintenanceActivity.class)
				.addClass(MaintenanceActivityService.class)
				.addClass(MaintenanceActivityServiceRemote.class)
				.addClass(SubProtocolAndCheklistService.class)
				.addClass(SubProtocolAndCheklistServiceRemote.class)
				.addClass(SubProtocolAndCheklistServiceLocal.class)
				.addClass(EiaMaintenancePlanificationService.class)
				.addClass(EiaMaintenancePlanificationServiceRemote.class)
				.addClass(EiaMaintenancePlanificationServiceLocal.class)
				.addClass(EiaMaintenanceService.class)
				.addClass(EiaMaintenanceServiceRemote.class)
				.addClass(UILog.class)
				.addClass(UILogService.class)
				.addClass(UILogServiceLocal.class)
				.addClass(UILogServiceRemote.class)
				.addClass(SSOUser.class)
				.addClass(SSOUserService.class)
				.addClass(SSOUserServiceRemote.class)
				.addClass(ServiceAndResourceType.class)
				.addClass(GHALog.class)
				.addClass(EiaCorrectiveMaintenance.class)
				.addClass(EiaMaintenance.class)
				.addClass(UserLogonStatusEnum.class)
				.addClass(MaintenanceCancelationCause.class)
				.addClass(MaintenancePlanificationState.class)
				.addClass(EiaDamageReportService.class)
				.addClass(EiaDamageReportServiceRemote.class)
				.addClass(EiaTypeMaintenancePlanService.class)
				.addClass(EiaTypeMaintenancePlanServiceRemote.class)
				.addClass(MaintenancePlanService.class)
				.addClass(MaintenancePlanServiceRemote.class)
				.addClass(MaintenanceProtocolsService.class)
				.addClass(MaintenanceProtocolsServiceRemote.class)
				.addClass(MaintenancePlanStadisticData.class)
				.addClass(MaintenanceProtocolStadisticData.class)
				.addClass(BpuService.class)
				.addClass(BpuServiceRemote.class)
				.addClass(MaintenanceServiceOrder.class)
				.addClass(ServiceOrderState.class)
				.addClass(MaintenanceServiceOrderService.class)
				.addClass(MaintenanceServiceOrderServiceLocal.class)
				.addClass(CCDIDefinition.class)
				.addClass(CCDILevelDefinition.class)
				.addClass(CCDILevelValue.class)
				.addClass(CCDIStatusEnum.class)
				.addClass(CCDIValueStatusEnum.class)
				.addClass(CCDIEndValueActionEnum.class)
				.addClass(CCDIValueTypeEnum.class)
				.addClass(CCDICodeTypeEnum.class)
				.addClass(CCDIService.class)
				.addClass(CCDIServiceLocal.class)
				.addClass(CCDIServiceRemote.class)
				.addClass(PDTMessageProducer.class)
				.addClass(PDTMessageProducerLocal.class)
				.addClass(Concept.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/test/EiaMaintenanceService")
	private EiaMaintenanceServiceRemote maintenanceService;

	@EJB(lookup = "java:global/test/EiaDamageReportService")
	private EiaDamageReportServiceRemote damageReportService;

	@EJB(lookup = "java:global/test/EiaMaintenancePlanificationService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceLocal")
	private EiaMaintenancePlanificationServiceLocal planifServiceLocal;

	@EJB(lookup = "java:global/test/EiaMaintenancePlanificationService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote")
	private EiaMaintenancePlanificationServiceRemote planifServiceRemote;

	@EJB(lookup = "java:global/test/EiaTypeMaintenancePlanService")
	private EiaTypeMaintenancePlanServiceRemote eiaTypeMPlanService;

	@EJB(lookup = "java:global/test/EiaService")
	private EiaServiceRemote eiaService;

	@EJB(lookup = "java:global/test/MaintenancePlanService")
	private MaintenancePlanServiceRemote maintenancePlanService;

	@EJB(lookup = "java:global/test/BpuService")
	private BpuServiceRemote bpuService;

	@EJB(lookup = "java:global/test/MaintenanceServiceOrderService")
	private MaintenanceServiceOrderServiceLocal serviceOrderService;

	private EiaDamageReport eiaDamageReport;
	private EiaMaintenancePlanification planif;
	private EiaTypeMaintenancePlan eiaTypeMPlan;
	private MaintenancePlan maintenancePlan;
	EiaCorrectiveMaintenance maintenance;
	private EiaType eiaType;
	private Eia eia;

	private void deleteTest(MaintenanceServiceOrder entity) {
		final int resultExpected = 0;

		try {
			serviceOrderService.delete(entity.getId());
			final List<MaintenanceServiceOrder> result = serviceOrderService
					.getAll();

			Assert.assertEquals(resultExpected, result.size());
		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void findByEntityTest(MaintenanceServiceOrder entity) {
		final int expectedResult = 1;
		try {
			final List<MaintenanceServiceOrder> result = serviceOrderService
					.find(entity);

			Assert.assertEquals(expectedResult, result.size());

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void findByIdTest(MaintenanceServiceOrder entity) {
		try {
			final long id = entity.getId();
			final MaintenanceServiceOrder result = serviceOrderService.find(id);

			Assert.assertNotNull(result);

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void getAllTest() {
		final int expectedResult = 1;
		try {
			final List<MaintenanceServiceOrder> result = serviceOrderService
					.getAll();

			Assert.assertEquals(expectedResult, result.size());

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private MaintenanceServiceOrder saveTest() {
		try {
			final long time = (new Date()).getTime();

			final MaintenanceServiceOrder entity = new MaintenanceServiceOrder();
			entity.setServiceOrderNumber("MSO1");
			entity.setMaintenance(maintenance);
			entity.setOpeningTimestamp(new Timestamp(time));
			entity.setState(ServiceOrderState.ACTIVE);

			final MaintenanceServiceOrder result = serviceOrderService
					.save(entity);

			Assert.assertNotNull(result);
			return result;

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}

		return null;
	}

	/** */
	@Before
	public void set() {
		System.out
				.println("\n SET - MAINTENANCE SERVICE ORDER SERVICE TEST \n");

		try {
			maintenancePlan = new MaintenancePlan();
			maintenancePlan.setName("mantenimiento prueba");
			maintenancePlan.setFrequency(3);
			maintenancePlan.setPot(TimePeriodEnum.DAYS);
			maintenancePlan.setType(MaintenancePlanType.PREVENTIVE);
			maintenancePlan.setState(MaintenancePlanState.ACTIVE);
			maintenancePlan
					.setCancelationOption(MaintenancePlanCancelationOption.DEFERRABLE);
			maintenancePlan = maintenancePlanService.save(maintenancePlan);

			eiaType = new EiaType("3000000001");

			eia = eiaService.findByEiaType(eiaType).get(0);

			eiaTypeMPlan = new EiaTypeMaintenancePlan();
			eiaTypeMPlan.setEiaType(eiaType);
			eiaTypeMPlan.setMaintenancePlan(maintenancePlan);
			eiaTypeMPlan = eiaTypeMPlanService.save(eiaTypeMPlan);

			planif = new EiaMaintenancePlanification();
			planif.setEia(eia);
			planif.setPlan(eiaTypeMPlan);
			planif = planifServiceRemote.save(planif);

			final Bpu bpu = bpuService.find(1);
			eiaDamageReport = new EiaDamageReport();
			eiaDamageReport.setEia(eia);
			eiaDamageReport.setDamageStatus(EiaDamageStatusEnum.DAMAGE);
			eiaDamageReport.setPriority(EiaDamagePriorityEnum.NORMAL);
			eiaDamageReport.setUserWhoRegistered(bpu);
			eiaDamageReport.setUserWhoReported(bpu);
			eiaDamageReport = damageReportService.save(eiaDamageReport);

			maintenance = new EiaCorrectiveMaintenance();
			maintenance.setDamageReport(eiaDamageReport);
			maintenance.setDescription(eiaDamageReport.getDamageMotive());
			maintenance = maintenanceService
					.saveCorrectiveMaintenance(maintenance);

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	/** */
	@Test
	public void test() {
		final String sep = "\n---------------------------------------\n";

		System.out.println("\n TESTING MAINTENANCE SERVICE ORDER SERVICE\n");

		System.out.println(sep + "saveTest" + sep);
		MaintenanceServiceOrder entity = saveTest();

		System.out.println(sep + "updateTest" + sep);
		entity = updateTest(entity);

		System.out.println(sep + "getAllTest" + sep);
		getAllTest();

		System.out.println(sep + "findByIdTest" + sep);
		findByIdTest(entity);

		System.out.println(sep + "findByEntityTest" + sep);
		findByEntityTest(entity);

		System.out.println(sep + "deleteTest" + sep);
		deleteTest(entity);
	}

	/** */
	@After
	public void unset() {
		System.out
				.println("\n UNSET - MAINTENANCE SERVICE ORDER SERVICE TEST \n");

		try {
			maintenanceService.deleteCorrectiveMaintenance(maintenance.getId());
			planifServiceRemote.delete(planif.getId());
			eiaTypeMPlanService.delete(eiaTypeMPlan.getId());
			maintenancePlanService.delete(maintenancePlan.getId());
			damageReportService.delete(eiaDamageReport.getId());

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private MaintenanceServiceOrder updateTest(MaintenanceServiceOrder entity) {
		try {
			final String newValue = "XXX";
			entity.setServiceOrderNumber(newValue);

			final MaintenanceServiceOrder result = serviceOrderService
					.update(entity);

			Assert.assertEquals(newValue, result.getServiceOrderNumber());
			return result;

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}

		return null;
	}
}
