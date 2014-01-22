package org.fourgeeks.gha.ejb.gmh;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.HasKey;
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
import org.fourgeeks.gha.domain.enu.MaintenanceActivityState;
import org.fourgeeks.gha.domain.enu.MaintenanceActivitySubTypeEnum;
import org.fourgeeks.gha.domain.enu.MaintenanceActivityTypeEnum;
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
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityServiceResource;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author vivi.torresg
 * 
 */
@RunWith(Arquillian.class)
public class EiaServiceTest {
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
				// .addClass(AppFormViewFunctionService.class)
				// .addClass(AppFormViewFunctionServiceRemote.class)
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
				// .addClass(EiaPictureStateEnum.class)
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
				.addClass(EiaDamagePriorityEnum.class)
				.addClass(EiaService.class)
				.addClass(ExternalProvider.class)
				.addClass(EiaServiceTest.class)
				.addClass(EiaServiceRemote.class)
				.addClass(EiaPreventiveMaintenancePlanification.class)
				.addClass(ExternalProvider.class)
				.addClass(Facility.class)
				.addClass(FacilityCategory.class)
				.addClass(Function.class)
				.addClass(GenderTypeEnum.class)
				.addClass(GHAEJBException.class)
				// .addClass(GHAMessage.class)
				//
				.addClass(GHAEJBExceptionService.class)
				.addClass(HasKey.class)
				.addClass(Institution.class)
				.addClass(Job.class)
				.addClass(JobCategory.class)
				.addClass(JobPosition.class)
				.addClass(LegalEntity.class)
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
				.addClass(MaintenanceActivityState.class)
				.addClass(MaintenanceActivityTypeEnum.class)
				.addClass(Manufacturer.class)
				// .addClass(MaterialTypeEnum.class)
				.addClass(MaintenanceActivitySubTypeEnum.class)
				// .addClass(Material.class)
				// .addClass(MaterialCategory.class)
				.addClass(MaintenanceActivity.class)
				.addClass(MaintenanceActivityServiceResource.class)
				.addClass(Module.class).addClass(Obu.class)
				.addClass(ProviderResourceTypeEnum.class)
				.addClass(ProviderServicesEnum.class)
				.addClass(ProviderPreferenceEnum.class)
				.addClass(ProviderQualEnum.class)
				.addClass(ProviderTypeEnum.class)
				.addClass(ProviderRepresentEnum.class).addClass(Role.class)
				.addClass(ServiceResource.class)
				.addClass(ServiceResourceCategory.class)
				.addClass(TimePeriodEnum.class)

				.addClass(View.class)

				// .addClass(GHAMessageId.class)
				// .addClass(CSVReader.class)
				// .addClass(CSVParser.class)

				// .addClass(Parameter.class)
				// .addClass(CSVParser.class)
				//
				//

				//

				//
				.addClass(WorkingArea.class).addClass(WarrantySinceEnum.class)
				//
				// .addAsResource("codes.csv")
				// .addAsResource("messages.csv")
				// .addAsResource("uistrings.csv")
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml");
	}

	// private ExternalProvider externalProvider;
	//
	// private Role role;
	//
	// private Obu obu;
	//
	// private EiaType eiaType;

	/**
	 * @param em
	 * @throws SystemException
	 * @throws NotSupportedException
	 */
	@Before
	public void set() throws NotSupportedException, SystemException {
		//
		// EiaType eiaType = new EiaType();
		// eiaType.setCode("EiaType test code");
		// eiaType.setMobility(EiaMobilityEnum.FIXED);
		// eiaType.setName("EiaType test name");
		// eiaType.setType(EiaTypeEnum.EQUIPMENT);
		// eiaType.setSubtype(EiaSubTypeEnum.DIAGNOSE);
		// em.persist(eiaType);
		// em.flush();
		// this.eiaType = em.find(EiaType.class, eiaType.getCode());
		// Obu obu = new Obu();
		// obu.setCode("Obu test code");
		// obu.setName("Obu test name");
		// em.persist(obu);
		// em.flush();
		// this.obu = em.find(Obu.class, obu.getId());
		// LegalEntity legalEntity = new LegalEntity();
		// em.persist(legalEntity);
		// em.flush();
		// legalEntity = em.find(LegalEntity.class, legalEntity.getId());
		// Institution institution = new Institution();
		// institution.setName("Institution name test");
		// institution.setLegalEntity(legalEntity);
		// em.persist(institution);
		// em.flush();
		// institution = em.find(Institution.class, institution.getId());
		// ExternalProvider externalProvider = new ExternalProvider();
		// externalProvider.setInstitution(institution);
		// em.persist(externalProvider);
		// em.flush();
		// this.externalProvider = em.find(ExternalProvider.class,
		// externalProvider.getId());
		// Role role = new Role();
		// role.setName("Role test name");
		// em.persist(role);
		// em.flush();
		// this.role = em.find(Role.class, role.getId());

	}

	@PersistenceContext(unitName = "test")
	EntityManager em;

	// @EJB(name = "EiaService"/*
	// * , lookup = "java:global/ear-1/ejb-1/EiaService"
	// */)
	// EiaServiceRemote service;

	@Inject
	UserTransaction ux;

	/**
	 * @throws NotSupportedException
	 * @throws SystemException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws RollbackException
	 * @throws HeuristicMixedException
	 * @throws HeuristicRollbackException
	 * @throws GHAEJBException
	 */
	@Test
	public void test() throws NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException,
			GHAEJBException {

		System.out
				.println("TEST \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		ux.begin();
		// em.joinTransaction();

		// Assert.assertNotNull(em);
		// Assert.assertNotNull(em);
		//
		// Eia entity = new Eia();
		// entity.setCode("Eia test code");
		// entity.setEiaType(eiaType);
		// entity.setObu(obu);
		// entity.setProvider(externalProvider);
		// entity.setResponsibleRole(role);
		// entity.setMaintenanceProvider(externalProvider);
		// entity.setSerialNumber("Eia test serialNumber");
		// entity.setFixedAssetIdentifier("Eia test asset");
		//
		// entity = service.save(entity);
		//
		// Assert.assertNotNull(service.find(entity.getId()));
		// System.out.println("BEFORE " + entity.getId() + " " +
		// entity.getCode()
		// + "\nAFTER " + service.find(entity.getId()).getId() + " "
		// + service.find(entity.getId()).getCode());
		// // Assert.assertEquals(entity, service.find(entity.getId()));
		// Assert.assertTrue(service.find(entity) != null
		// && service.find(entity).size() >= 1);
		// Assert.assertTrue(service.findByEiaType(entity.getEiaType()) != null
		// && service.findByEiaType(entity.getEiaType()).size() >= 1);
		// Assert.assertTrue(service.getAll() != null
		// && service.getAll().size() >= 1);
		// Assert.assertTrue(service.getAll(0, 10) != null
		// && service.getAll(0, 10).size() >= 1);
		// entity.setCode("Eia test code updated");
		// entity = service.update(entity);
		// Assert.assertEquals("Eia test code updated",
		// service.find(entity.getId()).getCode());
		// Assert.assertFalse("Eia test code" == service.find(entity.getId())
		// .getCode());
		// long id = entity.getId();
		// service.delete(entity.getId());
		// Assert.assertNull(service.find(id));
		//
		// ux.commit();

	}
}
