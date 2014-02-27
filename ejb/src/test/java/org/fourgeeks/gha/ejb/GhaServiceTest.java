package org.fourgeeks.gha.ejb;

import java.sql.Date;

import javax.persistence.EntityManager;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;

/**
 * @author alacret, vivi.torresg
 * 
 */
@Deprecated
public class GhaServiceTest {

	// private static Citizen citizen;
	// private static LegalEntity legalEntity, legalEntity2;
	// private static Institution institution;
	// private static Bpi bpi;

	public static Bpu createBpu() {
		return null;
	}

	public static void deleteBpu() {
	}

	private Bpa originalBpa = null;
	private Bpi originalbpi = null;
	private Bpu originalbpu = null;
	private BuildingLocation buildingLocation = null;
	private Citizen originalcitizen = null;
	private Date date = null;
	private Eia eia = null;
	private EiaType eiaType = null;
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan = null;
	private ExternalProvider externalProvider = null;
	private Institution originalinstitution = null;
	private LegalEntity originallegalEntity = null;
	private MaintenanceActivity maintenanceActivity = null;
	private RequiredResources maintenanceActivityServiceResource = null;
	private MaintenancePlan maintenancePlan = null;
	private Manufacturer manufacturer;
	private Obu obu = null;
	private Role role = null;
	private Bsp bsp = null;;

	private final ServiceAndResource serviceResource = null;

	@Deprecated
	public Bpa getBpa(EntityManager em) {
		if (originalBpa == null) {
			final Bpa bpa = new Bpa();
			em.persist(bpa);
			em.flush();
			this.originalBpa = em.find(Bpa.class, bpa.getId());
		}
		return originalBpa;
	}

	@Deprecated
	public Bpi getBpi(EntityManager em) {
		if (originalbpi == null) {
			final Bpi bpi = new Bpi();
			bpi.setInstitution(getInstitution(em));
			em.persist(bpi);
			em.flush();
			this.originalbpi = em.find(Bpi.class, bpi.getId());
		}
		return originalbpi;
	}

	@Deprecated
	public Bpu getBpu(EntityManager em) {
		if (originalbpu == null) {
			final Bpu bpu = new Bpu();
			bpu.setBpi(getBpi(em));
			bpu.setCitizen(getCitizen(em));
			em.persist(bpu);
			em.flush();
			this.originalbpu = em.find(Bpu.class, bpu.getId());
		}
		return originalbpu;
	}

	@Deprecated
	public BuildingLocation getBuildingLocation(EntityManager em) {
		if (buildingLocation == null) {
			final BuildingLocation buildingLocation = new BuildingLocation();
			buildingLocation.setCode("BuildingLocation test code");
			buildingLocation.setLocationLevel(LocationLevelEnum.AREA_HALL);
			buildingLocation.setBpi(getBpi(em));
			em.persist(buildingLocation);
			em.flush();
			this.buildingLocation = em.find(BuildingLocation.class,
					buildingLocation.getCode());
		}
		return buildingLocation;
	}

	public Citizen getCitizen(EntityManager em) {
		if (originalcitizen == null) {
			final Citizen citizen = new Citizen();
			citizen.setLegalEntity(getLegalEntity(em));
			citizen.setGender(GenderTypeEnum.FEMALE);
			em.persist(citizen);
			em.flush();
			this.originalcitizen = em.find(Citizen.class, citizen.getId());
		}
		return originalcitizen;
	}

	public Date getDate() {
		if (date == null) {
			date = new Date(new java.util.Date().getTime());
		}
		return date;
	}

	public Eia getEia(EntityManager em) {
		if (eia == null) {
			final Eia eia = new Eia();
			eia.setEiaType(getEiaType(em));
			eia.setObu(getObu(em));
			eia.setProvider(getExternalProvider(em));
			eia.setResponsibleRole(getRole(em));
			eia.setSerialNumber("Eia test serialNumber");
			eia.setFixedAssetIdentifier("Eia test asset");
			eia.setMaintenanceProvider(getBsp(em));
			em.persist(eia);
			em.flush();
			this.eia = em.find(Eia.class, eia.getId());
		}
		return eia;
	}

	public EiaType getEiaType(EntityManager em) {
		if (eiaType == null) {
			final EiaType eiaType = new EiaType();
			eiaType.setCode("EiaType test code");
			eiaType.setMobility(EiaMobilityEnum.FIXED);
			eiaType.setName("EiaType test name");
			eiaType.setType(EiaTypeEnum.EQUIPMENT);
			eiaType.setSubtype(EiaSubTypeEnum.DIAGNOSE);
			em.persist(eiaType);
			em.flush();
			this.eiaType = em.find(EiaType.class, eiaType.getCode());
		}
		return eiaType;
	}

