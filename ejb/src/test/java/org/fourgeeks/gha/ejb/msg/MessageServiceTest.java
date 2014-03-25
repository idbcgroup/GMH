package org.fourgeeks.gha.ejb.msg;

import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javax.ejb.EJB;

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
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
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
import org.fourgeeks.gha.ejb.gar.BpuService;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.ejb.log.UILogService;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;
import org.fourgeeks.gha.ejb.log.UILogServiceRemote;
import org.fourgeeks.gha.ejb.mix.BpiService;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.ejb.mix.CitizenService;
import org.fourgeeks.gha.ejb.mix.CitizenServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionService;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityService;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;
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
 * @author emiliot, jfuentes
 * 
 */

@RunWith(Arquillian.class)
public class MessageServiceTest {

	/**
	 * @return the deplyment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(EiaMaintenanceState.class)
				.addClass(MaintenanceCancelationCause.class)
				.addClass(AppView.class)
				.addClass(Citizen.class)
				.addClass(EiaMaintenance.class)
				.addClass(EiaPreventiveMaintenance.class)
				.addClass(AbstractEntity.class)
				.addClass(AbstractCodeEntity.class)
				.addClass(Activity.class)
				.addClass(ActivityCategoryEnum.class)
				.addClass(ActivitySubCategoryEnum.class)
				.addClass(FunctionBpu.class)
				.addClass(ActivityState.class)
				.addClass(App.class)
				.addClass(Bpu.class)
				.addClass(BpuService.class)
				.addClass(BpuServiceRemote.class)
				.addClass(Brand.class)
				.addClass(Bpi.class)
				.addClass(BpiService.class)
				.addClass(BpiServiceRemote.class)
				.addClass(Bsp.class)
				.addClass(BpiOriginEnum.class)
				.addClass(BpiTypeEnum.class)
				.addClass(BpiInstitutionRelationTypeEnum.class)
				.addClass(BpiRiskEnum.class)
				.addClass(BuildingLocation.class)
				.addClass(SSOUser.class)
				.addClass(CitizenServiceRemote.class)
				.addClass(CitizenService.class)
				.addClass(CurrencyTypeEnum.class)
				.addClass(DepreciationMethodEnum.class)
				.addClass(DocumentTypeEnum.class)
				.addClass(Eia.class)
				.addClass(EiaStateEnum.class)
				.addClass(EiaDamageReport.class)
				.addClass(EiaDamagePriorityEnum.class)
				.addClass(EiaDamageStatusEnum.class)
				.addClass(EiaSubTypeEnum.class)
				.addClass(EiaPreventiveMaintenance.class)
				.addClass(EiaType.class)
				.addClass(EiaTypeCategory.class)
				.addClass(EiaTypeEnum.class)
				.addClass(EiaTypeMaintenancePlan.class)
				.addClass(EiaMaintenancePlanification.class)
				.addClass(EiaMobilityEnum.class)
				.addClass(ExternalProvider.class)
				.addClass(Facility.class)
				.addClass(FacilityCategory.class)
				.addClass(Function.class)
				.addClass(GHALog.class)
				.addClass(GHAEJBException.class)
				.addClass(GHAMessage.class)
				.addClass(GHAMessageId.class)
				.addClass(GHAEJBExceptionService.class)
				.addClass(GHAMessageType.class)
				.addClass(GenderTypeEnum.class)
				.addClass(HasKey.class)
				.addClass(Institution.class)
				.addClass(InstitutionService.class)
				.addClass(InstitutionServiceRemote.class)
				.addClass(Job.class)
				.addClass(JobPosition.class)
				.addClass(JobCategory.class)
				.addClass(LanguageEnum.class)
				.addClass(LocationLevelEnum.class)
				.addClass(LegalEntity.class)
				.addClass(LegalEntityService.class)
				.addClass(LegalEntityServiceRemote.class)
				.addClass(LocationType.class)
				.addClass(MaintenanceActivity.class)
				.addClass(MaintenanceProtocol.class)
				.addClass(Manufacturer.class)
				.addClass(RequiredResources.class)
				.addClass(MaintenancePlan.class)
				.addClass(MaintenancePlanificationState.class)
				.addClass(MaintenancePlanState.class)
				.addClass(MaintenancePlanStatus.class)
				.addClass(MaintenancePlanType.class)
				.addClass(MaintenancePlanCancelationOption.class)
				.addClass(MessageServiceRemote.class)
				.addClass(MessageServiceLocal.class)
				.addClass(MessageService.class)
				.addClass(Module.class)
				.addClass(Obu.class)
				.addClass(ProviderResourceTypeEnum.class)
				.addClass(ProviderPreferenceEnum.class)
				.addClass(ProviderQualEnum.class)
				.addClass(ProviderRepresentEnum.class)
				.addClass(ProviderServicesEnum.class)
				.addClass(ProviderTypeEnum.class)
				.addClass(Role.class)
				.addClass(RuntimeParameters.class)
				.addClass(ServiceAndResource.class)
				.addClass(ServiceResourceCategory.class)
				.addClass(TimePeriodEnum.class)
				.addClass(UILogService.class)
				.addClass(UILogServiceRemote.class)
				.addClass(UILogServiceLocal.class)
				.addClass(UILog.class)
				.addClass(View.class)
				.addClass(WorkingArea.class)
				.addClass(WarrantySinceEnum.class)
				.addClass(SSOUserService.class)
				.addClass(SSOUserServiceRemote.class)
				.addClass(SSOUser.class)
				.addClass(UserLogonStatusEnum.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/test/MessageService!org.fourgeeks.gha.ejb.msg.MessageServiceLocal")
	MessageServiceLocal messageServiceLocal;

	@EJB(lookup = "java:global/test/MessageService!org.fourgeeks.gha.ejb.msg.MessageServiceRemote")
	MessageServiceRemote messageServiceRemote;

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceRemote")
	UILogServiceRemote uILogServiceRemote;

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceLocal")
	UILogServiceLocal uILogServiceLocal;

	/**
	 */
	@Before
	public void set() {

	}

	/**
	 * 
	 */
	@Test
	public void test() {
		assertNotNull(messageServiceLocal);
		assertNotNull(messageServiceRemote);

		final String code = "GHAMESSAGE-TESTCODE";
		GHAMessage ghaMessage = new GHAMessage(code, LanguageEnum.ES);
		ghaMessage.setText("ghaMessage unit test");

		try {
			ghaMessage = messageServiceLocal.save(ghaMessage);
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(ghaMessage);

		GHAMessage result = null;
		try {
			result = messageServiceRemote.findAndLog(null, code);
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull(result);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e2) {
			unset();
			Assert.fail(e2.getMessage());
		}

		try {
			List<UILog> uiLogList = uILogServiceRemote.getAll();
			for (final UILog u : uiLogList) {
				uILogServiceLocal.delete(u);
			}
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}

		try {
			messageServiceLocal.delete(ghaMessage);
		} catch (final GHAEJBException e) {
			unset();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 */
	@After
	public void unset() {

	}
}
