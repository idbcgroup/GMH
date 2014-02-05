package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
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
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationType;
import org.fourgeeks.gha.domain.enu.ProviderPreferenceEnum;
import org.fourgeeks.gha.domain.enu.ProviderQualEnum;
import org.fourgeeks.gha.domain.enu.ProviderRepresentEnum;
import org.fourgeeks.gha.domain.enu.ProviderResourceTypeEnum;
import org.fourgeeks.gha.domain.enu.ProviderServicesEnum;
import org.fourgeeks.gha.domain.enu.ProviderTypeEnum;
import org.fourgeeks.gha.domain.enu.ServiceAndResourceType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.LocationType;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.ui.AppForm;
import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
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
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;
import org.fourgeeks.gha.ejb.ess.RoleService;
import org.fourgeeks.gha.ejb.ess.RoleServiceRemote;
import org.fourgeeks.gha.ejb.gar.ObuService;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.ejb.glm.ExternalProviderService;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg, naramirez
 * 
 */
@RunWith(Arquillian.class)
public class SubProtocolAndChecklistServiceTest {
	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClass(AbstractEntity.class)
				.addClass(AbstractCodeEntity.class)
				.addClass(AppForm.class)
				.addClass(AppFormViewFunction.class)
				.addClass(AppFormViewFunctionBpu.class)
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
				.addClass(EiaServiceTest.class)
				.addClass(EiaServiceRemote.class)
				.addClass(EiaPreventiveMaintenancePlanification.class)
				.addClass(ExternalProvider.class)
				.addClass(Facility.class)
				.addClass(FacilityCategory.class)
				.addClass(Function.class)
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
				.addClass(MaintenancePlanificationStatus.class)
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
				.addClass(Role.class)
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
				.addClass(MaintenanceActivityService.class)
				.addClass(MaintenanceActivityServiceRemote.class)
				.addClass(SubProtocolAndCheklistService.class)
				.addClass(SubProtocolAndCheklistServiceRemote.class)
				.addClass(SubProtocolAndCheklistServiceLocal.class)
				.addClass(MaintenanceActivity.class)
				.addClass(ServiceAndResourceType.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB(lookup = "java:global/test/SubProtocolAndCheklistService!"
			+ "org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceRemote")
	private SubProtocolAndCheklistServiceRemote serviceRemote;

	@EJB(lookup = "java:global/test/SubProtocolAndCheklistService!"
			+ "org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceLocal")
	private SubProtocolAndCheklistServiceLocal serviceLocal;

	private Activity activity;
	private Activity parentActivity;
	private SubProtocolAndChecklist subProtocol;

	/** */
	@Before
	public void set() {
		activity = new Activity();
		activity.setId(1);

		parentActivity = new Activity();
		parentActivity.setId(7);

		subProtocol = new SubProtocolAndChecklist();
		subProtocol.setActivity(activity);
		subProtocol.setParentActivity(parentActivity);
		subProtocol.setOrdinal(4);
	}

	/** */
	@After
	public void unset() {

	}

	/** */
	@Test
	public void test() {
		final String sep = "\n---------------------------------------\n";

		System.out.println("TESTING SUBPROTOCOL AND CHECKLIST SERVICE\n");

		System.out.println(sep + "getAllTest" + sep);
		getAllTest();
		System.out.println(sep + "getAllTest2" + sep);
		getAllTest2();
		System.out.println(sep + "findByParentActivityTest" + sep);
		findByParentActivityTest();
		System.out.println(sep + "findByIdTest" + sep);
		findByIdTest();
		System.out.println(sep + "getSubProtocolActivitiesCountTest" + sep);
		getSubProtocolActivitiesCountTest();
		System.out.println(sep + "getSubProtocolCostTest" + sep);
		getSubProtocolCostTest();
		System.out.println(sep + "getSubProtocolDurationTest" + sep);
		getSubProtocolDurationTest();
		System.out.println(sep + "saveTest" + sep);
		saveTest();
		System.out.println(sep + "updateTest" + sep);
		updateTest();
		System.out.println(sep + "deleteTest" + sep);
		deleteTest();
		// System.out.println(sep + "deleteListTest" + sep);
		// deleteListTest();
	}

	private void findByParentActivityTest() {
		try {
			Activity activity = new Activity();
			activity.setId(7);

			final List<SubProtocolAndChecklist> result = serviceRemote
					.findByParentActivity(activity);

			Assert.assertEquals(3, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void findByIdTest() {
		try {
			final SubProtocolAndChecklist result = serviceRemote.find(1);
			Assert.assertNotNull(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAllTest() {
		try {
			final List<SubProtocolAndChecklist> result = serviceRemote.getAll();
			Assert.assertEquals(3, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAllTest2() {
		final int itemsExpected = 3;
		try {
			final List<SubProtocolAndChecklist> result = serviceRemote.getAll(
					0, 3);
			Assert.assertEquals(itemsExpected, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveTest() {
		try {
			subProtocol = serviceRemote.save(subProtocol);
			Assert.assertNotNull(subProtocol);
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void updateTest() {
		final int ordinalExpected = 5;
		try {
			subProtocol.setOrdinal(ordinalExpected);
			SubProtocolAndChecklist result = serviceRemote.update(subProtocol);
			final int ordinalResult = result.getOrdinal();

			Assert.assertEquals(ordinalExpected, ordinalResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteTest() {
		final int itemsExpected = 3;
		try {
			serviceRemote.delete(subProtocol.getId());

			Assert.assertEquals(itemsExpected, serviceRemote.getAll().size());
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	// private void deleteListTest() {
	// final int itemsExpected = 1;
	// try {
	// List<SubProtocolAndChecklist> subprotocolos = new
	// ArrayList<SubProtocolAndChecklist>();
	// for (int i = 0; i < 2; i++) {
	// SubProtocolAndChecklist sp = new SubProtocolAndChecklist();
	// sp.setId(i + 1);
	// subprotocolos.add(sp);
	// }
	// serviceRemote.delete(subprotocolos);
	// Assert.assertEquals(itemsExpected, serviceRemote.getAll().size());
	// } catch (GHAEJBException e) {
	// e.printStackTrace();
	// }
	// }

	private void getSubProtocolActivitiesCountTest() {
		final int countExpected = 3;
		try {
			final long result = serviceLocal
					.getSubProtocolActivitiesCount(parentActivity);

			Assert.assertEquals(countExpected, result);
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void getSubProtocolCostTest() {
		final double costExpected = 1896.97;
		try {
			final BigDecimal result = serviceLocal
					.getSubProtocolCost(parentActivity);

			Assert.assertEquals(costExpected, result.doubleValue());
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void getSubProtocolDurationTest() {
		try {
			final int result = serviceLocal
					.getSubProtocolDuration(parentActivity);

			Assert.assertEquals(1, result);
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}
}
