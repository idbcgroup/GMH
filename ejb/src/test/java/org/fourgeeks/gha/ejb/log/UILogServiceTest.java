package org.fourgeeks.gha.ejb.log;

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
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.enu.ProviderPreferenceEnum;
import org.fourgeeks.gha.domain.enu.ProviderQualEnum;
import org.fourgeeks.gha.domain.enu.ProviderRepresentEnum;
import org.fourgeeks.gha.domain.enu.ProviderResourceTypeEnum;
import org.fourgeeks.gha.domain.enu.ProviderServicesEnum;
import org.fourgeeks.gha.domain.enu.ProviderTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.LocationType;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.ui.AppForm;
import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.ess.ui.Function;
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
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityServiceResource;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
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
import org.fourgeeks.gha.ejb.msg.MessageService;
import org.fourgeeks.gha.ejb.msg.MessageServiceLocal;
import org.fourgeeks.gha.ejb.msg.MessageServiceRemote;
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
 * @author alacret
 * 
 */
@RunWith(Arquillian.class)
public class UILogServiceTest {
	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(AbstractEntity.class)
				.addClass(AbstractCodeEntity.class)
				.addClass(Activity.class)
				.addClass(ActivityCategoryEnum.class)
				.addClass(ActivitySubCategoryEnum.class)
				.addClass(AppFormViewFunctionBpu.class)
				.addClass(ActivityState.class)
				.addClass(AppForm.class)
				.addClass(Bpu.class)
				.addClass(Brand.class)
				.addClass(Bpi.class)
				.addClass(Bsp.class)
				.addClass(BpiOriginEnum.class)
				.addClass(BpiTypeEnum.class)
				.addClass(BpiInstitutionRelationTypeEnum.class)
				.addClass(BpiRiskEnum.class)
				.addClass(BuildingLocation.class)
				.addClass(Citizen.class)
				.addClass(CurrencyTypeEnum.class)
				.addClass(DepreciationMethodEnum.class)
				.addClass(DocumentTypeEnum.class)
				.addClass(Eia.class)
				.addClass(EiaStateEnum.class)
				.addClass(EiaDamageReport.class)
				.addClass(EiaDamagePriorityEnum.class)
				.addClass(EiaDamageStatusEnum.class)
				.addClass(EiaSubTypeEnum.class)
				.addClass(EiaPreventiveMaintenancePlanification.class)
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
				.addClass(Job.class)
				.addClass(JobPosition.class)
				.addClass(JobCategory.class)
				.addClass(LanguageEnum.class)
				.addClass(LocationLevelEnum.class)
				.addClass(LegalEntity.class)
				.addClass(LocationType.class)
				.addClass(MaintenanceActivity.class)
				.addClass(MaintenanceProtocols.class)
				.addClass(Manufacturer.class)
				.addClass(MaintenanceActivityServiceResource.class)
				.addClass(MaintenancePlan.class)
				.addClass(MaintenancePlanificationState.class)
				.addClass(MaintenancePlanificationStatus.class)
				.addClass(MaintenancePlanificationType.class)
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
				.addClass(ServiceResource.class)
				.addClass(ServiceResourceCategory.class)
				.addClass(TimePeriodEnum.class)
				.addClass(UILogService.class)
				.addClass(UILogServiceRemote.class)
				.addClass(UILogServiceLocal.class)
				.addClass(UILog.class)
				.addClass(View.class)
				.addClass(WorkingArea.class)
				.addClass(WarrantySinceEnum.class)

				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceRemote")
	UILogServiceRemote uILogServiceRemote;

	@EJB(lookup = "java:global/test/UILogService!org.fourgeeks.gha.ejb.log.UILogServiceLocal")
	UILogServiceLocal uILogServiceLocal;

	@EJB(lookup = "java:global/test/MessageService!org.fourgeeks.gha.ejb.msg.MessageServiceLocal")
	MessageServiceLocal messageServiceLocal;

	private GHAMessage ghaMessage;

	/**
	 * 
	 */
	@Before
	public void set() {
		ghaMessage = new GHAMessage(LanguageEnum.ES, "msg-test",
				"Mensjae de prueba");
		try {
			messageServiceLocal.save(ghaMessage);
		} catch (final GHAEJBException e) {
			// unset();
			// Assert.fail("failing creating the ghamessage: " +
			// e.getMessage());

		}
	}

	/**
	 * 
	 */
	@Test
	public void test() {
		Assert.assertNotNull(uILogServiceRemote);
		Assert.assertNotNull(uILogServiceLocal);
		Assert.assertNotNull(ghaMessage);
		final UILog uiLog = new UILog();
		uiLog.setMessage(ghaMessage);
		uILogServiceRemote.log(uiLog);

		try {
			final List<UILog> all = uILogServiceRemote.getAll();
			Assert.assertEquals(1, all.size());
			for (final UILog uiLog2 : all)
				uILogServiceLocal.delete(uiLog2);
		} catch (final GHAEJBException e) {
			Assert.fail(e.getMessage());
		}

	}

	@After
	public void unset() {
		try {
			messageServiceLocal.delete(ghaMessage);
		} catch (final GHAEJBException e) {
			// System.out.println("AUN NO HE SOLUCIONADO EL PROBLEMA DEL BORRADO");
		}
	}
}
