package org.fourgeeks.gha.ejb.gmh;

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
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;

/**
 * @author vivi.torresg
 * 
 */
// @RunWith(Arquillian.class)
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
				// .addClass(MaterialTypeEnum.class)
				.addClass(ActivitySubCategoryEnum.class)
				// .addClass(Material.class)
				// .addClass(MaterialCategory.class)
				.addClass(MaintenanceActivity.class)
				.addClass(MaintenanceActivityServiceResource.class)
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
				.addClass(ServiceResource.class)
				.addClass(ServiceResourceCategory.class)
				.addClass(TimePeriodEnum.class)
				.addClass(View.class)
				.addClass(WorkingArea.class)
				.addClass(WarrantySinceEnum.class)
				.addClass(GHAMessageType.class)
				.addClass(Activity.class)
				.addClass(Bsp.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	private ExternalProvider externalProvider;
	private long externalProviderId = 0l;
	private long institutionId = 0l;
	private long legalEntityId = 0l;
	private Role role;
	private final long roleId = 0l;
	private Obu obu;
	private EiaType eiaType;

	@EJB(lookup = "java:global/test/EiaService")
	EiaServiceRemote service;

	@EJB(lookup = "java:global/test/EiaTypeService")
	EiaTypeServiceRemote eiaTypeService;

	@EJB(lookup = "java:global/test/ObuService")
	ObuServiceRemote obuService;

	@EJB(lookup = "java:global/test/RoleService")
	RoleServiceRemote roleService;

	@EJB(lookup = "java:global/test/LegalEntityService")
	LegalEntityServiceRemote legalEntityService;

	@EJB(lookup = "java:global/test/InstitutionService")
	InstitutionServiceRemote institutionService;

	@EJB(lookup = "java:global/test/ExternalProviderService")
	ExternalProviderServiceRemote externalProviderService;

	/**
	 */
	@Before
	public void set() {
		// CREATING AN EIATYPE
		String eiaTypeCode = "eiatype-code";
		EiaType localEiaType = new EiaType();
		localEiaType.setCode(eiaTypeCode);
		localEiaType.setMobility(EiaMobilityEnum.FIXED);
		localEiaType.setName("test eiatype");
		localEiaType.setType(EiaTypeEnum.EQUIPMENT);
		try {
			eiaType = eiaTypeService.save(localEiaType);
			System.out.println("AAAA\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println(eiaType.getCode());
			eiaType = eiaTypeService.find(eiaTypeCode);
			System.out.println("BBB\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println(eiaType.getCode());
		} catch (GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the eiatype");
		}

		// CREATING AN OBU
		String obuCode = "obu-code";
		Obu localObu = new Obu();
		localObu.setCode(obuCode);
		localObu.setName("Obu test name");

		try {
			obu = obuService.save(localObu);
		} catch (GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the obu");
		}
		//
		// CREATING ROLE
		Role localRole = new Role();
		localRole.setName("Role test name");

		try {
			role = roleService.save(localRole);
		} catch (GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the role");
		}

		// CREATING LEGAL ENTITY
		LegalEntity localLegalEntity = null;
		try {
			localLegalEntity = legalEntityService.save(new LegalEntity());
			legalEntityId = localLegalEntity.getId();
		} catch (GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the legalentity");
		}

		// CREATING THE INSTITUTIoN
		Institution localInstitution = new Institution();
		localInstitution.setName("Institution name test");
		localInstitution.setLegalEntity(localLegalEntity);
		try {
			localInstitution = institutionService.save(localInstitution);
			institutionId = localInstitution.getId();
		} catch (GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the intitution");
		}
		//
		// CREATING THE EXTERNAL PROVIDER
		ExternalProvider localExternalProvider = new ExternalProvider();
		localExternalProvider.setInstitution(localInstitution);
		try {
			externalProvider = externalProviderService
					.save(localExternalProvider);
			externalProviderId = externalProvider.getId();
		} catch (GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the intitution");
		}
	}

	/**
	 */
	@After
	public void unset() {
		// DELETING THE EIATYPE
		try {
			eiaTypeService.delete(eiaType.getCode());
		} catch (GHAEJBException e1) {
			// e1.printStackTrace();
		}

		// DELETING THE OBU
		try {
			obuService.delete(obu.getId());
		} catch (GHAEJBException e1) {
			// e1.printStackTrace();
		}

		// DELETING THE ROLE
		try {
			roleService.delete(role.getId());
		} catch (GHAEJBException e1) {
			// e1.printStackTrace();
		}
		// DELETING THE EXTERNAL PROVIDER
		try {
			externalProviderService.delete(externalProviderId);
		} catch (GHAEJBException e1) {
			e1.printStackTrace();
		}

		// DELETING THE InstiTUTION
		try {
			institutionService.delete(institutionId);
		} catch (GHAEJBException e1) {
			// e1.printStackTrace();
		}

		// DELETING THE LEGALENTITY
		try {
			legalEntityService.delete(legalEntityId);
		} catch (GHAEJBException e1) {
			// e1.printStackTrace();
		}

	}

	/**
	 */
	// @Test
	public void test() {

		System.out.println("CCCC\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println(eiaType.getCode());

		Eia eia = new Eia();
		eia.setEiaType(eiaType);
		eia.setProvider(externalProvider);
		eia.setMaintenanceProvider(externalProvider);
		eia.setResponsibleRole(role);
		eia.setObu(obu);
		eia.setSerialNumber("eia-serial");
		eia.setFixedAssetIdentifier("eia-fai");
		try {
			eia = service.save(eia);
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(eia);
		try {
			Assert.assertEquals(4, service.getAll().size());
		} catch (GHAEJBException e1) {
			e1.printStackTrace();
		}
		try {
			service.delete(eia.getId());
		} catch (GHAEJBException e) {
			e.printStackTrace();
		}
	}
}
