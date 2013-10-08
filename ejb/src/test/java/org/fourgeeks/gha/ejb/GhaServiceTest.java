package org.fourgeeks.gha.ejb;

import java.sql.Date;

import javax.ejb.EJBException;
import javax.persistence.EntityManager;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.codes.FunctionsCodes;
import org.fourgeeks.gha.domain.conf.Parameter;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityServiceResource;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.ejb.ess.FunctionServiceRemote;
import org.fourgeeks.gha.ejb.ess.InstanceLogonService;
import org.fourgeeks.gha.ejb.gar.BpuFunctionService;
import org.fourgeeks.gha.ejb.glm.ExternalProviderService;
import org.fourgeeks.gha.ejb.gmh.BrandService;
import org.fourgeeks.gha.ejb.language.LanguageService;
import org.fourgeeks.gha.ejb.log.LogonLogServiceRemote;
import org.fourgeeks.gha.ejb.mix.BpaService;
import org.fourgeeks.gha.ejb.msg.MessageService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * @author alacret, vivi.torresg
 * 
 */
public class GhaServiceTest {

	private Bpa bpa = null;
	private Bpi bpi = null;
	private Bpu bpu = null;
	private BuildingLocation buildingLocation = null;
	private Citizen citizen = null;
	private Date date = null;
	private Eia eia = null;
	private EiaType eiaType = null;
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan = null;
	private ExternalProvider externalProvider = null;
	private Institution institution = null;
	private LegalEntity legalEntity = null;
	private MaintenanceActivity maintenanceActivity = null;
	private MaintenanceActivityMaintenanceProtocol maintenanceActivityMaintenanceProtocol = null;
	private MaintenanceActivityServiceResource maintenanceActivityServiceResource = null;
	private MaintenancePlan maintenancePlan = null;
	private MaintenanceProtocol maintenanceProtocol = null;
	private Manufacturer manufacturer;
	private Obu obu = null;
	private Role role = null;
	private ServiceResource serviceResource = null;

	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(AbstractEntity.class.getPackage())
				.addPackage(Bpa.class.getPackage())
				.addPackage(BpaService.class.getPackage())
				.addPackage(Bpu.class.getPackage())
				.addPackage(BpuFunction.class.getPackage())
				.addPackage(BpuFunctionService.class.getPackage())
				.addPackage(Brand.class.getPackage())
				.addPackage(BrandService.class.getPackage())
				.addPackage(DocumentTypeEnum.class.getPackage())
				.addPackage(EJBException.class.getPackage())
				.addPackage(ExternalProvider.class.getPackage())
				.addPackage(ExternalProviderService.class.getPackage())
				.addPackage(Function.class.getPackage())
				.addPackage(FunctionsCodes.class.getPackage())
				.addPackage(FunctionServiceRemote.class.getPackage())
				.addPackage(GHAEJBException.class.getPackage())
				.addPackage(GhaServiceTest.class.getPackage())
				.addPackage(InstanceLogonService.class.getPackage())
				.addPackage(LanguageService.class.getPackage())
				.addPackage(LogonLog.class.getPackage())
				.addPackage(LogonLogServiceRemote.class.getPackage())
				.addPackage(GHAMessage.class.getPackage())
				.addPackage(MessageService.class.getPackage())
				.addPackage(Parameter.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("jbossas-ds.xml");
	}

	public Bpa getBpa(EntityManager em) {
		if (bpa == null) {
			Bpa bpa = new Bpa();
			em.persist(bpa);
			em.flush();
			this.bpa = em.find(Bpa.class, bpa.getId());
		}
		return bpa;
	}

	public Bpi getBpi(EntityManager em) {
		if (bpi == null) {
			Bpi bpi = new Bpi();
			bpi.setInstitution(getInstitution(em));
			em.persist(bpi);
			em.flush();
			this.bpi = em.find(Bpi.class, bpi.getId());
		}
		return bpi;
	}

	public Bpu getBpu(EntityManager em) {
		if (bpu == null) {
			Bpu bpu = new Bpu();
			bpu.setBpi(getBpi(em));
			bpu.setCitizen(getCitizen(em));
			em.persist(bpu);
			em.flush();
			this.bpu = em.find(Bpu.class, bpu.getId());
		}
		return bpu;
	}

