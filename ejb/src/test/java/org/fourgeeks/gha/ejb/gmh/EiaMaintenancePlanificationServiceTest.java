package org.fourgeeks.gha.ejb.gmh;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.ess.auth.RoleServiceRemote;
import org.fourgeeks.gha.ejb.gar.BspServiceRemote;
import org.fourgeeks.gha.ejb.gar.FacilityServiceRemote;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.fourgeeks.gha.ejb.helpers.EiaHelper;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author naramirez
 * 
 */
@RunWith(Arquillian.class)
public class EiaMaintenancePlanificationServiceTest extends
		GHAArquillianBaseServiceTest {
	private final static Logger logger = Logger
			.getLogger(EiaMaintenancePlanificationServiceTest.class.getName());
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

	@EJB(lookup = "java:global/test/EiaService")
	private EiaServiceRemote eiaServiceRemote;

	// -----------------------------------------------

	@EJB(lookup = "java:global/test/EiaMaintenancePlanificationService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote")
	private EiaMaintenancePlanificationServiceRemote serviceRemote;

	@EJB(lookup = "java:global/test/EiaMaintenancePlanificationService!"
			+ "org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceLocal")
	private EiaMaintenancePlanificationServiceLocal serviceLocal;

	@EJB(lookup = "java:global/test/MaintenancePlanService")
	private MaintenancePlanServiceRemote maintenancePlanService;

	@EJB(lookup = "java:global/test/EiaTypeMaintenancePlanService")
	private EiaTypeMaintenancePlanServiceRemote eiaTypeMPlanService;

	private MaintenancePlan maintenancePlan;
	private EiaTypeMaintenancePlan eiaTypeMPlan;
	private EiaType eiaType;
	private EiaHelper eiaHelper;
	private Eia eia;

	private void deleteTest(final EiaMaintenancePlanification planif) {
		final int itemsExpected = 0;
		try {
			serviceRemote.delete(planif.getId());

			List<EiaMaintenancePlanification> aux = serviceRemote.find(eiaType);

			Assert.assertEquals(itemsExpected, aux.size());

		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private void findByEiaTypeTest() {
		int itemsExpected = 1;
		try {
			final List<EiaMaintenancePlanification> result = serviceRemote
					.find(eiaType);

			Assert.assertEquals(itemsExpected, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAllTest() {
		int itemsExpected = 1;
		try {
			final List<EiaMaintenancePlanification> result = serviceLocal
					.getAll();

			Assert.assertEquals(itemsExpected, result.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getPlanificationsCountTest() {
		final long countExpected = 1;
		try {
			final long result = serviceLocal
					.getPlanificationsCount(maintenancePlan);
			System.out.println("3");

			Assert.assertEquals(countExpected, result);
			System.out.println("4");
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	private EiaMaintenancePlanification saveTest() {
		try {
			Calendar calendar = Calendar.getInstance();
			EiaMaintenancePlanification planif = new EiaMaintenancePlanification();
			planif.setEia(eia);
			planif.setPlan(eiaTypeMPlan);
			planif.setBeginningDate(new Date(calendar.getTimeInMillis()));
			EiaMaintenancePlanification result = serviceRemote.save(planif);

			Assert.assertNotNull(result);
			return result;
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** */
	@Before
	public void set() {
		System.out.println("\n SET - MAINTENANCE PLANIFICATION TEST \n");

		try {
			eiaHelper = new EiaHelper(ccdiServiceLocal,
					manufacturerServiceRemote, eiaTypeCategoryServiceRemote,
					brandServiceRemote, eiaTypeServiceRemote,
					roleServiceRemote, legalEntityServiceRemote,
					bpiServiceRemote, institutionServiceRemote,
					obuServiceRemote, bspServiceRemote,
					externalProviderServiceRemote, facilityServiceRemote,
					buildingLocationServiceRemote, eiaServiceRemote);

			eia = eiaHelper.createEia();
			eiaType = eia.getEiaType();

			// asociando planes a tipos de equipo
			maintenancePlan = new MaintenancePlan();
			maintenancePlan.setName("mantenimiento prueba");
			maintenancePlan.setFrequency(3);
			maintenancePlan.setPot(TimePeriodEnum.DAYS);
			maintenancePlan.setType(MaintenancePlanType.PREVENTIVE);
			maintenancePlan.setState(MaintenancePlanState.ACTIVE);
			maintenancePlan
					.setCancelationOption(MaintenancePlanCancelationOption.DEFERRABLE);
			maintenancePlan = maintenancePlanService.save(maintenancePlan);

			eiaTypeMPlan = new EiaTypeMaintenancePlan();
			eiaTypeMPlan.setEiaType(eiaType);
			eiaTypeMPlan.setMaintenancePlan(maintenancePlan);
			eiaTypeMPlan = eiaTypeMPlanService.save(eiaTypeMPlan);

		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}

	/**  */
	@Test
	public void test() {
		EiaMaintenancePlanification planif = null;
		final String sep = "\n---------------------------------------\n";

		System.out.println("TESTING EIA MAINTENANCE PLANIFICATION SERVICE\n");

		System.out.println(sep + "saveTest" + sep);
		planif = saveTest();

		System.out.println(sep + "getAllTest" + sep);
		getAllTest();

		System.out.println(sep + "findByEiaTypeTest" + sep);
		findByEiaTypeTest();

		System.out.println(sep + "getPlanificationsCountTest" + sep);
		getPlanificationsCountTest();

		System.out.println(sep + "deleteTest" + sep);
		deleteTest(planif);
	}

	/** */
	@After
	public void unset() {
		System.out.println("\n UNSET - MAINTENANCE PLANIFICATION TEST \n");

		try {
			eiaTypeMPlanService.delete(eiaTypeMPlan.getId());
		} catch (final GHAEJBException e) {
			logger.info("error deleting eieTypeMPlan" + e.getMessage());
		}
		try {
			maintenancePlanService.delete(maintenancePlan.getId());
		} catch (Exception e) {
			logger.info("error deleting maintenance plan" + e.getMessage());
		}
		try {
			eiaHelper.removeEia();
		} catch (Exception e) {
			logger.info("error deleting eia" + e.getMessage());
		}

	}

}
