package org.fourgeeks.gha.ejb;

import javax.persistence.EntityManager;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.HasKey;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.InstanceLogon;
import org.fourgeeks.gha.domain.ess.ItSystem;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.ess.InstanceLogonService;
import org.fourgeeks.gha.ejb.ess.RoleService;
import org.fourgeeks.gha.ejb.ess.SSOUserService;
import org.fourgeeks.gha.ejb.ess.WorkingAreaService;
import org.fourgeeks.gha.ejb.gar.BpuFunctionService;
import org.fourgeeks.gha.ejb.gar.BpuFunctionServiceRemote;
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
				.addPackage(BpuFunctionServiceRemote.class.getPackage())
				.addPackage(Brand.class.getPackage())
				.addPackage(BrandService.class.getPackage())
				.addPackage(EiaMobilityEnum.class.getPackage())
				.addPackage(EJBException.class.getPackage())
				.addPackage(ExternalProvider.class.getPackage())
				.addPackage(Facility.class.getPackage())
				.addPackage(GhaServiceTest.class.getPackage())
				.addPackage(HasKey.class.getPackage())
				.addPackage(ItSystem.class.getPackage())
				.addPackage(InstanceLogon.class.getPackage())
				.addPackage(InstanceLogonService.class.getPackage())
				.addPackage(LegalEntity.class.getPackage())
				.addPackage(LocationLevelEnum.class.getPackage())
				.addPackage(Material.class.getPackage())
				.addPackage(Role.class.getPackage())
				.addPackage(RoleService.class.getPackage())
				.addPackage(ServiceResource.class.getPackage())
				.addPackage(SSOUser.class.getPackage())
				.addPackage(SSOUserService.class.getPackage())
				.addPackage(WorkingArea.class.getPackage())
				.addPackage(WorkingAreaService.class.getPackage())
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
