package org.fourgeeks.gha.ejb.ess.auth;

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
import org.fourgeeks.gha.domain.enu.CredentialTypeEnum;
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
import org.fourgeeks.gha.domain.enu.ItSystemEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.MaintenanceCancelationCause;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
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
import org.fourgeeks.gha.domain.ess.auth.InstanceLogon;
import org.fourgeeks.gha.domain.ess.auth.ItSystem;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.ess.auth.SystemInstance;
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
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;
import org.fourgeeks.gha.ejb.ess.ui.ViewFunctionService;
import org.fourgeeks.gha.ejb.ess.ui.ViewFunctionServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BrandService;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceService;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIService;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.fourgeeks.gha.ejb.log.UILogService;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;
import org.fourgeeks.gha.ejb.log.UILogServiceRemote;
import org.fourgeeks.gha.ejb.mix.BpaService;
import org.fourgeeks.gha.ejb.mix.BpaServiceRemote;
import org.fourgeeks.gha.ejb.msg.MessageService;
import org.fourgeeks.gha.ejb.msg.MessageServiceLocal;
import org.fourgeeks.gha.ejb.msg.MessageServiceRemote;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author alacret
 * 
 */
@RunWith(Arquillian.class)
public class FunctionServiceTest {
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
				.addClass(Bpa.class)
				.addClass(BpaServiceRemote.class)
				.addClass(BpaService.class)
				.addClass(Bpu.class)
				.addClass(BuildingLocation.class)
				.addClass(Citizen.class)
				.addClass(CCDICodeTypeEnum.class)
				.addClass(CCDIDefinition.class)
				.addClass(CCDIEndValueActionEnum.class)
				.addClass(CCDILevelDefinition.class)
				.addClass(CCDILevelValue.class)
				.addClass(CCDIService.class)
				.addClass(CCDIServiceRemote.class)
				.addClass(CCDIStatusEnum.class)
				.addClass(CCDIValueStatusEnum.class)
				.addClass(CCDIValueTypeEnum.class)
				.addClass(CCDIServiceLocal.class)
				.addClass(Job.class)
				.addClass(JobPosition.class)
				.addClass(Institution.class)
				.addClass(ItSystem.class)
				.addClass(ItSystemEnum.class)
				.addClass(LegalEntity.class)
				.addClass(InstanceLogon.class)
				.addClass(InstanceLogonService.class)
				.addClass(InstanceLogonServiceRemote.class)
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
				.addClass(CredentialTypeEnum.class)
				.addClass(CCDILevelDefinition.class)
				.addClass(Obu.class)
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
				.addClass(MaintenancePlanificationType.class)
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
				.addClass(MessageService.class)
				.addClass(MessageServiceLocal.class)
				.addClass(MessageServiceRemote.class)
				.addClass(MaintenancePlanCancelationOption.class)
				.addClass(EiaSubTypeEnum.class)
				.addClass(EiaTypeCategory.class)
				.addClass(EiaTypeEnum.class)
				.addClass(MaintenancePlanState.class)
				.addClass(EiaMaintenanceService.class)
				.addClass(EiaMaintenanceServiceRemote.class)
				.addClass(EiaPreventiveMaintenance.class)
				.addClass(EiaCorrectiveMaintenance.class)
				.addClass(SystemInstance.class)
				.addClass(ViewFunction.class)
				.addClass(ViewFunctionService.class)
				.addClass(ViewFunctionServiceRemote.class)
				.addClass(FunctionBpu.class)
				.addClass(FunctionBpuService.class)
				.addClass(FunctionBpuServiceRemote.class)
				.addClass(Function.class)
				.addClass(FunctionService.class)
				.addClass(FunctionServiceRemote.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/test/FunctionService")
	FunctionServiceRemote functionService;

	/**
	 * 
	 */
	@Test
	public void test() {
		System.out.println("\n TESTING FUNCTION SERVICE\n\n");

		final String code = "TESTCODE" + Math.random() / 10;

		Assert.assertNotNull(functionService);
		final Function originalPermission = new Function();
		originalPermission.setCode(code);
		Function newPermission = null;
		try {
			newPermission = functionService.save(originalPermission);
			Assert.assertEquals(newPermission.getCode(), code);
		} catch (final GHAEJBException e) {
			Assert.fail("error saving the function");
		}

		Assert.assertEquals(originalPermission.getCode(),
				newPermission.getCode());

		try {
			functionService.delete(newPermission);
		} catch (final GHAEJBException e) {
			Assert.fail("error deleting the function");
		}

		// try {
		// functionService.getAppViewsByBpu(bpu);
		// } catch (final GHAEJBException e) {
		// Assert.fail("error gettting the appview for a user");
		// }

	}

}