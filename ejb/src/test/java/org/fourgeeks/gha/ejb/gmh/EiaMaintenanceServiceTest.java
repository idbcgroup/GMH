package org.fourgeeks.gha.ejb.gmh;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.ess.auth.RoleServiceRemote;
import org.fourgeeks.gha.ejb.gar.BpuServiceRemote;
import org.fourgeeks.gha.ejb.gar.BspServiceRemote;
import org.fourgeeks.gha.ejb.gar.FacilityServiceRemote;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.fourgeeks.gha.ejb.helpers.BpuHelper;
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
public class EiaMaintenanceServiceTest extends GHAArquillianBaseServiceTest {
	private final static Logger logger = Logger
			.getLogger(EiaMaintenanceServiceTest.class.getName());

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

	@EJB(lookup = "java:global/test/CitizenService")
	private CitizenServiceRemote citizenServiceRemote;

	@EJB(lookup = "java:global/test/EiaMaintenanceService")
	private EiaMaintenanceServiceRemote service;

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

	private EiaDamageReport savedEiaDamageReport;
	private EiaMaintenancePlanification savedEiaMPlani;
	private EiaTypeMaintenancePlan savedEiaTypeMPlan;
	private MaintenancePlan savedMaintenancePlan;
	private BpuHelper bpuHelper;
	private Eia savedEia;
	private ExternalProvider savedExternalProvider;
	private Facility savedFacility;
	private BuildingLocation savedBuildingLocation;
	private Bsp savedBsp;
	private Obu savedObu;
	private Bpi savedBpi;
	private Institution savedInstitution;
	private LegalEntity savedLegalEntity;
	private Role savedRole;
	private EiaType savedEiatype;
	private EiaTypeCategory savedEiatypeCategory;
	private Brand savedBrand;
	private Manufacturer savedManufacturer;
	private CCDIDefinition savedCCDIDefinition;

