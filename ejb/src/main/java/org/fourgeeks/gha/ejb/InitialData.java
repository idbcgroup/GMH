package org.fourgeeks.gha.ejb;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.conf.Parameter;
import org.fourgeeks.gha.domain.conf.ParameterGroup;
import org.fourgeeks.gha.domain.conf.ParameterValue;
import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivityState;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.domain.enu.CCDICodeTypeEnum;
import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.ui.AppForm;
import org.fourgeeks.gha.domain.ess.ui.AppFormView;
import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunction;
import org.fourgeeks.gha.domain.ess.ui.AppFormViewFunctionBpu;
import org.fourgeeks.gha.domain.ess.ui.Function;
import org.fourgeeks.gha.domain.ess.ui.Module;
import org.fourgeeks.gha.domain.ess.ui.View;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.gom.Concept;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.domain.msg.UiString;
import org.fourgeeks.gha.ejb.ess.AppFormViewFunctionServiceRemote;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author alacret
 * 
 */
@Startup
@Singleton
public class InitialData {

	private final static Logger logger = Logger.getLogger(InitialData.class
			.getName());

	@PersistenceContext
	EntityManager em;

	@EJB(name = "ess.AppFormViewFunctionService")
	AppFormViewFunctionServiceRemote functionService;

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

