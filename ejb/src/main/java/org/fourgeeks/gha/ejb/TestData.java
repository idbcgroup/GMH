package org.fourgeeks.gha.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.ess.SingleSignOnUser;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;

/**
 * @author alacret
 * 
 */
@Startup
@Singleton
public class TestData {

	private final static Logger logger = Logger.getLogger(TestData.class
			.getName());

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BrandService")
	BrandServiceRemote brandService;

	@EJB(name = "gmh.ManufacturerService")
	ManufacturerServiceRemote manufacturerService;

	@EJB(name = "gmh.EiaTypeService")
	EiaTypeServiceRemote eiaTypeServ;

	@EJB(name = "gmh.EiaService")
	EiaServiceRemote eiaServ;

	@EJB(name = "gmh.EiaTypeComponentService")
	EiaTypeComponentServiceRemote etcService;

	@EJB(name = "gmh.EiaComponentService")
	EiaComponentServiceRemote ecService;

	/**
	 * 
	 */
	@PostConstruct
	public void loadTestData() {
		legalEntityTestData();
		institutionTestData();
		bpiTestData();
		obuTestData();
		roleBaseTestData();
		buildingLocationsTestData();
		userTestData();
		brandTestData();
		manufacturerTestData();
		externalProviderTestData();
		materialTestData();
		// // TODO
		eiaTypeTestData();
		eiaTestData();
		eiaTypeMaintenancePlanTestData();
		eiaTypeMaintenanceProtocolTestData();
	}