	/** */
	@Before
	public void set() {
		try {
			// creating a ccdi definition
			CCDIDefinition definition = new CCDIDefinition("CCDIDEFTEST");
			definition.setLevels(1);
			definition.setLength(10);
			savedCCDIDefinition = ccdiServiceLocal
					.createCCDIDefinition(definition);

			// creating a ccdi level denition
			CCDILevelDefinition ccdiLevelDefinition = new CCDILevelDefinition();
			ccdiLevelDefinition.setDefinition(savedCCDIDefinition);
			ccdiLevelDefinition.setLevel(0);
			ccdiLevelDefinition.setLength(2);
			ccdiLevelDefinition.setValueType(CCDIValueTypeEnum.FIXED);
			CCDILevelDefinition savedCCDILevelDefinition = ccdiServiceLocal
					.createCCDILevelDefinition(savedCCDIDefinition,
							ccdiLevelDefinition);

			// creating a ccdi level value
			CCDILevelValue ccdiLevelValue = new CCDILevelValue(
					savedCCDILevelDefinition, null, "TESTLEVELVALUE",
					"TESTLEVELVALUE", 1, "XX", CCDIValueStatusEnum.ACTIVE);
			ccdiLevelValue.setNextElement(1);
			CCDILevelValue savedCCDILevelValue = ccdiServiceLocal
					.createCCDILevelValue(savedCCDILevelDefinition, null,
							ccdiLevelValue);

			savedManufacturer = manufacturerServiceRemote
					.save(new Manufacturer("TESTMAN"));

			// Creating a Brand
			Brand b = new Brand("TESTBRAND");
			b.setManufacturer(savedManufacturer);
			savedBrand = brandServiceRemote.save(b);

			// Creating an Eiatye CAtegory
			final EiaTypeCategory category = new EiaTypeCategory();
			category.setName("TESTLEVELVALUE");
			category.setCode(savedCCDILevelValue.getCode());
			savedEiatypeCategory = eiaTypeCategoryServiceRemote.save(category);

			// CREating an eiatype
			final EiaType eiaType = new EiaType();
			eiaType.setBrand(savedBrand);
			eiaType.setName("TESTEIATYPE");
			eiaType.setMobility(EiaMobilityEnum.FIXED);
			eiaType.setEiaTypeCategory(savedEiatypeCategory);
			eiaType.setCode("EIATYPETESTCODE");
			eiaType.setSubtype(EiaSubTypeEnum.DIAGNOSE);
			eiaType.setModel("TESTMODEL");
			savedEiatype = eiaTypeServiceRemote.save(eiaType);

			savedRole = roleServiceRemote.save(new Role("Test Role 2"));

			savedLegalEntity = legalEntityServiceRemote.save(new LegalEntity(
					"J-0001"));

			// creating an Institution
			Institution institution = new Institution();
			institution.setName("TestInstitution");
			institution.setLegalEntity(savedLegalEntity);
			savedInstitution = institutionServiceRemote.save(institution);

			// Creating a BPI
			Bpi bpi = new Bpi();
			bpi.setInstitution(savedInstitution);
			savedBpi = bpiServiceRemote.save(bpi);

			// creating a OBU
			Obu obu = new Obu();
			obu.setName("TESTOVU");
			obu.setCode("TestOBU");
			obu.setBpi(savedBpi);
			savedObu = obuServiceRemote.save(obu);

			// creating a BSP
			final Bsp bsp = new Bsp();
			bsp.setObu(savedObu);
			savedBsp = bspServiceRemote.save(bsp);

			// creating a buildingLocation
			final BuildingLocation buildingLocation = new BuildingLocation(
					savedBpi, "Building 00", LocationLevelEnum.BUILDING,
					"Building Location Name ");
			savedBuildingLocation = buildingLocationServiceRemote
					.save(buildingLocation);

			// creating a Facility
			final Facility facility = new Facility();
			facility.setName("TESTFACILITY");
			facility.setBuildingLocation(savedBuildingLocation);
			savedFacility = facilityServiceRemote.save(facility);

			// creating an externalProvider
			ExternalProvider eP = new ExternalProvider();
			eP.setInstitution(savedInstitution);
			savedExternalProvider = externalProviderServiceRemote.save(eP);

			// CREating an Eia
			final Eia eia = new Eia(savedRole, savedEiatype, savedObu,
					EiaStateEnum.ACQUIRED, "GHAEQ-00", savedBsp, "S9023423");
			eia.setCode("eia-00");
			eia.setFacility(savedFacility);
			eia.setProvider(savedExternalProvider);

			savedEia = eiaServiceRemote.save(eia);

			MaintenancePlan maintenancePlan = new MaintenancePlan();
			maintenancePlan.setName("mantenimiento prueba");
			maintenancePlan.setFrequency(3);
			maintenancePlan.setPot(TimePeriodEnum.DAYS);
			maintenancePlan.setType(MaintenancePlanType.PREVENTIVE);
			maintenancePlan.setState(MaintenancePlanState.ACTIVE);
			maintenancePlan
					.setCancelationOption(MaintenancePlanCancelationOption.DEFERRABLE);
			savedMaintenancePlan = maintenancePlanService.save(maintenancePlan);

			EiaTypeMaintenancePlan eiaTypeMPlan = new EiaTypeMaintenancePlan();
			eiaTypeMPlan.setEiaType(savedEiatype);
			eiaTypeMPlan.setMaintenancePlan(savedMaintenancePlan);
			savedEiaTypeMPlan = eiaTypeMPlanService.save(eiaTypeMPlan);

			EiaMaintenancePlanification eiaMPlani = new EiaMaintenancePlanification();
			eiaMPlani.setBeginningDate(Date.valueOf("2014-12-12"));
			eiaMPlani.setEia(savedEia);
			eiaMPlani.setPlan(savedEiaTypeMPlan);
			savedEiaMPlani = planifServiceRemote.save(eiaMPlani);

			bpuHelper = new BpuHelper(legalEntityServiceRemote,
					citizenServiceRemote, institutionServiceRemote, bpuService,
					bpiServiceRemote);
			Bpu savedBpu = bpuHelper.createBpu();

			EiaDamageReport eiaDamageReport = new EiaDamageReport();
			eiaDamageReport.setEiaCondition(EiaStateEnum.CREATED);
			eiaDamageReport.setEia(savedEia);
			eiaDamageReport.setDamageStatus(EiaDamageStatusEnum.DAMAGE);
			eiaDamageReport.setPriority(EiaDamagePriorityEnum.NORMAL);
			eiaDamageReport.setUserWhoRegistered(savedBpu);
			eiaDamageReport.setUserWhoReported(savedBpu);
			savedEiaDamageReport = damageReportService.save(eiaDamageReport);

		} catch (GHAEJBException e) {
			unset();
			Assert.fail("error setting: " + e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	public void testCorrectiveMaintenance() {
		EiaCorrectiveMaintenance result = null;
		try {
			final EiaCorrectiveMaintenance entity = new EiaCorrectiveMaintenance();
			entity.setDamageReport(savedEiaDamageReport);

			result = service.saveCorrectiveMaintenance(entity);

			Assert.assertNotNull(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			result.setEffectiveTime(3);
			result.setEffectivePoT(TimePeriodEnum.MONTHS);

			final EiaCorrectiveMaintenance localResult = service
					.updateCorrectiveMaintenance(result);

			Assert.assertEquals(localResult.getEffectiveTime(), 3);
			Assert.assertEquals(localResult.getEffectivePoT(),
					TimePeriodEnum.MONTHS);

		} catch (Exception e) {
			unset();
			Assert.fail("error updating preventive maintenance");
		}

		try {
			service.deleteCorrectiveMaintenance(result.getId());
			Assert.assertTrue(true);
		} catch (Exception e) {
			unset();
			Assert.fail("error deleting corrective maintenance");
		}
	}

	/** */
	@Test
	public void testPreventiveMaintenance() {
		long time = Calendar.getInstance().getTimeInMillis();
		Timestamp finishTimestamp = new Timestamp(time);
		Date scheduledDate = new Date(time);

		EiaPreventiveMaintenance result = null;
		try {
			EiaPreventiveMaintenance prevEntity = new EiaPreventiveMaintenance();
			prevEntity.setPlanification(savedEiaMPlani);
			prevEntity.setState(EiaMaintenanceState.ACCOMPLISHED);
			prevEntity.setFinishTimestamp(finishTimestamp);
			prevEntity.setScheduledDate(scheduledDate);
			result = service.savePreventiveMaintenance(prevEntity);
			Assert.assertNotNull(result);

		} catch (Exception e) {
			unset();
			Assert.fail("error saving preventive maintenance");
		}

		try {
			result.setEffectiveTime(3);
			result.setEffectivePoT(TimePeriodEnum.MONTHS);

			final EiaPreventiveMaintenance localResult = service
					.updatePreventiveMaintenance(result);

			Assert.assertEquals(localResult.getEffectiveTime(), 3);
			Assert.assertEquals(localResult.getEffectivePoT(),
					TimePeriodEnum.MONTHS);

		} catch (Exception e) {
			unset();
			Assert.fail("error updating preventive maintenance");
		}

		try {
			Assert.assertEquals(1, service.find(savedEiatype).size());
		} catch (Exception e) {
			unset();
			Assert.fail("error finding preventive maintenance by eiatype");
		}

		try {
			service.deletePreventiveMaintenance(result.getId());
			Assert.assertTrue(true);
		} catch (Exception e) {
			unset();
			Assert.fail("error deleting preventive maintenance");
		}

		try {
			final long planis = planifServiceLocal
					.getEffectuatedPlanificationsCount(savedMaintenancePlan);
			Assert.assertEquals(0, planis);
		} catch (GHAEJBException e) {
			unset();
			Assert.fail("error getting planification count");
		}

		try {
			final Timestamp result1 = planifServiceLocal
					.getLastEffectuatedPlanificationDate(savedMaintenancePlan);
			Assert.assertNull(result1);
		} catch (GHAEJBException e) {
			unset();
			Assert.fail("error getting last planification effectuate date");
		}

		try {
			final Date result1 = planifServiceLocal
					.getScheduleDateOfLastMaintenance(savedEiaMPlani);
			Assert.assertNull(result1);
		} catch (GHAEJBException e) {
			unset();
			Assert.fail("error getting last planification date");
		}

	}

	/** */
	@After
	public void unset() {
		try {
			damageReportService.delete(savedEiaDamageReport.getId());
		} catch (Exception e) {
			logger.info("error deleting the damage report" + e.getMessage());
		}
		try {
			bpuHelper.removeBpu();
		} catch (Exception e) {
			logger.info("error deleting the bpu " + e.getMessage());
		}
		try {
			planifServiceRemote.delete(savedEiaMPlani.getId());
		} catch (Exception e) {
			logger.info("error deleting eia mPlaninif" + e.getMessage());
		}
		try {
			eiaTypeMPlanService.delete(savedEiaTypeMPlan.getId());
		} catch (Exception e) {
			logger.info("error deleting eiatype mplan " + e.getMessage());
		}
		try {
			maintenancePlanService.delete(savedMaintenancePlan.getId());
		} catch (Exception e) {
			logger.info("error deleting maintenance plan" + e.getMessage());
		}
		try {
			eiaServiceRemote.delete(savedEia.getId());
		} catch (Exception e) {
			logger.info("error deleting plan eia " + e.getMessage());
		}
		try {
			externalProviderServiceRemote.delete(savedExternalProvider.getId());
		} catch (Exception e) {
			logger.info("error deleting external provider " + e.getMessage());
		}
		try {
			facilityServiceRemote.delete(savedFacility.getId());
		} catch (Exception e) {
			logger.info("error deleting facility " + e.getMessage());
		}
		try {
			buildingLocationServiceRemote.delete(savedBuildingLocation
					.getCode());
		} catch (Exception e) {
			logger.info("error deleting building location" + e.getMessage());
		}
		try {
			bspServiceRemote.delete(savedBsp.getId());
		} catch (Exception e) {
			logger.info("error deleting bsp" + e.getMessage());
		}
		try {
			obuServiceRemote.delete(savedObu.getId());
		} catch (Exception e) {
			logger.info("error deleting obu " + e.getMessage());
		}
		try {
			bpiServiceRemote.delete(savedBpi.getId());
		} catch (Exception e) {
			logger.info("error deleting bpi " + e.getMessage());
		}
		try {
			institutionServiceRemote.delete(savedInstitution.getId());
		} catch (Exception e) {
			logger.info("error deleting institution " + e.getMessage());
		}
		try {
			legalEntityServiceRemote.delete(savedLegalEntity.getId());
		} catch (Exception e) {
			logger.info("error deleting legal entity " + e.getMessage());
		}
		try {
			roleServiceRemote.delete(savedRole.getId());
		} catch (Exception e) {
			logger.info("error deleting role " + e.getMessage());
		}
		try {
			eiaTypeServiceRemote.delete(savedEiatype.getCode());
		} catch (Exception e) {
			logger.info("error deleting eiatype " + e.getMessage());
		}
		try {
			eiaTypeCategoryServiceRemote.delete(savedEiatypeCategory);
		} catch (Exception e) {
			logger.info("error deleting eiatypecategory " + e.getMessage());
		}
		try {
			brandServiceRemote.delete(savedBrand.getId());
		} catch (Exception e) {
			logger.info("error deleting a brand " + e.getMessage());
		}
		try {
			manufacturerServiceRemote.delete(savedManufacturer.getId());
		} catch (Exception e) {
			logger.info("error deleting manufacturer " + e.getMessage());
		}
		try {
			ccdiServiceLocal.deleteByCode(savedCCDIDefinition.getCode());
		} catch (Exception e) {
			logger.info("error deleting ccdi definition" + e.getMessage());
		}
	}
}