	public BuildingLocation getBuildingLocation(EntityManager em) {
		if (buildingLocation == null) {
			BuildingLocation buildingLocation = new BuildingLocation();
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
		if (citizen == null) {
			Citizen citizen = new Citizen();
			citizen.setLegalEntity(getLegalEntity(em));
			citizen.setGender(GenderTypeEnum.FEMALE);
			em.persist(citizen);
			em.flush();
			this.citizen = em.find(Citizen.class, citizen.getId());
		}
		return citizen;
	}

	public Date getDate() {
		if (date == null) {
			date = new Date(new java.util.Date().getTime());
		}
		return date;
	}

	public Eia getEia(EntityManager em) {
		if (eia == null) {
			Eia eia = new Eia();
			eia.setEiaType(getEiaType(em));
			eia.setObu(getObu(em));
			eia.setProvider(getExternalProvider(em));
			eia.setResponsibleRole(getRole(em));
			eia.setSerialNumber("Eia test serialNumber");
			eia.setFixedAssetIdentifier("Eia test asset");
			eia.setMaintenanceProvider(getExternalProvider(em));
			em.persist(eia);
			em.flush();
			this.eia = em.find(Eia.class, eia.getId());
		}
		return eia;
	}

	public EiaType getEiaType(EntityManager em) {
		if (eiaType == null) {
			EiaType eiaType = new EiaType();
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
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan = new EiaTypeMaintenancePlan();
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
			ExternalProvider externalProvider = new ExternalProvider();
			externalProvider.setInstitution(getInstitution(em));
			em.persist(externalProvider);
			em.flush();
			this.externalProvider = em.find(ExternalProvider.class,
					externalProvider.getId());
		}
		return externalProvider;
	}

	public Institution getInstitution(EntityManager em) {
		if (institution == null) {
			Institution institution = new Institution();
			institution.setName("Institution name test");
			institution.setLegalEntity(getLegalEntity(em));
			em.persist(institution);
			em.flush();
			this.institution = em.find(Institution.class, institution.getId());
		}
		return institution;
	}

	public LegalEntity getLegalEntity(EntityManager em) {
		if (legalEntity == null) {
			LegalEntity legalEntity = new LegalEntity();
			em.persist(legalEntity);
			em.flush();
			this.legalEntity = em.find(LegalEntity.class, legalEntity.getId());
		}
		return legalEntity;
	}

	public MaintenanceActivity getMaintenanceActivity(EntityManager em) {
		if (maintenanceActivity == null) {
			MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
			maintenanceActivity.setName("MaintenanceActivity test name");
			maintenanceActivity
					.setDescription("MaintenanceActivity test description");
			em.persist(maintenanceActivity);
			em.flush();
			this.maintenanceActivity = em.find(MaintenanceActivity.class,
					maintenanceActivity.getId());
		}
		return maintenanceActivity;
	}

	public MaintenanceActivityMaintenanceProtocol getMaintenanceActivityMaintenanceProtocol(
			EntityManager em, MaintenanceActivity maintenanceActivity,
			MaintenanceProtocol maintenanceProtocol) {
		if (maintenanceActivityMaintenanceProtocol == null) {
			MaintenanceActivityMaintenanceProtocol maintenanceActivityMaintenanceProtocol = new MaintenanceActivityMaintenanceProtocol();
			maintenanceActivityMaintenanceProtocol
					.setActivity(maintenanceActivity);
			maintenanceActivityMaintenanceProtocol
					.setProtocol(maintenanceProtocol);
			em.persist(maintenanceActivityMaintenanceProtocol);
			em.flush();
			this.maintenanceActivityMaintenanceProtocol = em.find(
					MaintenanceActivityMaintenanceProtocol.class,
					maintenanceActivityMaintenanceProtocol.getId());
		}
		return maintenanceActivityMaintenanceProtocol;
	}

	public MaintenanceActivityServiceResource getMaintenanceActivityServiceResource(
			EntityManager em, MaintenanceActivity maintenanceActivity,
			ServiceResource serviceResource) {
		if (maintenanceActivityServiceResource == null) {
			MaintenanceActivityServiceResource maintenanceActivityServiceResource = new MaintenanceActivityServiceResource();
			maintenanceActivityServiceResource
					.setProtocolActivity(maintenanceActivity);
			maintenanceActivityServiceResource
					.setServiceResource(serviceResource);
			em.persist(maintenanceActivityServiceResource);
			em.flush();
			this.maintenanceActivityServiceResource = em.find(
					MaintenanceActivityServiceResource.class,
					maintenanceActivityServiceResource.getId());
		}
		return maintenanceActivityServiceResource;
	}

	public MaintenancePlan getMaintenancePlan(EntityManager em) {
		if (maintenancePlan == null) {
			MaintenancePlan maintenancePlan = new MaintenancePlan();
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

	public MaintenanceProtocol getMaintenanceProtocol(EntityManager em) {
		if (maintenanceProtocol == null) {
			MaintenanceProtocol maintenanceProtocol = new MaintenanceProtocol();
			maintenanceProtocol.setName("MaintenanceProtocol test name");
			em.persist(maintenanceProtocol);
			em.flush();
			this.maintenanceProtocol = em.find(MaintenanceProtocol.class,
					maintenanceProtocol.getId());
		}
		return maintenanceProtocol;
	}

	public Manufacturer getManufacturer(EntityManager em) {
		if (manufacturer == null) {
			Manufacturer manufacturer = new Manufacturer();
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
			Obu obu = new Obu();
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
			Role role = new Role();
			role.setName("Role test name");
			em.persist(role);
			em.flush();
			this.role = em.find(Role.class, role.getId());
		}
		return role;
	}

	public ServiceResource getServiceResource(EntityManager em) {
		if (serviceResource == null) {
			ServiceResource serviceResource = new ServiceResource();
			serviceResource.setName("ServiceResource test name");
			em.persist(serviceResource);
			em.flush();
			this.serviceResource = em.find(ServiceResource.class,
					serviceResource.getId());
		}
		return serviceResource;
	}
}