	private void materialTestData() {
		String query = "SELECT t from Material t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : material");
				for (int j = 0; j < 3; j++) {
					em.persist(new Material("mat-00"+j, "material-00"+j, MaterialTypeEnum.values()[j%3]));
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error creating test data: external provider", e);
			}
		}
	}

	/**
	 * 
	 */
	private void eiaTypeMaintenanceProtocolTestData() {
		String query = "SELECT t from EiaTypeMaintenanceProtocol t WHERE t.id = 1 ";
		try{
			em.createQuery(query).getSingleResult();
		}catch(NoResultException e){
			System.out.println("Creating test data for EiaTypeMaintenanceProtocols");
			EiaTypeMaintenancePlan plans[] = new EiaTypeMaintenancePlan[2];
			
			plans[0] = em.find(EiaTypeMaintenancePlan.class, 1L);
			plans[1] = em.find(EiaTypeMaintenancePlan.class, 2L);
			
			for(int i = 0, serial = 1; i<2; ++i){
				for(int j = 0, ordinal = 1; j<1; ++j, ordinal = 1){
					EiaTypeMaintenanceProtocol parentProtocol = new EiaTypeMaintenanceProtocol();
					parentProtocol.setEiaTypeMaintenancePlan(plans[i]);
//					parentProtocol.setOrdinal(ordinal++);
					parentProtocol.setDescription("Protocol #"+Integer.toString(serial));
					em.persist(parentProtocol);
					
					parentProtocol = em.find(EiaTypeMaintenanceProtocol.class, (long) serial++);
					List<EiaTypeMaintenanceProtocol> childrenProtocols = new ArrayList <EiaTypeMaintenanceProtocol>();
					
					for(int k = 0; k<2; ++k, ++serial, ++ordinal){
						EiaTypeMaintenanceProtocol childProtocol = new EiaTypeMaintenanceProtocol();
						childProtocol.setEiaTypeMaintenancePlan(plans[i]);
						childProtocol.setOrdinal(ordinal);
						childProtocol.setParentProtocol(parentProtocol);
						childProtocol.setDescription("Protocol #"+serial+" (CHILD OF "+parentProtocol.getDescription() +")");
						em.persist(childProtocol);
						
						childProtocol = em.find(EiaTypeMaintenanceProtocol.class, (long)(serial));
						childrenProtocols.add(childProtocol);
					}
					parentProtocol.setChildrenProtocols(childrenProtocols);
					em.merge(parentProtocol);
				}
			}
		}
	}

	/**
	 * 
	 */
	private void eiaTypeMaintenancePlanTestData() {
		System.out.println("Creating test data for EiaTypeMaintenancePlans");
		String query = "SELECT t from EiaTypeMaintenancePlan t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			for(int i=1; i <= 5; ++i){
				EiaTypeMaintenancePlan eiaTypeMaintenancePlan = new EiaTypeMaintenancePlan();
				eiaTypeMaintenancePlan.setDescription("EiaTypeMaintenancePlan #" + Integer.toString(i));
				em.persist(eiaTypeMaintenancePlan);
			}
		}
		System.out.println("Finished loading test data for EiaTypeMaintenancePlans");
		
	}

	private void externalProviderTestData() {
		String query = "SELECT t from ExternalProvider t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : external provider");
				ExternalProvider eP = null;

				for (int j = 0; j < 3; j++) {
					eP = new ExternalProvider();
					eP.setInstitution(em.find(Institution.class,
							Long.valueOf(j + 3)));
					em.persist(eP);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error creating test data: external provider", e);
			}
		}
	}

	private void legalEntityTestData() {
		String query = "SELECT t from LegalEntity t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : legal entity ");
				for (int i = 0; i < 5; i++)
					em.persist(new LegalEntity());
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data legal entity",
						e);
			}
		}
	}

	private void institutionTestData() {
		String query = "SELECT t from Institution t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : institution");
				Institution institution = null;
				for (int i = 0; i < 5; i++) {
					institution = new Institution();
					institution.setName("Test Institution " + i);
					institution.setLegalEntity(em.find(LegalEntity.class,
							Long.valueOf(i + 1L)));
					em.persist(institution);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data institution",
						e1);
			}
		}
	}

	private void bpiTestData() {
		String query = "SELECT t from Bpi t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : bpi");
				Bpi bpi = new Bpi();
				bpi.setInstitution(em.find(Institution.class, 1L));
				em.persist(bpi);

				bpi = new Bpi();
				bpi.setInstitution(em.find(Institution.class, 2L));
				em.persist(bpi);

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data bpi", e);
			}
		}
	}

	private void obuTestData() {
		String query = "SELECT t from Obu t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : obu");
				Obu obu = null;
				for (int i = 0; i < 3; i++) {
					obu = new Obu();
					obu.setName("Test Obu " + i);
					obu.setCode("Test code " + i);
					obu.setBpi(em.find(Bpi.class, 1L));
					em.persist(obu);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data obu", e);
			}
		}
	}

	private void roleBaseTestData() {
		String query = "SELECT t from RoleBase t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : RoleBase");
				RoleBase baseRole = new RoleBase();
				baseRole.setName("Test Role Base 1");
				em.persist(baseRole);
				baseRole = new RoleBase();
				baseRole.setName("Test Role Base 2");
				em.persist(baseRole);
				baseRole = new RoleBase();
				baseRole.setName("Test Role Base 3");
				em.persist(baseRole);

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data obu", e);
			}
		}
	}

	private void buildingLocationsTestData() {
		String query = "SELECT t from BuildingLocation t WHERE t.code='Building 000'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating eia building locations");

				for (int i = 0; i < 5; i++) {
					BuildingLocation buildingLocation = new BuildingLocation(
							em.find(Bpi.class, 1L), "Building 00" + i,
							LocationLevelEnum.BUILDING,
							"Building Location Name " + i);
					em.persist(buildingLocation);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test building location",
						e);
			}
		}
	}

	/**
	 * 
	 */
	private void manufacturerTestData() {

		try {
			String query = "SELECT e from Manufacturer e WHERE e.id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				logger.log(Level.INFO, "creating test manufacturers");
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setName("AMD");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Caribe");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Sidor");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Alpes");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Nipon");
				em.persist(manufacturer);

				em.flush();
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "error creating test manufacturers", e);
		}

	}

	/**
	 * 
	 */
	private void brandTestData() {
		try {
			String query = "SELECT e from Brand e WHERE e.id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				logger.log(Level.INFO, "creating test brands");
				Brand brand = new Brand();
				brand.setName("HP");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Epson");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Compaq");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Dell");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Canon");
				em.persist(brand);

				em.flush();
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "error creating test brands", e);
		}

	}

	private void eiaTypeTestData() {
		String query = "SELECT t from EiaType t WHERE t.code = '90001'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test eiaType");
				EiaType eiaType = new EiaType("90001",
						em.find(Brand.class, 1L), em.find(Manufacturer.class, 1L),
						"Impresora Tinta", EiaMobilityEnum.FIXED,
						EiaTypeEnum.EQUIPMENT, EiaSubTypeEnum.IT_SYSTEM, "Stylus");
				em.persist(eiaType);

				eiaType = new EiaType("90002",
						em.find(Brand.class, 2L), em.find(Manufacturer.class, 2L),
						"Impresora Laser", EiaMobilityEnum.FIXED,
						EiaTypeEnum.EQUIPMENT, EiaSubTypeEnum.IT_SYSTEM, "Deskjet");
				em.persist(eiaType);

				eiaType = new EiaType("90003",
						em.find(Brand.class, 3L), em.find(Manufacturer.class, 3L),
						"Cartucho Tricolor", EiaMobilityEnum.FIXED,
						EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "EP60");
				em.persist(eiaType);

				eiaType = new EiaType("90004",
						em.find(Brand.class, 4L), em.find(Manufacturer.class, 4L),
						"Toner Laser", EiaMobilityEnum.FIXED,
						EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "HP60");
				em.persist(eiaType);

				eiaType = new EiaType("90005",
						em.find(Brand.class, 5L), em.find(Manufacturer.class, 5L),
						"Cartucho Negro", EiaMobilityEnum.FIXED,
						EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "EPN60");
				em.persist(eiaType);

				em.flush();

			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test eiatype", e);
			}
		}
	}

	private void eiaTestData() {
		String query = "SELECT t from Eia t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test eia");

				Facility facility = new Facility();
				facility.setBuildingLocation(em.find(BuildingLocation.class,
						"Building 000"));
				em.persist(facility);
				em.flush();

				Obu obu = em.find(Obu.class, 2L);
				BuildingLocation bLocation = em.find(BuildingLocation.class,
						"Building 000");
				ExternalProvider eProvider = em
						.find(ExternalProvider.class, 1L);
				RoleBase bRole = em.find(RoleBase.class, 1L);

				Eia eia = new Eia(bRole, em.find(EiaType.class, "90001"), bLocation,
						bLocation, obu, EiaStateEnum.CREATED);
				eia.setCode("Stylus-001");
				eia.setSerialNumber("001");
				eia.setProvider(eProvider);
				eia.setCode("p-001");
				em.persist(eia);

				Eia eia2 = new Eia(bRole, em.find(EiaType.class, "90002"), bLocation,
						bLocation, obu, EiaStateEnum.CREATED);
				eia2.setProvider(eProvider);
				eia2.setCode("p-002");

				Eia eia3 = new Eia(bRole, em.find(EiaType.class, "90003"), bLocation,
						bLocation, obu, EiaStateEnum.CREATED);
				eia3.setProvider(eProvider);
				eia3.setCode("p-003");

				Eia eia4 = new Eia(bRole, em.find(EiaType.class, "90004"), bLocation,
						bLocation, obu, EiaStateEnum.CREATED);
				eia4.setObu(obu);
				eia4.setProvider(eProvider);
				eia4.setCode("p-004");

				Eia eia5 = new Eia(bRole, em.find(EiaType.class, "90005"), bLocation,
						bLocation, obu, EiaStateEnum.CREATED);
				eia5.setObu(obu);
				eia5.setProvider(eProvider);
				eia5.setCode("p-005");

				em.persist(eia2);
				em.persist(eia3);
				em.persist(eia4);
				em.persist(eia5);
				em.flush();

			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test eia", e);
			}
		}
	}

	private void userTestData() {
		try {
			String query = "SELECT t from SingleSignOnUser t WHERE t.id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				logger.info("creating test data: users");
				SingleSignOnUser signOnUser = new SingleSignOnUser();
				signOnUser.setLegalEntity(em.find(LegalEntity.class, 2L));
				signOnUser.setPassword("admin");
				signOnUser.setUserName("admin");
				em.persist(signOnUser);

				signOnUser = new SingleSignOnUser();
				signOnUser.setLegalEntity(em.find(LegalEntity.class, 3L));
				signOnUser.setPassword("asanchez");
				signOnUser.setUserName("asanchez");
				em.persist(signOnUser);

				em.flush();
				logger.info("done creating test users");
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "error creating test data:  users", e);
		}
	}

}