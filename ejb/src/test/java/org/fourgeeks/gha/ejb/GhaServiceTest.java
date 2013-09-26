package org.fourgeeks.gha.ejb;

import javax.persistence.EntityManager;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.ess.InstanceLogonService;
import org.fourgeeks.gha.ejb.gar.BpuFunctionService;
import org.fourgeeks.gha.ejb.gmh.BrandService;
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
	private Institution institution = null;
	private LegalEntity legalEntity = null;

	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(AbstractEntity.class.getPackage())
				.addPackage(Bpa.class.getPackage())
				.addPackage(Bpu.class.getPackage())
				.addPackage(BpuFunction.class.getPackage())
				.addPackage(BpuFunctionService.class.getPackage())
				.addPackage(Brand.class.getPackage())
				.addPackage(BrandService.class.getPackage())
				.addPackage(EiaMobilityEnum.class.getPackage())
				.addPackage(EJBException.class.getPackage())
				.addPackage(ExternalProvider.class.getPackage())
				.addPackage(GhaServiceTest.class.getPackage())
				.addPackage(InstanceLogonService.class.getPackage())
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

}
