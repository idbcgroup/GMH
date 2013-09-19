package org.fourgeeks.gha.ejb;

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

import org.fourgeeks.gha.domain.codes.FunctionsCodes;
import org.fourgeeks.gha.domain.codes.ModulesCodes;
import org.fourgeeks.gha.domain.codes.ScreenCodes;
import org.fourgeeks.gha.domain.codes.ViewCodes;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.ess.Module;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.ess.Screen;
import org.fourgeeks.gha.domain.ess.View;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.Message;
import org.fourgeeks.gha.ejb.ess.FunctionServiceRemote;
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

	@EJB(name = "ess.FunctionService")
	FunctionServiceRemote functionService;

	/**
	 * 
	 */
	@PostConstruct
	public void loadTestData() {
		legalEntityTestData();
		institutionTestData();
		citizenTestData();
		//
		bpiTestData();
		bpuTestData();
		// //
		modulesTestData();
		bpuFunctionTestData();
		//
		obuTestData();
		roleTestData();
		buildingLocationsTestData();
		//
		ssoUserTestData();
		//
		messagesTestData();
		//
		manufacturerTestData();
		brandTestData();
		externalProviderTestData();
		materialCategoryTestData();
		materialTestData();
		facilityTestData();
		// // TODO
		eiaTypeTestData();
		eiaTestData();
	}

	private void messagesTestData() {
		String query = "SELECT t FROM Message t WHERE t.id = 'LOGIN-001'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating bpumessage test data");
				em.persist(new Message("LOGIN-001", LanguageEnum.ES,
						"Login exitoso"));
				em.persist(new Message("LOGIN-002", LanguageEnum.ES,
						"Usuario desconocido"));
				em.persist(new Message("LOGIN-003", LanguageEnum.ES,
						"Usuario bloqueado"));
				em.persist(new Message("LOGIN-004", LanguageEnum.ES,
						"Contraseña erronea1"));
				em.persist(new Message("LOGIN-005", LanguageEnum.ES,
						"Contraseña erronea2"));
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error Creating message test data", e1);
			}
		}
	}

	/**
	 * 
	 */
	private void bpuTestData() {
		String query = "SELECT t FROM Bpu t WHERE t.id = '1'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating bpu test data");
				for (int i = 0; i < 5; ++i)
					em.persist(new Bpu(em.find(Bpi.class, 1L), em.find(
							Citizen.class, i + 1L)));
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error Creating bpu test data", e1);
			}
		}
	}

	/**
	 * 
	 */
	private void bpuFunctionTestData() {
		String query = "SELECT t FROM BpuFunction t WHERE t.id = '1'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating bpufunction test data");
				List<Function> all = functionService.getAll();
				for (Function function : all)
					em.persist(new BpuFunction(em.find(Bpu.class, 1L), em.find(
							Function.class, function.getCode())));
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error Creating bpufunction test data",
						e1);
			}
		}
	}

	/**
	 * 
	 */
	private void citizenTestData() {
		String query = "SELECT t FROM Citizen t WHERE t.id = '1'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating Citizen test data");
				for (int i = 0; i < 5; ++i) {
					Citizen citizen = new Citizen(em.find(LegalEntity.class,
							i + 5L), GenderTypeEnum.MALE);
					citizen.setFirstName(" Test Citizen : " + i);
					citizen.setFirstLastName(" Test Citizen : " + i);
					citizen.setIdType(DocumentTypeEnum.LOCAL);
					em.persist(citizen);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating Citizen test data : ",
						e1);
			}
		}

	}

	private void modulesTestData() {
		String query = "SELECT t from Module t WHERE t.code ='"
				+ ModulesCodes.USER + "'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : module, view, screen, functions");
				Module moduleUser = new Module("Usuarios", ModulesCodes.USER);
				em.persist(moduleUser);
				Module moduleEiaType = new Module("Tipos de equipo",
						ModulesCodes.EIATYPE);
				em.persist(moduleEiaType);
				Module moduleEia = new Module("Equipos", ModulesCodes.EIA);
				em.persist(moduleEia);
				// Screen
				Screen screenUsuer = new Screen(moduleUser,
						"Administración de usuarios", ScreenCodes.USER_ADM,
						"user");
				em.persist(screenUsuer);
				Screen screenEiaType = new Screen(moduleEiaType,
						"Tipos de equipo", ScreenCodes.EIATYPE_ADM, "eiatype");
				em.persist(screenEiaType);
				Screen screenEia = new Screen(moduleEia, "Equipos",
						ScreenCodes.EIA_ADM, "eia");
				em.persist(screenEia);
				// View
				View viewUserInfo = new View(screenUsuer, "Información",
						ViewCodes.USER_ADM_INFO);
				em.persist(viewUserInfo);
				View viewUserCred = new View(screenUsuer, "Credenciales",
						ViewCodes.USER_ADM_CRED);
				em.persist(viewUserCred);
				View viewUserLLog = new View(screenUsuer, "Logon log",
						ViewCodes.USER_ADM_LLOG);
				em.persist(viewUserLLog);
				View viewEiaTypeInfo = new View(screenEiaType, "Información",
						ViewCodes.EIATYPE_ADM_INFO);
				em.persist(viewEiaTypeInfo);
				View viewEiaTypeEquip = new View(screenEiaType, "Equipos",
						ViewCodes.EIATYPE_ADM_EQUI);
				em.persist(viewEiaTypeEquip);
				View viewEiaTypeComp = new View(screenEiaType, "Componentes",
						ViewCodes.EIATYPE_ADM_COMP);
				em.persist(viewEiaTypeComp);
				View viewEiaTypeMate = new View(screenEiaType, "Materiales",
						ViewCodes.EIATYPE_ADM_MATE);
				em.persist(viewEiaTypeMate);
				View viewEiaTypeServ = new View(screenEiaType,
						"Servicios utilitarios", ViewCodes.EIATYPE_ADM_SERV);
				em.persist(viewEiaTypeServ);
				View viewEiaInfo = new View(screenEia, "Información",
						ViewCodes.EIA_ADM_INFO);
				em.persist(viewEiaInfo);
				View viewEiaComp = new View(screenEia, "Componentes",
						ViewCodes.EIA_ADM_COMP);
				em.persist(viewEiaComp);
				// Function
				Function function = new Function(viewUserInfo, "ver",
						FunctionsCodes.USER_ADM_INFO_VIEW);
				em.persist(function);
				function = new Function(viewUserInfo, "editar",
						FunctionsCodes.USER_ADM_INFO_EDIT);
				em.persist(function);
				function = new Function(viewUserCred, "ver",
						FunctionsCodes.USER_ADM_CRED_VIEW);
				em.persist(function);
				function = new Function(viewUserCred, "editar",
						FunctionsCodes.USER_ADM_CRED_EDIT);
				em.persist(function);
				function = new Function(viewUserLLog, "ver",
						FunctionsCodes.USER_ADM_LLOG_VIEW);
				em.persist(function);
				function = new Function(viewUserLLog, "editar",
						FunctionsCodes.USER_ADM_LLOG_EDIT);
				em.persist(function);
				function = new Function(viewEiaTypeEquip, "ver",
						FunctionsCodes.EIATYPE_ADM_EQUI_VIEW);
				em.persist(function);
				function = new Function(viewEiaTypeEquip, "editar",
						FunctionsCodes.EIATYPE_ADM_EQUI_EDIT);
				em.persist(function);
				function = new Function(viewEiaTypeComp, "ver",
						FunctionsCodes.EIATYPE_ADM_COMP_VIEW);
				em.persist(function);
				function = new Function(viewEiaTypeComp, "editar",
						FunctionsCodes.EIATYPE_ADM_COMP_EDIT);
				em.persist(function);
				function = new Function(viewEiaTypeMate, "ver",
						FunctionsCodes.EIATYPE_ADM_MATE_VIEW);
				em.persist(function);
				function = new Function(viewEiaTypeMate, "editar",
						FunctionsCodes.EIATYPE_ADM_MATE_EDIT);
				em.persist(function);
				function = new Function(viewEiaTypeServ, "ver",
						FunctionsCodes.EIATYPE_ADM_SERV_VIEW);
				em.persist(function);
				function = new Function(viewEiaTypeServ, "editar",
						FunctionsCodes.EIATYPE_ADM_SERV_EDIT);
				em.persist(function);
				function = new Function(viewEiaInfo, "ver",
						FunctionsCodes.EIA_ADM_INFO_VIEW);
				em.persist(function);
				function = new Function(viewEiaInfo, "editar",
						FunctionsCodes.EIA_ADM_INFO_EDIT);
				em.persist(function);
				function = new Function(viewEiaComp, "ver",
						FunctionsCodes.EIA_ADM_COMP_VIEW);
				em.persist(function);
				function = new Function(viewEiaComp, "editar",
						FunctionsCodes.EIA_ADM_COMP_EDIT);
				em.persist(function);

				em.flush();
			} catch (Exception e1) {
				logger.log(
						Level.INFO,
						"error creating test data: module, view, screen, functions",
						e1);
			}
		}
	}

	/**
	 * 
	 */
	private void materialCategoryTestData() {
		String query = "SELECT t from MaterialCategory t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : materialCategory");
				for (int j = 0; j < 3; j++) {
					em.persist(new MaterialCategory("mat-cat-00" + j,
							"material-category-00" + j, MaterialTypeEnum
									.values()[j % 3]));
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
	private void facilityTestData() {
		String query = "SELECT t from Facility t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			logger.info("Creating test data : facility");
			Facility facility = new Facility();
			facility.setBuildingLocation(em.find(BuildingLocation.class,
					"Building 000"));
			em.persist(facility);
		}

	}

	private void materialTestData() {
		String query = "SELECT t from Material t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : material");
				for (int j = 0; j < 3; j++) {
					Material next = new Material("mat-00" + j, "material-00"
							+ j, MaterialTypeEnum.values()[j % 3]);
					next.setMaterialCategory(em.find(MaterialCategory.class,
							(long) (j + 1)));
					em.persist(next);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error creating test data: external provider", e);
			}
		}
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
					em.persist(new LegalEntity("J-000" + i));

				for (int i = 5; i < 10; i++)
					em.persist(new LegalEntity("V-000" + i));

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

	private void roleTestData() {
		String query = "SELECT t from Role t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : Role");
				Role role = new Role();
				role.setName("Test Role 1");
				em.persist(role);
				role = new Role();
				role.setName("Test Role 2");
				em.persist(role);
				role = new Role();
				role.setName("Test Role 3");
				em.persist(role);

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data role", e);
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

				String brandNames[] = new String[] { "HP", "Epson", "Compaq",
						"Dell", "Canon" };
				List<Manufacturer> mans = em.createNamedQuery(
						"Manufacturer.getAll", Manufacturer.class)
						.getResultList();
				int k = 0;
				for (String brandName : brandNames) {
					Brand next = new Brand();
					next.setName(brandName);
					next.setManufacturer(mans.get(k++));
					em.persist(next);
					em.flush();
				}
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
						em.find(Brand.class, 1L), "Impresora Tinta",
						EiaMobilityEnum.FIXED, EiaTypeEnum.EQUIPMENT,
						EiaSubTypeEnum.IT_SYSTEM, "Stylus");
				em.persist(eiaType);

				eiaType = new EiaType("90002", em.find(Brand.class, 2L),
						"Impresora Laser", EiaMobilityEnum.FIXED,
						EiaTypeEnum.EQUIPMENT, EiaSubTypeEnum.IT_SYSTEM,
						"Deskjet");
				em.persist(eiaType);

				eiaType = new EiaType("90003", em.find(Brand.class, 3L),
						"Cartucho Tricolor", EiaMobilityEnum.FIXED,
						EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "EP60");
				em.persist(eiaType);

				eiaType = new EiaType("90004", em.find(Brand.class, 4L),
						"Toner Laser", EiaMobilityEnum.FIXED, EiaTypeEnum.PART,
						EiaSubTypeEnum.IT_SYSTEM, "HP60");
				em.persist(eiaType);

				eiaType = new EiaType("90005", em.find(Brand.class, 5L),
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

				Facility facility = em.find(Facility.class, 1L);
				Obu obu = em.find(Obu.class, 1L);
				ExternalProvider eProvider = em
						.find(ExternalProvider.class, 1L);
				Role bRole = em.find(Role.class, 1L);

				for (int i = 1; i < 4; ++i) {
					Eia eia = new Eia(bRole, em.find(EiaType.class, "9000"
							+ Long.toString(i)), obu,
							EiaStateEnum.values()[i % 3]);
					eia.setCode("eia-00" + i);
					eia.setFacility(facility);
					eia.setProvider(eProvider);
					em.persist(eia);
					em.flush();
				}

			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test eia", e1);
			}
		}
	}

	private void ssoUserTestData() {
		try {
			String query = "SELECT t from SSOUser t WHERE t.id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				logger.info("creating test data: users");
				em.persist(new SSOUser(em.find(Bpu.class, 1L), "admin",
						"admin", UserLogonStatusEnum.STAYIN));
				em.persist(new SSOUser(em.find(Bpu.class, 2L), "asanchez",
						"asanchez", UserLogonStatusEnum.STAYIN));

				em.flush();
				logger.info("done creating test users");
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "error creating test data:  users", e);
		}
	}

}