	public EiaTypeMaintenancePlan getEiaTypeMaintenancePlan(EntityManager em,
			EiaType eiaType, MaintenancePlan maintenancePlan) {
		if (eiaTypeMaintenancePlan == null) {
			final EiaTypeMaintenancePlan eiaTypeMaintenancePlan = new EiaTypeMaintenancePlan();
			eiaTypeMaintenancePlan.setEiaType(eiaType);
			eiaTypeMaintenancePlan.setMaintenancePlan(maintenancePlan);
			em.persist(eiaTypeMaintenancePlan);
			em.flush();
			this.eiaTypeMaintenancePlan = em.find(EiaTypeMaintenancePlan.class,
					eiaTypeMaintenancePlan.getId());
		}
		return eiaTypeMaintenancePlan;
	}

	public ExternalProvider getExternalProvider(EntityManager em) {
		if (externalProvider == null) {
			final ExternalProvider externalProvider = new ExternalProvider();
			externalProvider.setInstitution(getInstitution(em));
			em.persist(externalProvider);
			em.flush();
			this.externalProvider = em.find(ExternalProvider.class,
					externalProvider.getId());
		}
		return externalProvider;
	}

	public Bsp getBsp(EntityManager em) {
		if (bsp == null) {
			final Bsp externalProvider = new Bsp();
			externalProvider.setObu(getObu(em));
			em.persist(externalProvider);
			em.flush();
			this.bsp = em.find(Bsp.class, bsp.getId());
		}
		return bsp;
	}

	public Institution getInstitution(EntityManager em) {
		if (originalinstitution == null) {
			final Institution institution = new Institution();
			institution.setName("Institution name test");
			institution.setLegalEntity(getLegalEntity(em));
			em.persist(institution);
			em.flush();
			this.originalinstitution = em.find(Institution.class,
					institution.getId());
		}
		return originalinstitution;
	}

	public LegalEntity getLegalEntity(EntityManager em) {
		if (originallegalEntity == null) {
			final LegalEntity legalEntity = new LegalEntity();
			em.persist(legalEntity);
			em.flush();
			this.originallegalEntity = em.find(LegalEntity.class,
					legalEntity.getId());
		}
		return originallegalEntity;
	}

	public MaintenanceActivity getMaintenanceActivity(EntityManager em) {
		if (maintenanceActivity == null) {
			final MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
			final Activity activity = new Activity();
			activity.setName("MaintenanceActivity test name");
			activity.setDescription("MaintenanceActivity test description");

			maintenanceActivity.setActivity(activity);
			em.persist(maintenanceActivity);
			em.flush();
			this.maintenanceActivity = em.find(MaintenanceActivity.class,
					maintenanceActivity.getId());
		}
		return maintenanceActivity;
	}

	public RequiredResources getMaintenanceActivityServiceResource(
			EntityManager em, MaintenanceActivity maintenanceActivity,
			ServiceAndResource serviceResource) {
		if (maintenanceActivityServiceResource == null) {
			final RequiredResources maintenanceActivityServiceResource = new RequiredResources();
			maintenanceActivityServiceResource.setActivity(maintenanceActivity
					.getActivity());
			maintenanceActivityServiceResource.setResource(serviceResource);
			em.persist(maintenanceActivityServiceResource);
			em.flush();
			this.maintenanceActivityServiceResource = em.find(
					RequiredResources.class,
					maintenanceActivityServiceResource.getId());
		}
		return maintenanceActivityServiceResource;
	}

	public MaintenancePlan getMaintenancePlan(EntityManager em) {
		if (maintenancePlan == null) {
			final MaintenancePlan maintenancePlan = new MaintenancePlan();
			maintenancePlan.setDescription("MaintenancePlan test description");
			maintenancePlan.setName("MaintenancePlan teste name");
			maintenancePlan.setPot(TimePeriodEnum.DAYS);
			em.persist(maintenancePlan);
			em.flush();
			this.maintenancePlan = em.find(MaintenancePlan.class,
					maintenancePlan.getId());
		}
		return maintenancePlan;
	}

	public Manufacturer getManufacturer(EntityManager em) {
		if (manufacturer == null) {
			final Manufacturer manufacturer = new Manufacturer();
			manufacturer.setName("Manufacturer test name");
			em.persist(manufacturer);
			em.flush();
			this.manufacturer = em.find(Manufacturer.class,
					manufacturer.getId());
		}
		return manufacturer;
	}

	public Obu getObu(EntityManager em) {
		if (obu == null) {
			final Obu obu = new Obu();
			obu.setCode("Obu test code");
			obu.setName("Obu test name");
			em.persist(obu);
			em.flush();
			this.obu = em.find(Obu.class, obu.getId());
		}
		return obu;
	}

	public Role getRole(EntityManager em) {
		if (role == null) {
			final Role role = new Role();
			role.setName("Role test name");
			em.persist(role);
			em.flush();
			this.role = em.find(Role.class, role.getId());
		}
		return role;
	}

	public ServiceAndResource getServiceResource(EntityManager em) {
		// if (serviceResource == null) {
		// ServiceAndResource serviceResource = new ServiceAndResource();
		// serviceResource.setCode("ServiceResource test name");
		// em.persist(serviceResource);
		// em.flush();
		// this.serviceResource = em.find(ServiceAndResource.class,
		// serviceResource.getCode());
		// }
		// return serviceResource;
		return null;
	}
}
