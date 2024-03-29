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
import org.fourgeeks.gha.domain.enu.ServiceAndResourceType;
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
import org.fourgeeks.gha.domain.gmh.GlaLog;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
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
import org.fourgeeks.gha.ejb.ess.auth.RoleService;
import org.fourgeeks.gha.ejb.ess.auth.RoleServiceRemote;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserService;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.gar.BspService;
import org.fourgeeks.gha.ejb.gar.BspServiceRemote;
import org.fourgeeks.gha.ejb.gar.ObuService;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.ejb.glm.ExternalProviderService;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
import org.fourgeeks.gha.ejb.log.UILogService;
import org.fourgeeks.gha.ejb.log.UILogServiceLocal;
import org.fourgeeks.gha.ejb.log.UILogServiceRemote;
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
 * @author naramirez
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
				.addClass(App.class)
				.addClass(ViewFunction.class)
				// .addClass(AppFormViewFunctionService.class)
				// .addClass(AppFormViewFunctionServiceRemote.class)
				.addClass(FunctionBpu.class)
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
				.addClass(GlaLog.class)
				.addClass(EiaMaintenancePlanification.class)
				.addClass(EiaDamageStatusEnum.class)
				.addClass(EiaMobilityEnum.class)
				.addClass(EiaTypeEnum.class)
				.addClass(EiaSubTypeEnum.class)
				.addClass(ServiceResourceCategory.class)
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
				.addClass(EiaPreventiveMaintenance.class)
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
				.addClass(EiaMaintenanceState.class)
				.addClass(MaintenanceProtocol.class)
				.addClass(ActivityState.class)
				.addClass(ActivityCategoryEnum.class)
				.addClass(Manufacturer.class)
				// .addClass(MaterialTypeEnum.class)
				.addClass(ActivitySubCategoryEnum.class)
				// .addClass(Material.class)
				// .addClass(ServicesResourceCategory.class)
				.addClass(MaintenanceActivity.class)
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
				.addClass(ServiceAndResourceType.class)
				.addClass(UILog.class)
				.addClass(UILogService.class)
				.addClass(UILogServiceLocal.class)
				.addClass(UILogServiceRemote.class)
				.addClass(SSOUser.class)
				.addClass(SSOUserService.class)
				.addClass(SSOUserServiceRemote.class)
				.addClass(GHALog.class)
				.addClass(EiaCorrectiveMaintenance.class)
				.addClass(EiaMaintenance.class)
				.addClass(UserLogonStatusEnum.class)
				.addClass(MaintenanceCancelationCause.class)
				.addClass(MaintenancePlanificationState.class)
				.addClass(BspService.class)
				.addClass(BspServiceRemote.class)
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	private ExternalProvider externalProvider;
	private long externalProviderId = 0l;
	private long institutionId = 0l;
	private long legalEntityId = 0l;
	private long bspId = 0l;
	private Role role;
	private Obu obu;
	private Bsp bsp;
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

	@EJB(lookup = "java:global/test/BspService")
	BspServiceRemote bspService;

	/**
	 */
	@Before
	public void set() {
		// CREATING AN EIATYPE
		final String eiaTypeCode = "eiatype-code";
		final EiaType localEiaType = new EiaType();
		localEiaType.setCode(eiaTypeCode);
		localEiaType.setMobility(EiaMobilityEnum.FIXED);
		localEiaType.setName("test eiatype");
		localEiaType.setType(EiaTypeEnum.EQUIPMENT);
		try {
			eiaType = eiaTypeService.save(localEiaType);
			eiaType = eiaTypeService.find(eiaTypeCode);
		} catch (final GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the eiatype");
		}

		// CREATING AN OBU
		final String obuCode = "obu-code";
		final Obu localObu = new Obu();
		localObu.setCode(obuCode);
		localObu.setName("Obu test name");

		try {
			obu = obuService.save(localObu);
		} catch (final GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the obu");
		}
		//
		// CREATING ROLE
		final Role localRole = new Role();
		localRole.setName("Role test name");

		try {
			role = roleService.save(localRole);
		} catch (final GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the role");
		}

		// CREATING LEGAL ENTITY
		LegalEntity localLegalEntity = null;
		try {
			localLegalEntity = legalEntityService.save(new LegalEntity());
			legalEntityId = localLegalEntity.getId();
		} catch (final GHAEJBException e) {
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
		} catch (final GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the intitution");
		}
		//
		// CREATING THE EXTERNAL PROVIDER
		final ExternalProvider localExternalProvider = new ExternalProvider();
		localExternalProvider.setInstitution(localInstitution);
		try {
			externalProvider = externalProviderService
					.save(localExternalProvider);
			externalProviderId = externalProvider.getId();
		} catch (final GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the intitution");
		}

		// CREATING THE BSP
		final Bsp localBsp = new Bsp();
		localBsp.setObu(localObu);
		try {
			bsp = bspService.save(localBsp);
			bspId = externalProvider.getId();
		} catch (final GHAEJBException e) {
			e.printStackTrace();
			unset();
			Assert.fail("failing creating the intitution");
		}
	}

	/**
	 */
	// @Test
	public void test() {
		Eia eia = new Eia();
		eia.setEiaType(eiaType);
		eia.setProvider(externalProvider);
		eia.setMaintenanceProvider(bsp);
		eia.setResponsibleRole(role);
		eia.setObu(obu);
		eia.setSerialNumber("eia-serial");
		eia.setFixedAssetIdentifier("eia-fai");
		try {
			eia = service.save(eia);
		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(eia);
		try {
			Assert.assertEquals(1, service.getAll().size());
		} catch (final GHAEJBException e1) {
			e1.printStackTrace();
		}
		try {
			service.delete(eia.getId());
		} catch (final GHAEJBException e) {
			e.printStackTrace();
		}
	}

	/**
	 */
	@After
	public void unset() {
		// DELETING THE EIATYPE
		try {
			eiaTypeService.delete(eiaType.getCode());
		} catch (final GHAEJBException e1) {
			// e1.printStackTrace();
		}

		// DELETING THE OBU
		try {
			obuService.delete(obu.getId());
		} catch (final GHAEJBException e1) {
			// e1.printStackTrace();
		}

		// DELETING THE ROLE
		try {
			roleService.delete(role.getId());
		} catch (final GHAEJBException e1) {
			// e1.printStackTrace();
		}
		// DELETING THE EXTERNAL PROVIDER
		try {
			externalProviderService.delete(externalProviderId);
		} catch (final GHAEJBException e1) {
			e1.printStackTrace();
		}

		// DELETING THE EXTERNAL PROVIDER
		try {
			bspService.delete(bspId);
		} catch (final GHAEJBException e1) {
			e1.printStackTrace();
		}

		// DELETING THE InstiTUTION
		try {
			institutionService.delete(institutionId);
		} catch (final GHAEJBException e1) {
			// e1.printStackTrace();
		}

		// DELETING THE LEGALENTITY
		try {
			legalEntityService.delete(legalEntityId);
		} catch (final GHAEJBException e1) {
			// e1.printStackTrace();
		}

	}
}