	/**
	 * 
	 */
	private void bpuFunctionTestData() {
		try {
			logger.info("Creating bpufunction test data");
			List<AppFormViewFunction> all = functionService.getAll();
			Bpu admin = em.find(Bpu.class, 1L);
			Bpu gha = em.find(Bpu.class, 3L);
			for (AppFormViewFunction function : all) {
				em.merge(new AppFormViewFunctionBpu(admin, function
						.getAppForm(), function.getView(), function
						.getFunction()));
				em.merge(new AppFormViewFunctionBpu(gha, function.getAppForm(),
						function.getView(), function.getFunction()));
			}

			// Bpu gha = em.find(Bpu.class, 3L);
			//
			// for (Function function : all) {
			// em.persist(new AppFormViewFunctionBpu(admin, function));
			// if (function.getCode().matches(
			// "^EIATYPE-ADM-(EQUI|COMP)-(VIEW|EDIT)$")) {
			// // usuario base solo eiatype
			// em.persist(new AppFormViewFunctionBpu(gha, function));
			// }
			// }
			// em.flush();
		} catch (Exception e1) {
			logger.log(Level.INFO, "error Creating bpufunction test data", e1);
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

	/**
	 * 
	 */
	private void bspTestData() {
		String query = "SELECT t from Bsp t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : bsp");
				for (int i = 0; i < 2; i++) {
					Bsp bsp = new Bsp();
					bsp.setObu(new Obu(i + 1));
					em.persist(bsp);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data bsp", e);
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
	private void ccdiLevelDefinitionTestData() {
		String query;
		query = "SELECT t from CCDILevelDefinition t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				CCDIDefinition definition = em
						.createNamedQuery("CCDIDefinition.findByCode",
								CCDIDefinition.class)
						.setParameter("code", "MATERIAL").getSingleResult();

				CCDILevelDefinition materiales = new CCDILevelDefinition(
						definition, 0, "MATERIALES", 1,
						CCDIValueTypeEnum.FIXED, 0, 0, "",
						CCDIEndValueActionEnum.RESTART);
				em.persist(materiales);

				CCDILevelDefinition type = new CCDILevelDefinition(definition,
						0, "TIPO", 1, CCDIValueTypeEnum.FIXED, 0, 0, "",
						CCDIEndValueActionEnum.RESTART);
				em.persist(type);

				CCDILevelDefinition family = new CCDILevelDefinition(
						definition, 2, "FAMILIA", 2,
						CCDIValueTypeEnum.VARIABLE, 1, 1, "",
						CCDIEndValueActionEnum.RESTART);
				em.persist(family);

				CCDILevelDefinition subFamily = new CCDILevelDefinition(
						definition, 3, "SUB FAMILIA", 2,
						CCDIValueTypeEnum.VARIABLE, 1, 1, "",
						CCDIEndValueActionEnum.RESTART);
				em.persist(subFamily);

				CCDILevelDefinition element = new CCDILevelDefinition(
						definition, 4, "ELEMENTOS", 4,
						CCDIValueTypeEnum.VARIABLE, 1, 1, "",
						CCDIEndValueActionEnum.RESTART);
				em.persist(element);

			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error creating test ccdi level definition", e);
			}

		}
	}

	/**
	 * 
	 */
	private void ccdiLevelValuesTestData() {
		String query;
		query = "SELECT t from CCDILevelValue t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test CCDILevelValue");
				CCDIDefinition definition = em
						.createNamedQuery("CCDIDefinition.findByCode",
								CCDIDefinition.class)
						.setParameter("code", "MATERIAL").getSingleResult();

				CCDILevelDefinition material = em
						.createNamedQuery("CCDILevelDefinition.findByLevel",
								CCDILevelDefinition.class)
						.setParameter("level", 0)
						.setParameter("definition", definition)
						.getSingleResult();
				CCDILevelDefinition type = em
						.createNamedQuery("CCDILevelDefinition.findByLevel",
								CCDILevelDefinition.class)
						.setParameter("level", 1)
						.setParameter("definition", definition)
						.getSingleResult();
				CCDILevelDefinition family = em
						.createNamedQuery("CCDILevelDefinition.findByLevel",
								CCDILevelDefinition.class)
						.setParameter("level", 2)
						.setParameter("definition", definition)
						.getSingleResult();
				CCDILevelDefinition subFamily = em
						.createNamedQuery("CCDILevelDefinition.findByLevel",
								CCDILevelDefinition.class)
						.setParameter("level", 3)
						.setParameter("definition", definition)
						.getSingleResult();
				CCDILevelDefinition element = em
						.createNamedQuery("CCDILevelDefinition.findByLevel",
								CCDILevelDefinition.class)
						.setParameter("level", 4)
						.setParameter("definition", definition)
						.getSingleResult();

				CCDILevelValue materialValue = new CCDILevelValue(material,
						null, "MATERIAL", "0", 0, "0",
						CCDIValueStatusEnum.ACTIVE);
				em.persist(materialValue);
				em.flush();
				materialValue = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", materialValue.getCode())
						.getSingleResult();

				CCDILevelValue supplies = new CCDILevelValue(type,
						materialValue, "SUMINISTROS", "01", 3, "1",
						CCDIValueStatusEnum.ACTIVE);
				em.persist(supplies);
				em.flush();
				supplies = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", supplies.getCode())
						.getSingleResult();

				CCDILevelValue pharmacs = new CCDILevelValue(type,
						materialValue, "FARMACOS", "04", 1, "4",
						CCDIValueStatusEnum.ACTIVE);
				em.persist(pharmacs);
				em.flush();
				pharmacs = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", pharmacs.getCode())
						.getSingleResult();

				CCDILevelValue needle = new CCDILevelValue(family, supplies,
						"AGUJAS", "0101", 3, "", CCDIValueStatusEnum.ACTIVE);
				em.persist(needle);
				em.flush();
				needle = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", needle.getCode())
						.getSingleResult();

				CCDILevelValue syringe = new CCDILevelValue(family, supplies,
						"AGUJAS", "0102", 2, "", CCDIValueStatusEnum.ACTIVE);
				em.persist(syringe);
				em.flush();
				syringe = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", syringe.getCode())
						.getSingleResult();

				CCDILevelValue antiBiotics = new CCDILevelValue(family,
						pharmacs, "ANTIBIOTICOS", "0401", 2, "",
						CCDIValueStatusEnum.ACTIVE);
				em.persist(antiBiotics);
				em.flush();
				antiBiotics = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", antiBiotics.getCode())
						.getSingleResult();

				CCDILevelValue hypodermic = new CCDILevelValue(subFamily,
						needle, "HIPODERMICAS", "010101", 1, "",
						CCDIValueStatusEnum.ACTIVE);
				em.persist(hypodermic);
				em.flush();
				hypodermic = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", hypodermic.getCode())
						.getSingleResult();

				CCDILevelValue puncture = new CCDILevelValue(subFamily, needle,
						"PUNCION", "010102", 1, "", CCDIValueStatusEnum.ACTIVE);
				em.persist(puncture);
				em.flush();
				puncture = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", puncture.getCode())
						.getSingleResult();

				CCDILevelValue insuline = new CCDILevelValue(subFamily,
						syringe, "INSULINA", "010201", 1, "",
						CCDIValueStatusEnum.ACTIVE);
				em.persist(insuline);
				em.flush();
				insuline = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", insuline.getCode())
						.getSingleResult();

				CCDILevelValue penicillin = new CCDILevelValue(subFamily,
						antiBiotics, "PENICILINA", "040101", 1, "",
						CCDIValueStatusEnum.ACTIVE);
				em.persist(penicillin);
				em.flush();
				penicillin = em
						.createNamedQuery("CCDILevelValue.findByCode",
								CCDILevelValue.class)
						.setParameter("code", penicillin.getCode())
						.getSingleResult();

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test ccdi level values",
						e);
			}

		}
	}

	private void ccdiTestData() {
		String query = "SELECT t from CCDIDefinition t WHERE t.id = 1";

		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test ccdiDefinition");

				CCDIDefinition material = new CCDIDefinition("MATERIAL",
						"MATERIAL", 10, 5, CCDIStatusEnum.ACTIVE,
						new Concept(), CCDICodeTypeEnum.ALPHANUMERIC, false, "");
				em.persist(material);

			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test ccdi definition", e);
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
				String names[] = { "Rigoberto", "Angel", "Jorge", "Alejandro",
						"Isaac" };
				String lastNames[] = { "Sanchez", "Lacret", "Fuentes",
						"Sanchez", "Casado" };
				for (int i = 0; i < 5; ++i) {
					Citizen citizen = new Citizen(em.find(LegalEntity.class,
							i + 5L), GenderTypeEnum.MALE);
					citizen.setFirstName(names[i]);
					citizen.setSecondName(names[(i + 1) % 5]);
					citizen.setFirstLastName(lastNames[i]);
					citizen.setSecondLastName(lastNames[(i + 1) % 5]);
					citizen.setIdType(DocumentTypeEnum.LOCAL);
					citizen.setIdNumber("" + i);
					citizen.setPrimaryEmail(names[i] + "@4geeks.co");
					em.persist(citizen);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating Citizen test data : ",
						e1);
			}
		}

	}

	/**
	 * 
	 */
	private void eiaMaintenancePlanificationTestData() {
		// TODO Auto-generated method stub

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
							EiaStateEnum.values()[i % 3], "GHAEQ-00" + i,
							eProvider, "S9023423" + i);
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

	/**
	 * 
	 */
	private void eiaTypeMaintenancePlanTestData() {
		String query = "SELECT t from EiaTypeMaintenancePlan t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: EiaTypeMaintenancePlan");
				em.persist(new EiaTypeMaintenancePlan(em.find(EiaType.class,
						"90001"), em.find(MaintenancePlan.class, 1L)));
				em.persist(new EiaTypeMaintenancePlan(em.find(EiaType.class,
						"90002"), em.find(MaintenancePlan.class, 2L)));
				em.flush();

			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceActivity test data", e1);
			}
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

	/**
	 * 
	 */
	private void facilityTestData() {
		String query = "SELECT t from Facility t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			logger.info("Creating test data : facility");
			String facilityNames[] = { "Sala 1 Rayos X", "Sala 1 Tomografía" };
			for (int i = 3, j = 0; i < 5; ++i, ++j) {
				Facility facility = new Facility();
				facility.setName(facilityNames[j]);
				facility.setBuildingLocation(em.find(BuildingLocation.class,
						"Building 00" + i));
				em.persist(facility);
			}
			em.flush();
		}

	}

	/**
	 * 
	 */
	@PostConstruct
	public void inicializar() {
		try {
			modules();
		} catch (IOException e) {
			e.printStackTrace();
		}
		messageTypes();
		messages();
		uiStrings();
		parameter();
		testData();
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

	private void jobTestData() {
		String query = "SELECT t from Job t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : job");
				Job job = null;

				job = new Job(new Obu(1), new Role(1));
				em.persist(job);
				job = new Job(new Obu(1), new Role(2));
				em.persist(job);
				job = new Job(new Obu(2), new Role(1));
				em.persist(job);
				job = new Job(new Obu(2), new Role(3));
				em.persist(job);

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test data job", e);
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

	private void maintenanceActivityTestData() {
		String query = "SELECT t from MaintenanceActivity t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: maintenance activity");

				String activityNames[] = { "Desconectar", "Abrir", "Limpiar",
						"Cerrar", "Conectar", "Reemplazar",
						"subprotocol_activity", "activity_1", "activity_2",
						"activity_3" };

				String activityDesc[] = {
						"Desconecte el equipo de la corriente eléctrica",
						"Quite los tornillos y levante la tapa del equipo",
						"Limpie cuidadosamente el interior del equipo, sin líquidos",
						"Cierre la tapa del equipo, y coloque los tornillos",
						"Conecte el equipo a la corriente eléctrica, y verifique su funcionamiento",
						"Reemplaze el toner del equipo",
						"actividad de subprotocolo para pruebas",
						"actividad de prueba 1 para la actividad de subprotocolo",
						"actividad de prueba 2 para la actividad de subprotocolo",
						"actividad de prueba 2 para la actividad de subprotocolo" };

				int durations[] = { 1, 2, 2, 1, 4, 3, 5, 6, 8, 7 };

				TimePeriodEnum pots[] = { TimePeriodEnum.DAYS,
						TimePeriodEnum.HOURS, TimePeriodEnum.HOURS,
						TimePeriodEnum.DAYS, TimePeriodEnum.HOURS,
						TimePeriodEnum.HOURS, TimePeriodEnum.DAYS,
						TimePeriodEnum.HOURS, TimePeriodEnum.HOURS,
						TimePeriodEnum.HOURS };

				double cost[] = { 1300.42, 200.0, 300.0, 1000.4, 42.5, 879.2,
						2432.45, 123.0, 1200.0, 573.97 };

				boolean isSubprotocol[] = { false, false, false, false, false,
						false, true, false, false, false };

				for (int i = 0; i < 10; ++i) {
					Activity activity = new Activity();
					activity.setName(activityNames[i]);
					activity.setDescription(activityDesc[i]);
					activity.setState(ActivityState.CREATED);
					activity.setCategory(ActivityCategoryEnum.MAINTENANCE);
					activity.setSubCategory(ActivitySubCategoryEnum.CALIBRATION);
					activity.setEstimatedDuration(new BigDecimal(durations[i]));
					activity.setEstimatedDurationPoT(pots[i]);
					activity.setEstimatedCost(new BigDecimal(cost[i]));
					activity.setEstimatedCostCurrency(CurrencyTypeEnum.BS);
					activity.setIsSubProtocol(isSubprotocol[i]);

					MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
					maintenanceActivity.setActivity(activity);

					em.persist(activity);
					em.persist(maintenanceActivity);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceActivity test data", e1);
			}
		}
	}

	/**
	 * 
	 */
	private void maintenancePlanTestData() {
		String query = "SELECT t from MaintenancePlan t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: maintenance plan");
				String planName[] = { "Plan de Mantenimiento Impresoras Tinta",
						"Plan de Mantenimiento Impresoras Laser" };
				String planDesc[] = {
						"plan de mantenimiento impresoras de tinta",
						"plan de mantenimiento impresoras laser" };
				int planFrequency[] = { 1, 3 };
				TimePeriodEnum planTimePeriod[] = { TimePeriodEnum.MONTHS,
						TimePeriodEnum.SEMESTERS };
				for (int i = 0; i < 2; ++i)
					em.persist(new MaintenancePlan(planName[i], planDesc[i],
							planFrequency[i], planTimePeriod[i],
							MaintenancePlanType.OVERHAUL,
							MaintenancePlanState.ACTIVE,
							MaintenancePlanCancelationOption.UNDEFERRABLE));
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenancePlan test data", e1);
			}
		}
	}

	private void maintenanceProtocolsTestData() {
		String query = "SELECT t from MaintenanceProtocols t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: MaintenanceProtocols");
				List<MaintenancePlan> plans = em.createNamedQuery(
						"MaintenancePlan.getAll", MaintenancePlan.class)
						.getResultList();
				List<MaintenanceActivity> entities = em
						.createNamedQuery("MaintenanceActivity.getAll",
								MaintenanceActivity.class).getResultList();

				for (int i = 0; i < 4; ++i) {
					em.persist(new MaintenanceProtocols(plans.get(0), entities
							.get(i), i + 1));
					em.persist(new MaintenanceProtocols(plans.get(1), entities
							.get(i), i + 1));
				}

				em.persist(new MaintenanceProtocols(plans.get(0), entities
						.get(4), 5));
				em.persist(new MaintenanceProtocols(plans.get(1), entities
						.get(5), 5));

				// subprotocol activity
				em.persist(new MaintenanceProtocols(plans.get(0), entities
						.get(6), 6));

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceProtocols test data", e1);
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
	private void materialCategoryTestData() {
		String query = "SELECT t from MaterialCategory t WHERE t.code = 'mat-cat-000' ";
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

	private void materialTestData() {
		String query = "SELECT t from Material t WHERE t.code= 'material-test-001'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : material");
				String names[] = { "aguja", "sutura", "inyectadora",
						"algodón", "alcohol" };
				int i = 1;
				for (String name : names) {
					Material next = new Material();
					next.setName(name);
					next.setCode("material-test-00" + i);
					next.setDescription(name);
					next.setType(MaterialTypeEnum.values()[i % 3]);
					next.setExternalCode("ex-code-00" + i);
					i++;
					em.persist(next);
				}
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error creating test data: external provider", e);
			}
		}
	}

	private void messages() {

		logger.info("Creating ghamessage data");
		InputStream in = null;
		CSVReader reader = null;
		GHAMessageType defaultType = em.find(GHAMessageType.class, 1l);
		try {
			in = InitialData.class.getResourceAsStream("/messages.csv");
			reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
					'\'', 0);
			List<String[]> readAll = reader.readAll();
			String code, text;
			LanguageEnum lang = null;
			Map<String, Boolean> words = new HashMap<String, Boolean>();
			for (String[] strings : readAll) {
				String language = strings[0];
				if (language.startsWith("#") || language.startsWith("//"))
					continue;
				code = strings[1];
				if (words.containsKey(code + language)) {
					logger.info("Repeated key in ghamessage: " + code);
					continue;
				}
				words.put(code + language, true);
				lang = LanguageEnum.valueOf(strings[0]);
				text = strings[2];
				long type = 1l;
				try {
					type = Long.valueOf(strings[3]);
				} catch (Exception e) {
					logger.info("no type info available in this line... Setting '1' by default");
				}
				try {
					if (type == 1)
						em.merge(new GHAMessage(lang, code, text, defaultType));
					else
						em.merge(new GHAMessage(lang, code, text, em.find(
								GHAMessageType.class, type)));

				} catch (Exception e) {
					logger.log(Level.SEVERE,
							"Error inserting/updating an ghamessage", e);
				}
			}
			em.flush();
			reader.close();
			in.close();
		} catch (IOException e) {
			try {
				reader.close();
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e1);
			}
			try {
				in.close();
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e1);
			}
			logger.log(Level.INFO, "error Reading file ghamessage test data", e);

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e);
			}
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e);
			}
		}
	}

	private void messageTypes() {
		String query = "SELECT t from GHAMessageType t WHERE t.id= 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : message types");
				String names[] = { "SAY", "CONFIRMATION", "ASKYESNO",
						"ERROR_HARD", "ERROR_SOFT", "WARNING", "INFORMATION",
						"FAILURE", "SUCCESS", "PROCESSING", "NEW_MESSAGE" };

				for (String name : names) {
					GHAMessageType next = new GHAMessageType();
					if (name.equals("SAY")) {
						// Gray
						next = new GHAMessageType(name, true, false);
					} else if (name.equals("CONFIRMATION")) {
						// Gray
						next = new GHAMessageType(name, false, true);
					} else if (name.equals("ASKYESNO")) {
						// Gray
						next = new GHAMessageType(name, false, true);
					} else if (name.equals("ERROR_HARD")) {
						// Red
						next = new GHAMessageType(name, false, true);
					} else if (name.equals("ERROR_SOFT")) {
						// Yellow
						next = new GHAMessageType(name, false, false);
					} else if (name.equals("WARNING")) {
						// Blue
						next = new GHAMessageType(name, false, false);
					} else if (name.equals("INFORMATION")) {
						// Blue
						next = new GHAMessageType(name, true, false);
					} else if (name.equals("FAILURE")) {
						// Yellow
						next = new GHAMessageType(name, false, false);
					} else if (name.equals("SUCCESS")) {
						// Green
						next = new GHAMessageType(name, true, false);
					} else if (name.equals("PROCESSING")) {
						// Green
						next = new GHAMessageType(name, false, false);
					} else if (name.equals("NEW_MESSAGE")) {
						// Gray
						next = new GHAMessageType(name, true, false);
					}
					em.persist(next);
				}
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error creating test data: Message Types", e);
			}
		}
	}

	private void modules() throws IOException {
		InputStream resourceAsStream = InitialData.class
				.getResourceAsStream("/codes.csv");
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new InputStreamReader(resourceAsStream,
					"UTF-8"), ',', '\'', 1);
			List<String[]> readAll = csvReader.readAll();
			Module module = null;
			AppForm appForm = null;
			View view = null;
			AppFormView appFormView = null;
			Function function = null;
			AppFormViewFunction appFormViewFunction = null;

			for (String[] strings : readAll) {
				String moduleName = strings[0];
				String moduleCode = strings[1];
				module = new Module(moduleName, moduleCode);
				em.merge(module);
				String appFormName = strings[2];
				String appFormToken = strings[3];
				String appFormCode = strings[4];
				appForm = new AppForm(module, appFormName, appFormToken,
						appFormCode);
				em.merge(appForm);
				String viewName = strings[5];
				String viewCode = strings[6];
				String viewDescription = strings[7];
				view = new View(viewCode, viewName, viewDescription);
				em.merge(view);
				appFormView = new AppFormView(appForm, view);
				em.merge(appFormView);
				String functionName = strings[8];
				String functionCode = strings[9];
				String functionDescription = strings[10];
				function = new Function(functionCode, functionName,
						functionDescription);
				em.merge(function);
				appFormViewFunction = new AppFormViewFunction(appForm, view,
						function);
				em.merge(appFormViewFunction);
			}
			csvReader.close();
		} catch (UnsupportedEncodingException e3) {
			csvReader.close();
			logger.log(
					Level.SEVERE,
					"Error loading modules, screens, views and functions: incorrect file encoding",
					e3);
		} finally {
			if (csvReader != null)
				csvReader.close();
		}
		resourceAsStream.close();
	}

	private void obuTestData() {
		String query = "SELECT t from Obu t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : obu");
				String obuNames[] = { "Administración", "Medicina General",
						"Dpto. de Nefrologia" };
				Obu obu = null;
				for (int i = 0; i < 3; i++) {
					obu = new Obu();
					obu.setName(obuNames[i]);
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

	private void parameter() {
		String query = "SELECT t FROM Parameter t WHERE t.code = 'P1'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating parameter test data");
				Parameter parameter = new Parameter("P1", "Lenguaje");
				em.persist(parameter);
				ParameterGroup parameterGroup = new ParameterGroup(
						"Valores por defecto");
				em.persist(parameterGroup);
				em.persist(new ParameterValue(parameterGroup, parameter,
						LanguageEnum.ES.name()));
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error Creating parameter test data", e1);
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

	private void ssoUserTestData() {
		String query = "SELECT t from SSOUser t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data: users");
				em.persist(new SSOUser(em.find(Bpu.class, 1L), "admin",
						"admin", UserLogonStatusEnum.STAYIN));
				em.persist(new SSOUser(em.find(Bpu.class, 2L), "asanchez",
						"asanchez", UserLogonStatusEnum.STAYIN));
				em.persist(new SSOUser(em.find(Bpu.class, 3L), "gha", "gha",
						UserLogonStatusEnum.STAYIN));
				em.flush();
				logger.info("done creating test users");
			} catch (Exception e1) {
				logger.log(Level.INFO, "error test data ssouser");
			}
		}
	}

	private void subProtocolAndChecklistTestData() {
		String query = "SELECT t from SubProtocolAndChecklist t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: ProtocolsAndChecklist");
				MaintenanceActivity parentActivity = em.find(
						MaintenanceActivity.class, Long.valueOf(7));
				List<MaintenanceActivity> activities = em
						.createNamedQuery("MaintenanceActivity.getAll",
								MaintenanceActivity.class).getResultList();

				em.persist(new SubProtocolAndChecklist(parentActivity
						.getActivity(), activities.get(7).getActivity(), 1));
				em.persist(new SubProtocolAndChecklist(parentActivity
						.getActivity(), activities.get(8).getActivity(), 2));
				em.persist(new SubProtocolAndChecklist(parentActivity
						.getActivity(), activities.get(9).getActivity(), 3));

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating SubProtocolAndChecklist test data", e1);
			}
		}
	}

	private void testData() {
		ccdiTestData();
		ccdiLevelDefinitionTestData();
		ccdiLevelValuesTestData();

		legalEntityTestData();
		institutionTestData();
		citizenTestData();
		workingAreaTestData();
		//
		bpiTestData();
		bpuTestData();
		// //
		bpuFunctionTestData();
		//
		obuTestData();
		roleTestData();
		buildingLocationsTestData();
		bspTestData();
		jobTestData();

		//
		ssoUserTestData();
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
		//
		maintenanceActivityTestData();
		subProtocolAndChecklistTestData();
		maintenancePlanTestData();
		maintenanceProtocolsTestData();
		// maintenanceProtocolTestData();
		// maintenanceActivityProtocolTestData();
		// MaintenancePlanMaintenanceProtocol();
		// eiaTypeMaintenancePlanTestData();
		// eiaMaintenancePlanificationTestData();
	}

	private void uiStrings() {
		logger.info("Creating uistrings data");
		InputStream in = null;
		CSVReader reader = null;
		try {
			in = InitialData.class.getResourceAsStream("/uistrings.csv");
			reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
					'\'', 0);
			List<String[]> readAll = reader.readAll();
			String code, text;
			LanguageEnum lang = null;
			Map<String, Boolean> words = new HashMap<String, Boolean>();
			for (String[] strings : readAll) {
				String language = strings[0];
				if (language.startsWith("#") || language.startsWith("//"))
					continue;
				code = strings[1];
				if (words.containsKey(code + language)) {
					logger.info("Repeated key in uistrings: " + code);
					continue;
				}
				words.put(code + language, true);
				lang = LanguageEnum.valueOf(strings[0]);
				text = strings[2];
				try {
					em.merge(new UiString(lang, code, text));
				} catch (Exception e) {
					logger.log(Level.SEVERE,
							"Error inserting/updating an uistring", e);
				}
			}
			em.flush();
			reader.close();
			in.close();
		} catch (IOException e) {
			try {
				reader.close();
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e1);
			}
			try {
				in.close();
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e1);
			}
			logger.log(Level.INFO, "error Reading file uistrings test data", e);

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e);
			}
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e);
			}
		}

	}

	private void workingAreaTestData() {
		String query = "SELECT t from WorkingArea t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			logger.info("Creating test data : WorkingArea");
			String workingAreaNames[] = { "Enfermería Emergencia",
					"Enfermería U.C.I", "Enfermería Pediatría" };
			for (int i = 0; i < 3; ++i) {
				WorkingArea entity = new WorkingArea();
				entity.setBuildingLocation(em.find(BuildingLocation.class,
						"Building 00" + i));
				entity.setName(workingAreaNames[i]);
				em.persist(entity);
			}
			em.flush();
		}
	}

}
