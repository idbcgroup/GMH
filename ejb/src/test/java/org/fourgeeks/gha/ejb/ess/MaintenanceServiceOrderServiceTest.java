package org.fourgeeks.gha.ejb.ess;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.ServiceOrderState;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.ess.auth.RoleServiceRemote;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.ejb.gar.BspServiceRemote;
import org.fourgeeks.gha.ejb.gar.FacilityServiceRemote;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BuildingLocationServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaDamageReportServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceLocal;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaMaintenanceServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeCategoryServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.fourgeeks.gha.ejb.helpers.BpuHelper;
import org.fourgeeks.gha.ejb.helpers.EiaHelper;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.ejb.mix.CitizenServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author naramirez
 */
@RunWith(Arquillian.class)
public class MaintenanceServiceOrderServiceTest extends
		GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/CCDIService!org.fourgeeks.gha.ejb.gom.CCDIServiceRemote")
	CCDIServiceRemote ccdiServiceRemote;

	@EJB(lookup = "java:global/test/CCDIService!org.fourgeeks.gha.ejb.gom.CCDIServiceLocal")
	CCDIServiceLocal ccdiServiceLocal;

	@EJB(lookup = "java:global/test/ManufacturerService")
	private ManufacturerServiceRemote manufacturerServiceRemote;

	@EJB(lookup = "java:global/test/EiaTypeCategoryService")
	private EiaTypeCategoryServiceRemote eiaTypeCategoryServiceRemote;

	@EJB(lookup = "java:global/test/BrandService")
	private BrandServiceRemote brandServiceRemote;

	@EJB(lookup = "java:global/test/EiaTypeService")
	private EiaTypeServiceRemote eiaTypeServiceRemote;

	@EJB(lookup = "java:global/test/RoleService")
	private RoleServiceRemote roleServiceRemote;

	@EJB(lookup = "java:global/test/LegalEntityService")
	private LegalEntityServiceRemote legalEntityServiceRemote;

	@EJB(lookup = "java:global/test/BpiService")
	private BpiServiceRemote bpiServiceRemote;

	@EJB(lookup = "java:global/test/InstitutionService")
	private InstitutionServiceRemote institutionServiceRemote;

	@EJB(lookup = "java:global/test/ObuService")
	private ObuServiceRemote obuServiceRemote;

	@EJB(lookup = "java:global/test/BspService")
	private BspServiceRemote bspServiceRemote;

	@EJB(lookup = "java:global/test/ExternalProviderService")
	private ExternalProviderServiceRemote externalProviderServiceRemote;

	@EJB(lookup = "java:global/test/FacilityService")
	private FacilityServiceRemote facilityServiceRemote;

	@EJB(lookup = "java:global/test/BuildingLocationService")
	private BuildingLocationServiceRemote buildingLocationServiceRemote;

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
	private EiaServiceRemote eiaServiceRemote;

	@EJB(lookup = "java:global/test/MaintenancePlanService")
	private MaintenancePlanServiceRemote maintenancePlanService;

	@EJB(lookup = "java:global/test/BpuService")
	private BpuServiceRemote bpuService;

	@EJB(lookup = "java:global/test/CitizenService")
	private CitizenServiceRemote citizenServiceRemote;

	@EJB(lookup = "java:global/test/MaintenanceServiceOrderService")
	private MaintenanceServiceOrderServiceLocal serviceOrderService;

	private EiaDamageReport eiaDamageReport;
	private EiaMaintenancePlanification planif;
	private EiaTypeMaintenancePlan eiaTypeMPlan;
	private MaintenancePlan maintenancePlan;
	private EiaCorrectiveMaintenance maintenance;
	private EiaHelper eiaHelper;
	private static Calendar calendar;
	private BpuHelper bpuHelper;

	static {
		calendar = Calendar.getInstance();
	}

	private void deleteTest(final MaintenanceServiceOrder entity) {
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

	private void findByEntityTest(final MaintenanceServiceOrder entity) {
		final int expectedResult = 1;
		try {
			final List<MaintenanceServiceOrder> result = serviceOrderService
					.find(entity);

			Assert.assertEquals(expectedResult, result.size());

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void findByIdTest(final MaintenanceServiceOrder entity) {
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

			final MaintenanceServiceOrder entity = new MaintenanceServiceOrder();
			entity.setServiceOrderNumber("MSO1");
			entity.setMaintenance(maintenance);
			entity.setOpeningTimestamp(new Timestamp(calendar.getTimeInMillis()));
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

			eiaHelper = new EiaHelper(ccdiServiceLocal,
					manufacturerServiceRemote, eiaTypeCategoryServiceRemote,
					brandServiceRemote, eiaTypeServiceRemote,
					roleServiceRemote, legalEntityServiceRemote,
					bpiServiceRemote, institutionServiceRemote,
					obuServiceRemote, bspServiceRemote,
					externalProviderServiceRemote, facilityServiceRemote,
					buildingLocationServiceRemote, eiaServiceRemote);
			Eia eia = eiaHelper.createEia();

			eiaTypeMPlan = new EiaTypeMaintenancePlan();
			eiaTypeMPlan.setEiaType(eia.getEiaType());
			eiaTypeMPlan.setMaintenancePlan(maintenancePlan);
			eiaTypeMPlan = eiaTypeMPlanService.save(eiaTypeMPlan);

			planif = new EiaMaintenancePlanification();
			planif.setEia(eia);
			planif.setPlan(eiaTypeMPlan);
			planif.setBeginningDate(new Date(calendar.getTimeInMillis()));
			planif = planifServiceRemote.save(planif);

			bpuHelper = new BpuHelper(legalEntityServiceRemote,
					citizenServiceRemote, institutionServiceRemote, bpuService,
					bpiServiceRemote);
			final Bpu bpu = bpuHelper.createBpu();
			eiaDamageReport = new EiaDamageReport();
			eiaDamageReport.setEia(eia);
			eiaDamageReport.setDamageStatus(EiaDamageStatusEnum.DAMAGE);
			eiaDamageReport.setPriority(EiaDamagePriorityEnum.NORMAL);
			eiaDamageReport.setEiaCondition(EiaStateEnum.ACQUIRED);
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
			bpuHelper.removeBpu();
			eiaHelper.removeEia();

		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private MaintenanceServiceOrder updateTest(
			final MaintenanceServiceOrder entity) {
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
