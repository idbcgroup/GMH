package org.fourgeeks.gha.ejb;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.HasKey;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.ess.ItSystem;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.domain.logs.LogonLog;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.Message;
import org.fourgeeks.gha.ejb.ess.FunctionServiceRemote;
import org.fourgeeks.gha.ejb.gar.BpuFunctionService;
import org.fourgeeks.gha.ejb.gar.BpuFunctionServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BrandService;
import org.fourgeeks.gha.ejb.log.LogonLogServiceRemote;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * @author alacret
 * 
 */
public class GhaServiceTest {
	/**
	 * @return the deployment descriptor
	 */
	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage(Brand.class.getPackage())
				.addPackage(GhaServiceTest.class.getPackage())
				.addPackage(HasKey.class.getPackage())
				.addPackage(AbstractEntity.class.getPackage())
				.addPackage(WorkingArea.class.getPackage())
				.addPackage(EiaMobilityEnum.class.getPackage())
				.addPackage(FunctionServiceRemote.class.getPackage())
				.addPackage(ExternalProvider.class.getPackage())
				.addPackage(Facility.class.getPackage())
				.addPackage(Function.class.getPackage())
				.addPackage(LegalEntity.class.getPackage())
				.addPackage(BpuFunction.class.getPackage())
				.addPackage(GHAEJBException.class.getPackage())
				.addPackage(javax.ejb.EJBException.class.getPackage())
				.addPackage(LogonLog.class.getPackage())
				.addPackage(LogonLogServiceRemote.class.getPackage())
				.addPackage(Message.class.getPackage())
				.addPackage(ItSystem.class.getPackage())
				.addPackage(AbstractEntity.class.getPackage())
				.addPackage(Bpu.class.getPackage())
				.addPackage(Bpa.class.getPackage())
				.addPackage(LocationLevelEnum.class.getPackage())
				.addPackage(ServiceResource.class.getPackage())
				.addPackage(Material.class.getPackage())
				.addPackage(BpuFunctionServiceRemote.class.getPackage())
				.addPackage(BpuFunctionService.class.getPackage())
				.addPackage(LegalEntity.class.getPackage())
				.addPackage(Brand.class.getPackage())
				.addPackage(BrandService.class.getPackage())
				.addPackage(GHAEJBException.class.getPackage())
				.addAsResource("test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("jbossas-ds.xml");
	}
}
