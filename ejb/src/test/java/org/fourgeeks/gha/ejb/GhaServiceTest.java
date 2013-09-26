package org.fourgeeks.gha.ejb;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.HasKey;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.InstanceLogon;
import org.fourgeeks.gha.domain.ess.ItSystem;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.ess.InstanceLogonService;
import org.fourgeeks.gha.ejb.ess.RoleService;
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
	/**
	 * @return the deployment descriptor
	 */

	private Bpa bpa = null;

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
				.addPackage(WorkingArea.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("jbossas-ds.xml");
	}

	public Bpa getBpa() {
		return bpa;
	}

	public void setBpa(Bpa bpa) {
		this.bpa = bpa;
	}
}
