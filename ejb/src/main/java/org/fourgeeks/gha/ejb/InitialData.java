package org.fourgeeks.gha.ejb;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.TimerParams;
import org.fourgeeks.gha.domain.TransactionParams;
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
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.auth.Function;
import org.fourgeeks.gha.domain.ess.auth.FunctionBpu;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.ess.ui.App;
import org.fourgeeks.gha.domain.ess.ui.AppView;
import org.fourgeeks.gha.domain.ess.ui.Module;
import org.fourgeeks.gha.domain.ess.ui.View;
import org.fourgeeks.gha.domain.ess.ui.ViewFunction;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
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
import org.fourgeeks.gha.ejb.ess.ui.ViewFunctionServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;

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
	ViewFunctionServiceRemote permissionService;

	@EJB(lookup = "java:global/ear-1/ejb-1/CCDIService!org.fourgeeks.gha.ejb.gom.CCDIServiceRemote")
	CCDIServiceRemote ccdiServiceRemote;

	private void bpiTestData() {
		final String query = "SELECT t from Bpi t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test data : bpi");
				Bpi bpi = new Bpi();
				bpi.setInstitution(em.find(Institution.class, 1L));
				em.persist(bpi);

				bpi = new Bpi();
				bpi.setInstitution(em.find(Institution.class, 2L));
				em.persist(bpi);

				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test data bpi", e);
			}
		}
	}

	/**
	 * 
	 */
	private void bpuFunctionTestData() {
		try {
			logger.info("Creating bpuPermission test data");
			final List<ViewFunction> all = permissionService.getAll();
			final Bpu admin = em.find(Bpu.class, 1L);
			final Bpu gha = em.find(Bpu.class, 3L);
			for (final ViewFunction permission : all) {
				em.merge(new FunctionBpu(admin, permission.getFunction()));
				em.merge(new FunctionBpu(gha, permission.getFunction()));
			}
		} catch (final Exception e1) {
			logger.log(Level.INFO, "error Creating bpupermission test data", e1);
		}
	}

	/**
	 * 
	 */
	private void bpuTestData() {
		final String query = "SELECT t FROM Bpu t WHERE t.id = '1'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating bpu test data");
				for (int i = 0; i < 5; ++i)
					em.persist(new Bpu(em.find(Bpi.class, 1L), em.find(
							Citizen.class, i + 1L)));
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error Creating bpu test data", e1);
			}
		}
	}

	/**
	 * 
	 */
	private void brandTestData() {
		try {
			final String query = "SELECT e from Brand e WHERE e.id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (final NoResultException e) {
				logger.log(Level.INFO, "creating test brands");

				final String brandNames[] = new String[] { "HP", "Epson",
						"Compaq", "Dell", "Canon" };
				final List<Manufacturer> mans = em.createNamedQuery(
						"Manufacturer.getAll", Manufacturer.class)
						.getResultList();
				int k = 0;
				for (final String brandName : brandNames) {
					final Brand next = new Brand();
					next.setName(brandName);
					next.setManufacturer(mans.get(k++));
					em.persist(next);
					em.flush();
				}
			}
		} catch (final Exception e) {
			logger.log(Level.INFO, "error creating test brands", e);
		}

	}

	/**
	 * 
	 */
	private void bspTestData() {
		final String query = "SELECT t from Bsp t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test data : bsp");
				for (int i = 0; i < 2; i++) {
					final Bsp bsp = new Bsp();
					bsp.setObu(new Obu(i + 1));
					em.persist(bsp);
				}
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test data bsp", e);
			}
		}
	}

	private void buildingLocationsTestData() {
		final String query = "SELECT t from BuildingLocation t WHERE t.code='Building 000'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("creating eia building locations");

				for (int i = 0; i < 5; i++) {
					final BuildingLocation buildingLocation = new BuildingLocation(
							em.find(Bpi.class, 1L), "Building 00" + i,
							LocationLevelEnum.BUILDING,
							"Building Location Name " + i);
					em.persist(buildingLocation);
				}
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test building location",
						e);
			}
		}
	}

	/**
	 * 
	 *
	 */
	private void ccdiLevelDefinitionTestData() {
		InputStream in = null;
		CSVReader reader = null;

		final String query = "SELECT COUNT(t) from CCDILevelDefinition t";
		try {
			final int count = ((Number) em.createQuery(query).getSingleResult())
					.intValue();
			if (count <= 0)
				throw new NoResultException();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test ccdiLevelDefinition");
				in = InitialData.class
						.getResourceAsStream("/ccdiLevelDefinition.csv");
				reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
						'\'', 0);
				final List<String[]> readAll = reader.readAll();

				for (final String[] strings : readAll) {
					if (strings[0].startsWith("#")
							|| strings[0].startsWith("//"))
						continue;
					final CCDIDefinition definition = em
							.createNamedQuery("CCDIDefinition.findByCode",
									CCDIDefinition.class)
							.setParameter("code", strings[0]).getSingleResult();

					final CCDILevelDefinition levelDefinition = new CCDILevelDefinition();
					levelDefinition.setDefinition(definition);
					levelDefinition.setLevel(Integer.parseInt(strings[1]));
					levelDefinition.setName(strings[2]);
					levelDefinition.setLength(Integer.parseInt(strings[3]));
					levelDefinition
							.setValueType(CCDIValueTypeEnum.values()[Integer
									.parseInt(strings[4])]);
					levelDefinition.setInitialValue(Integer
							.parseInt(strings[5]));
					levelDefinition.setIncValue(Integer.parseInt(strings[6]));
					levelDefinition.setSeparator(strings[7]);
					levelDefinition.setValueAtEndAction(CCDIEndValueActionEnum
							.values()[Integer.parseInt(strings[8])]);

					em.persist(levelDefinition);
					em.flush();
				}

			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error creating test ccdi level definition", e1);
			}
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN CCDI LEVEL DEFINITION");
			}

			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN CCDI LEVEL DEFINITION");
			}
		}
	}

	/**
	 * 
	 */
	private void ccdiLevelValuesTestData() {
		InputStream in = null;
		CSVReader reader = null;

		final String query = "SELECT COUNT(t) from CCDILevelValue t";
		try {
			final int count = ((Number) em.createQuery(query).getSingleResult())
					.intValue();
			if (count <= 0)
				throw new NoResultException();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test ccdiLevelValue");
				in = InitialData.class
						.getResourceAsStream("/ccdiLevelValue.csv");
				reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
						'\'', 0);
				final List<String[]> readAll = reader.readAll();

				for (final String[] strings : readAll) {
					if (strings[0].startsWith("#")
							|| strings[0].startsWith("//"))
						continue;

					final CCDIDefinition definition = em.find(
							CCDIDefinition.class, strings[0]);
					final CCDILevelDefinition levelDefinition = em
							.createNamedQuery(
									"CCDILevelDefinition.findByLevel",
									CCDILevelDefinition.class)
							.setParameter("definition", definition)
							.setParameter("level", Integer.parseInt(strings[1]))
							.getSingleResult();

					final CCDILevelValue levelValue = new CCDILevelValue();
					levelValue.setLevelDefinition(levelDefinition);

					final CCDILevelValue parentValue = strings[2].equals("") ? null
							: em.find(CCDILevelValue.class, strings[2]);
					levelValue.setParentValue(parentValue);
					levelValue.setName(strings[3]);
					levelValue.setCode(strings[4]);
					levelValue.setNextValue(Integer.parseInt(strings[5]));
					levelValue.setFixedValue(strings[6]);
					levelValue.setStatus(CCDIValueStatusEnum.values()[Integer
							.parseInt(strings[7])]);
					levelValue.setNextElement(Integer.parseInt(strings[8]));

					em.persist(levelValue);
					em.flush();
				}

			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test ccdi level value",
						e1);
			}
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN CCDI LEVEL VALUE");
			}

			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN CCDI LEVEL VALUE");
			}
		}
	}

	private void ccdiTestData() {
		InputStream in = null;
		CSVReader reader = null;

		final String query = "SELECT COUNT(t) from CCDIDefinition t";
		try {
			final int count = ((Number) em.createQuery(query).getSingleResult())
					.intValue();
			if (count <= 0)
				throw new NoResultException();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test ccdiDefinition");
				in = InitialData.class
						.getResourceAsStream("/ccdiDefinition.csv");
				reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
						'\'', 0);
				final List<String[]> readAll = reader.readAll();

				for (final String[] strings : readAll) {
					if (strings[0].startsWith("#")
							|| strings[0].startsWith("//"))
						continue;

					final CCDIDefinition definition = new CCDIDefinition();
					definition.setCode(strings[0]);
					definition.setName(strings[1]);
					definition.setLength(Integer.parseInt(strings[2]));
					definition.setLevels(Integer.parseInt(strings[3]));
					definition.setStatus(CCDIStatusEnum.values()[Integer
							.parseInt(strings[4])]);
					definition.setConcept(em.find(Concept.class,
							Long.parseLong(strings[5])));
					definition.setType(CCDICodeTypeEnum.values()[Integer
							.parseInt(strings[6])]);
					definition
							.setVerification(Boolean.parseBoolean(strings[7]));
					definition.setVerificationMethod(strings[8]);

					em.persist(definition);
					em.flush();
				}

			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test ccdi definition", e);
			}
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN CCDI DEFINITION");
			}

			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN CCDI DEFINITION");
			}
		}
	}

	/**
	 * 
	 */
	private void citizenTestData() {
		final String query = "SELECT t FROM Citizen t WHERE t.id = '1'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating Citizen test data");
				final String names[] = { "Rigoberto", "Angel", "Jorge",
						"Alejandro", "Isaac" };
				final String lastNames[] = { "Sanchez", "Lacret", "Fuentes",
						"Sanchez", "Casado" };
				for (int i = 0; i < 5; ++i) {
					final Citizen citizen = new Citizen(em.find(
							LegalEntity.class, i + 5L), GenderTypeEnum.MALE);
					citizen.setFirstName(names[i]);
					citizen.setSecondName(names[(i + 1) % 5]);
					citizen.setFirstLastName(lastNames[i]);
					citizen.setSecondLastName(lastNames[(i + 1) % 5]);
					citizen.setIdType(DocumentTypeEnum.LOCAL);
					final SimpleDateFormat sdf = new SimpleDateFormat(
							"dd/MM/yyyy");
					final java.util.Date bd = sdf.parse("25/05/1987");
					citizen.setBirthDate(new Date(bd.getTime()));
					citizen.setIdNumber("" + i);
					citizen.setPrimaryEmail(names[i] + "@4geeks.co");
					em.persist(citizen);
				}
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating Citizen test data : ",
						e1);
			}
		}

	}

	private void eiaTestData() {
		final String query = "SELECT t from Eia t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test eia");

				final Facility facility = em.find(Facility.class, 1L);
				final Obu obu = em.find(Obu.class, 1L);
				final ExternalProvider eProvider = em.find(
						ExternalProvider.class, 1L);
				final Bsp mProvider = em.find(Bsp.class, 1L);
				final Role bRole = em.find(Role.class, 1L);

				for (int i = 1; i < 4; ++i) {
					final Eia eia = new Eia(bRole, em.find(EiaType.class,
							"3XXXXX000" + Long.toString(i)), obu,
							EiaStateEnum.values()[i % 3], "GHAEQ-00" + i,
							mProvider, "S9023423" + i);
					eia.setCode("eia-00" + i);
					eia.setFacility(facility);
					eia.setProvider(eProvider);
					em.persist(eia);
					em.flush();
				}

			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test eia", e1);
			}
		}
	}

	private void eiaTypeCategoryTestData() {
		final String query = "SELECT COUNT(t) from EiaTypeCategory t";
		try {
			final int count = ((Number) em.createQuery(query).getSingleResult())
					.intValue();
			if (count <= 0)
				throw new NoResultException();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating test data: EiaTypeCategory");
				final List<CCDILevelValue> ccdiCategories = em
						.createNamedQuery(
								"CCDILevelValue.findAllByDefinitionCode",
								CCDILevelValue.class)
						.setParameter("code", "Equipos").getResultList();
				for (final CCDILevelValue ccdi : ccdiCategories) {
					if (!ccdi.getLevelDefinition().getDefinition().getCode()
							.equals("Equipos"))
						continue;
					final EiaTypeCategory category = new EiaTypeCategory();
					category.setName(ccdi.getName());
					category.setCode(ccdi.getCode());
					em.persist(category);
					em.flush();
				}

				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error Creating EiaTypeCategory test data", e1);
			}
		}
	}

	private void eiaTypeTestData() {
		InputStream in = null;
		CSVReader reader = null;

		final String query = "SELECT COUNT(t) from EiaType t";
		try {
			int count = ((Number) em.createQuery(query).getSingleResult())
					.intValue();
			if (count <= 0)
				throw new NoResultException();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test eiatype");
				in = InitialData.class.getResourceAsStream("/eiatype.csv");
				reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
						'\'', 0);
				final List<String[]> readAll = reader.readAll();

				for (final String[] strings : readAll) {
					if (strings[0].startsWith("#")
							|| strings[0].startsWith("//"))
						continue;
					final EiaType eiaType = new EiaType();
					eiaType.setBrand(em.find(Brand.class,
							Long.parseLong(strings[0])));
					eiaType.setName(strings[1]);
					eiaType.setMobility(EiaMobilityEnum.values()[Integer
							.parseInt(strings[2])]);
					eiaType.setEiaTypeCategory(em.find(EiaTypeCategory.class,
							strings[3]));
					eiaType.setCode(ccdiServiceRemote
							.getNextElementCode(eiaType.getEiaTypeCategory()
									.getCode()));

					eiaType.setSubtype(EiaSubTypeEnum.values()[Integer
							.parseInt(strings[4])]);
					eiaType.setModel(strings[5]);
					em.persist(eiaType);
					em.flush();
				}

			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating eiatype test data", e1);
			}
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN eiatype test data");
			}

			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN eiatype test data");
			}
		}
	}

	private void externalProviderTestData() {
		final String query = "SELECT t from ExternalProvider t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
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
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error creating test data: external provider", e);
			}
		}
	}

	/**
	 * 
	 */
	private void facilityTestData() {
		final String query = "SELECT t from Facility t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			logger.info("Creating test data : facility");
			final String facilityNames[] = { "Sala 1 Rayos X",
					"Sala 1 Tomografia" };
			for (int i = 3, j = 0; i < 5; ++i, ++j) {
				final Facility facility = new Facility();
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
		} catch (final IOException e) {
			e.printStackTrace();
		}
		messageTypes();
		messages();
		uiStrings();
		parameter();
		testData();
	}

	private void institutionTestData() {
		final String query = "SELECT t from Institution t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
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
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test data institution",
						e1);
			}
		}
	}

	private void jobTestData() {
		final String query = "SELECT t from Job t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
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
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test data job", e);
			}
		}
	}

	private void legalEntityTestData() {
		final String query = "SELECT t from LegalEntity t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test data : legal entity ");
				for (int i = 0; i < 5; i++)
					em.persist(new LegalEntity("J-000" + i));

				for (int i = 5; i < 10; i++)
					em.persist(new LegalEntity("V-000" + i));

				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test data legal entity",
						e);
			}
		}
	}

	private void activityTypeAndSubTypeTestData() {
		final String query = "SELECT t FROM ActivityType t";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating test data: activity type and subtype");

				final String activityTypeNames[] = { "Mantenimiento",
						"Asistencial", "Logística", "Operaciones",
						"Administrativa", "Del Sistema" };

				HashMap<String, String[]> map = new HashMap<String, String[]>();

				map.put("Mantenimiento", new String[] { "Medición", "Limpieza",
						"Calibración", "Desarme", "Armado", "Instalación",
						"Desinstalación", "Cambio de Repuesto",
						"Cambio de Consumibles", "Aceptación Mantenimiento",
						"Traslado" });

				map.put("Asistencial", new String[] { "Asistencia Paciente",
						"Bañado de paciente", "Consulta",
						"Recepción de Paciente", "Acompañar al Paciente",
						"Hacer procedimiento", "calificación del Paciente",
						"Pruebas Diagnósticas", "Medicación",
						"Estudios Diagnósticos", "Tratamientos",
						"Procedimientos" });

				map.put("Logística", new String[] { "Dar Cita",
						"Despacho de Materiales", "Mantenimiento Habitación" });

				map.put("Administrativa", new String[] { "Admisión Paciente",
						"Egreso Paciente", "Facturación" });

				for (String typeName : activityTypeNames) {
					ActivityType type = new ActivityType();
					type.setDescription(typeName);
					em.persist(type);

					String[] subtypeNames = map.get(typeName);
					for (String subTypeName : subtypeNames) {
						final ActivityType subType = new ActivityType();
						subType.setDescription(subTypeName);
						subType.setParentActivityTypeId(type.getId());
						em.persist(subType);
					}
				}

				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceActivity test data", e1);
			}
		}
	}

	private void maintenanceActivityTestData() {
		final String query = "SELECT t from MaintenanceActivity t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating test data: maintenance activity");

				final String activityNames[] = { "Desconectar", "Abrir",
						"Limpiar", "Cerrar", "Conectar", "Reemplazar",
						"subprotocol_activity", "activity_1", "activity_2",
						"activity_3" };

				final String activityDesc[] = {
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

				final int durations[] = { 1, 2, 2, 1, 4, 3, 5, 6, 8, 7 };

				final TimePeriodEnum pots[] = { TimePeriodEnum.DAYS,
						TimePeriodEnum.HOURS, TimePeriodEnum.HOURS,
						TimePeriodEnum.DAYS, TimePeriodEnum.HOURS,
						TimePeriodEnum.HOURS, TimePeriodEnum.DAYS,
						TimePeriodEnum.HOURS, TimePeriodEnum.HOURS,
						TimePeriodEnum.HOURS };

				final double cost[] = { 1300.42, 200.0, 300.0, 1000.4, 42.5,
						879.2, 2432.45, 123.0, 1200.0, 573.97 };

				final boolean isSubprotocol[] = { false, false, false, false,
						false, false, true, false, false, false };

				for (int i = 0; i < 10; ++i) {
					final Activity activity = new Activity();
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

					final MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
					maintenanceActivity.setActivity(activity);

					em.persist(activity);
					em.persist(maintenanceActivity);
				}
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceActivity test data", e1);
			}
		}
	}

	/**
	 * 
	 */
	private void maintenancePlanTestData() {
		final String query = "SELECT t from MaintenancePlan t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating test data: maintenance plan");
				final String planName[] = {
						"Plan de Mantenimiento Impresoras Tinta",
						"Plan de Mantenimiento Impresoras Laser" };
				final String planDesc[] = {
						"plan de mantenimiento impresoras de tinta",
						"plan de mantenimiento impresoras laser" };
				final int planFrequency[] = { 1, 3 };
				final TimePeriodEnum planTimePeriod[] = {
						TimePeriodEnum.MONTHS, TimePeriodEnum.SEMESTERS };
				for (int i = 0; i < 2; ++i)
					em.persist(new MaintenancePlan(planName[i], planDesc[i],
							planFrequency[i], planTimePeriod[i],
							MaintenancePlanType.OVERHAUL,
							MaintenancePlanState.ACTIVE,
							MaintenancePlanCancelationOption.UNDEFERRABLE));
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenancePlan test data", e1);
			}
		}
	}

	private void maintenanceProtocolsTestData() {
		final String query = "SELECT t from MaintenanceProtocol t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating test data: MaintenanceProtocols");
				final List<MaintenancePlan> plans = em.createNamedQuery(
						"MaintenancePlan.getAll", MaintenancePlan.class)
						.getResultList();
				final List<MaintenanceActivity> entities = em
						.createNamedQuery("MaintenanceActivity.getAll",
								MaintenanceActivity.class).getResultList();

				for (int i = 0; i < 4; ++i) {
					em.persist(new MaintenanceProtocol(plans.get(0), entities
							.get(i), i + 1));
					em.persist(new MaintenanceProtocol(plans.get(1), entities
							.get(i), i + 1));
				}

				em.persist(new MaintenanceProtocol(plans.get(0), entities
						.get(4), 5));
				em.persist(new MaintenanceProtocol(plans.get(1), entities
						.get(5), 5));

				// subprotocol activity
				em.persist(new MaintenanceProtocol(plans.get(0), entities
						.get(6), 6));

				em.flush();
			} catch (final Exception e1) {
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
			final String query = "SELECT e from Manufacturer e WHERE e.id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (final NoResultException e) {
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
		} catch (final Exception e) {
			logger.log(Level.INFO, "error creating test manufacturers", e);
		}

	}

	/**
	 * 
	 */
	private void materialCategoryTestData() {
		final String query = "SELECT COUNT(t) from MaterialCategory t";
		try {
			int count = ((Number) em.createQuery(query).getSingleResult())
					.intValue();
			if (count <= 0)
				throw new NoResultException();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating test data: MaterialCategory");
				final List<CCDILevelValue> ccdiCategories = em
						.createNamedQuery(
								"CCDILevelValue.findAllByDefinitionCode",
								CCDILevelValue.class)
						.setParameter("code", "Material").getResultList();
				for (final CCDILevelValue ccdi : ccdiCategories) {
					if (!ccdi.getLevelDefinition().getDefinition().getCode()
							.equals("Material"))
						continue;
					final MaterialCategory category = new MaterialCategory();
					category.setName(ccdi.getName());
					category.setCode(ccdi.getCode());
					em.persist(category);
					em.flush();
				}

				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error Creating Material Category test data", e1);
			}
		}
	}

	private void materialTestData() {
		InputStream in = null;
		CSVReader reader = null;

		final String query = "SELECT COUNT(t) from Material t";
		try {
			int count = ((Number) em.createQuery(query).getSingleResult())
					.intValue();
			if (count <= 0)
				throw new NoResultException();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test material");
				in = InitialData.class.getResourceAsStream("/material.csv");
				reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
						'\'', 0);
				final List<String[]> readAll = reader.readAll();

				for (final String[] strings : readAll) {
					if (strings[0].startsWith("#")
							|| strings[0].startsWith("//"))
						continue;
					Material material = new Material();
					material.setType(MaterialTypeEnum.values()[Integer
							.parseInt(strings[0])]);
					material.setMaterialCategory(em.find(
							MaterialCategory.class, strings[1]));
					material.setDescription(strings[2]);
					material.setName(strings[3]);
					material.setExternalCode(strings[4]);
					material.setModel(strings[5]);
					material.setBarCode(strings[6]);
					material.setCode(ccdiServiceRemote
							.getNextElementCode(material.getMaterialCategory()
									.getCode()));

					em.persist(material);
					em.flush();
					material = em.find(Material.class, material.getCode());

					MaterialBrand materialBrand = new MaterialBrand();
					materialBrand.setMaterial(material);
					materialBrand.setBrand(em.find(Brand.class,
							Long.parseLong(strings[7])));
					materialBrand.setAmount(Integer.parseInt(strings[8]));

					em.persist(materialBrand);
					em.flush();
				}

			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating eiatype test data", e1);
			}
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN eiatype test data");
			}

			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR IN eiatype test data");
			}
		}
	}

	private void messages() {

		logger.info("Creating ghamessage data");
		InputStream in = null;
		CSVReader reader = null;
		try {
			// Open csv reading buffers
			in = InitialData.class.getResourceAsStream("/messages.csv");
			reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
					'\'', 0);

			// Read CSV
			final List<String[]> readAll = reader.readAll();
			String code, text;
			String type;
			LanguageEnum lang = null;
			final Map<String, Boolean> words = new HashMap<String, Boolean>();
			for (final String[] strings : readAll) {
				final String language = strings[0];
				if (language.startsWith("#") || language.startsWith("//"))
					continue;
				code = strings[1];
				if (words.containsKey(code + language)) {
					logger.info("Repeated key in ghamessage: " + code);
					continue;
				}
				words.put(code + language, true);
				lang = LanguageEnum.valueOf(language);

				text = strings[2];
				type = "SAY";
				try {
					type = String.valueOf(strings[3]);
				} catch (final Exception e) {
					logger.info("no type info available in this line... Setting 'SAY' by default");
				}

				try {
					em.merge(new GHAMessage(lang, code, text, "", em.find(
							GHAMessageType.class, type), -1));
				} catch (final Exception e) {
					logger.log(Level.SEVERE,
							"Error inserting/updating a ghamessage of type "
									+ type, e);
				}
			}

			// Close csv reading buffers
			em.flush();
			reader.close();
			in.close();
		} catch (final IOException e) {
			try {
				reader.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e1);
			}
			try {
				in.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e1);
			}
			logger.log(Level.INFO, "error Reading file ghamessage test data", e);

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e);
			}
			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in ghamessage", e);
			}
		}
	}

	private void messageTypes() {
		final String query = "SELECT t from GHAMessageType t WHERE t.code= 'SAY'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test data : message types");
				em.persist(new GHAMessageType("SAY", 4, false));
				em.persist(new GHAMessageType("CONFIRMATION", 0, true));
				em.persist(new GHAMessageType("ASKYESNO", 0, true));
				em.persist(new GHAMessageType("ERROR-HARD", 0, true));
				em.persist(new GHAMessageType("ERROR-SOFT", 0, false));
				em.persist(new GHAMessageType("WARNING", 4, false));
				em.persist(new GHAMessageType("INFORMATION", 4, false));
				em.persist(new GHAMessageType("FAILURE", 4, false));
				em.persist(new GHAMessageType("SUCCESS", 4, false));
				em.persist(new GHAMessageType("PROCESSING", 0, false));
				em.persist(new GHAMessageType("NEW_MESSAGE", 0, false));
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error creating test data: Message Types", e);
			}
		}
	}

	private void modules() throws IOException {
		final InputStream resourceAsStream = InitialData.class
				.getResourceAsStream("/codes.csv");
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new InputStreamReader(resourceAsStream,
					"UTF-8"), ',', '\'', 1);
			final List<String[]> readAll = csvReader.readAll();
			Module module = null;
			App app = null;
			View view = null;
			AppView appView = null;
			Function permission = null;
			ViewFunction ViewPermission = null;

			for (final String[] strings : readAll) {
				if (strings[0].startsWith("#") || strings[0].startsWith("//"))
					continue;
				final String moduleCode = strings[0];
				module = new Module(moduleCode, null);
				em.merge(module);
				final String appCode = strings[1];
				final String appToken = strings[2];
				final String name = appCode;
				app = new App(module, name, appCode, appToken);
				em.merge(app);
				final String viewCode = strings[3];
				final String viewDescription = strings[4];
				view = new View(viewCode, null, viewDescription);
				em.merge(view);
				appView = new AppView(app, view);
				em.merge(appView);
				final String permissionCode = strings[5];
				final String functionDescription = strings[6];
				permission = new Function(permissionCode, null,
						functionDescription);
				em.merge(permission);
				ViewPermission = new ViewFunction(view, permission);
				em.merge(ViewPermission);
			}
			csvReader.close();
		} catch (final UnsupportedEncodingException e3) {
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
		final String query = "SELECT t from Obu t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("creating test data : obu");
				final String obuNames[] = { "Administración",
						"Medicina General", "Dpto. de Nefrologia" };
				Obu obu = null;
				for (int i = 0; i < 3; i++) {
					obu = new Obu();
					obu.setName(obuNames[i]);
					obu.setCode("Test code " + i);
					obu.setBpi(em.find(Bpi.class, 1L));
					em.persist(obu);
				}
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test data obu", e);
			}
		}
	}

	private void parameter() {
		final String query = "SELECT t FROM Parameter t WHERE t.code = 'P1'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating parameter test data");
				final Parameter parameter = new Parameter("P1", "Lenguaje");
				em.persist(parameter);
				final ParameterGroup parameterGroup = new ParameterGroup(
						"Valores por defecto");
				em.persist(parameterGroup);
				em.persist(new ParameterValue(parameterGroup, parameter,
						LanguageEnum.ES.name()));
				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error Creating parameter test data", e1);
			}
		}
	}

	private void roleTestData() {
		final String query = "SELECT t from Role t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
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
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error creating test data role", e);
			}
		}
	}

	private void ssoUserTestData() {
		final String query = "SELECT t from SSOUser t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
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
			} catch (final Exception e1) {
				logger.log(Level.INFO, "error test data ssouser");
			}
		}
	}

	private void subProtocolAndChecklistTestData() {
		final String query = "SELECT t from SubProtocolAndChecklist t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			try {
				logger.info("Creating test data: ProtocolsAndChecklist");
				final MaintenanceActivity parentActivity = em.find(
						MaintenanceActivity.class, Long.valueOf(7));
				final List<MaintenanceActivity> activities = em
						.createNamedQuery("MaintenanceActivity.getAll",
								MaintenanceActivity.class).getResultList();

				em.persist(new SubProtocolAndChecklist(parentActivity
						.getActivity(), activities.get(7).getActivity(), 1));
				em.persist(new SubProtocolAndChecklist(parentActivity
						.getActivity(), activities.get(8).getActivity(), 2));
				em.persist(new SubProtocolAndChecklist(parentActivity
						.getActivity(), activities.get(9).getActivity(), 3));

				em.flush();
			} catch (final Exception e1) {
				logger.log(Level.INFO,
						"error Creating SubProtocolAndChecklist test data", e1);
			}
		}
	}

	private void testData() {
		transactionParamsTestData();
		timerParamsTestData();

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
		// //
		eiaTypeCategoryTestData();
		eiaTypeTestData();
		eiaTestData();
		//
		activityTypeAndSubTypeTestData();
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

	private void timerParamsTestData() {
		InputStream in = null;
		CSVReader reader = null;

		try {
			logger.info("creating TimerParams test data");
			in = InitialData.class.getResourceAsStream("/timerParams.csv");
			reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',');
			final List<String[]> readAll = reader.readAll();
			final Map<String, Boolean> words = new HashMap<String, Boolean>();

			for (final String[] strings : readAll) {
				final String code = strings[0];
				if (code.startsWith("#") || code.startsWith("//"))
					continue;
				if (words.containsKey(code)) {
					logger.info("Repeated key in timerParams: " + code);
					continue;
				}
				words.put(code, true);

				final TimerParams entity = new TimerParams();
				entity.setCode(code);
				entity.setJndiProcessorName(strings[1]);
				entity.setSeconds(Integer.valueOf(strings[2]));
				entity.setMinutes(Integer.valueOf(strings[3]));
				entity.setHours(Integer.valueOf(strings[4]));
				entity.setDays(Integer.valueOf(strings[5]));
				entity.setYears(Integer.valueOf(strings[6]));
				entity.setDuration(Integer.valueOf(strings[7]));
				entity.setDurationPot(TimePeriodEnum.valueOf(strings[8]));

				em.merge(entity);
				em.flush();
			}

		} catch (final IOException e) {
			try {
				reader.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e1);
			}
			try {
				in.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e1);
			}
			logger.log(Level.INFO,
					"error Reading file TransactionParams test data", e);

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e);
			}
			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e);
			}
		}
	}

	private void transactionParamsTestData() {
		InputStream in = null;
		CSVReader reader = null;

		try {
			logger.info("creating TransactionParams test data");
			in = InitialData.class
					.getResourceAsStream("/transactionParams.csv");
			reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
					'\'', 0);
			final List<String[]> readAll = reader.readAll();
			final Map<String, Boolean> words = new HashMap<String, Boolean>();

			for (final String[] strings : readAll) {
				final String code = strings[0];
				if (code.startsWith("#") || code.startsWith("//"))
					continue;
				if (words.containsKey(code)) {
					logger.info("Repeated key in transactionParams: " + code);
					continue;
				}
				words.put(code, true);

				final TransactionParams entity = new TransactionParams();
				entity.setCode(code);
				entity.setJndiProcessorName(strings[1]);

				em.merge(entity);
				em.flush();
			}

		} catch (final IOException e) {
			try {
				reader.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e1);
			}
			try {
				in.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e1);
			}
			logger.log(Level.INFO,
					"error Reading file TransactionParams test data", e);

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e);
			}
			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in TransactionParams", e);
			}
		}
	}

	private void uiStrings() {
		logger.info("Creating uistrings data");
		InputStream in = null;
		CSVReader reader = null;
		try {
			in = InitialData.class.getResourceAsStream("/uistrings.csv");
			reader = new CSVReader(new InputStreamReader(in, "UTF-8"), ',',
					'\'', 0);
			final List<String[]> readAll = reader.readAll();
			String code, text;
			LanguageEnum lang = null;
			final Map<String, Boolean> words = new HashMap<String, Boolean>();
			for (final String[] strings : readAll) {
				final String language = strings[0];
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
				} catch (final Exception e) {
					logger.log(Level.SEVERE,
							"Error inserting/updating an uistring", e);
				}
			}
			em.flush();
			reader.close();
			in.close();
		} catch (final IOException e) {
			try {
				reader.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e1);
			}
			try {
				in.close();
			} catch (final IOException e1) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e1);
			}
			logger.log(Level.INFO, "error Reading file uistrings test data", e);

		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e);
			}
			try {
				if (in != null)
					in.close();
			} catch (final IOException e) {
				logger.log(Level.SEVERE, "ERROR in UisTrings", e);
			}
		}

	}

	private void workingAreaTestData() {
		final String query = "SELECT t from WorkingArea t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (final NoResultException e) {
			logger.info("Creating test data : WorkingArea");
			final String workingAreaNames[] = { "Enfermería Emergencia",
					"Enfermería U.C.I", "Enfermería Pediatría" };
			for (int i = 0; i < 3; ++i) {
				final WorkingArea entity = new WorkingArea();
				entity.setBuildingLocation(em.find(BuildingLocation.class,
						"Building 00" + i));
				entity.setName(workingAreaNames[i]);
				em.persist(entity);
			}
			em.flush();
		}
	}

}
