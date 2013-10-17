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
import org.fourgeeks.gha.domain.conf.Parameter;
import org.fourgeeks.gha.domain.conf.ParameterGroup;
import org.fourgeeks.gha.domain.conf.ParameterValue;
import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.LanguageEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.Function;
import org.fourgeeks.gha.domain.ess.Module;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.ess.Screen;
import org.fourgeeks.gha.domain.ess.View;
import org.fourgeeks.gha.domain.ess.WorkingArea;
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
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.UiString;
import org.fourgeeks.gha.ejb.ess.FunctionServiceRemote;

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

	/**
	 * 
	 */
	@PostConstruct
	public void inicializar() {
		modules();
		messages();
		uiStrings();
		parameter();
		testData();
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

	private void messages() {
		String query = "SELECT t FROM GHAMessage t WHERE t.code = 'LOGIN001'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating message test data");
				em.persist(new GHAMessage(LanguageEnum.ES, "info-tittle",
						"Información"));
				em.persist(new GHAMessage(LanguageEnum.EN, "info-tittle",
						"Information"));
				em.persist(new GHAMessage(LanguageEnum.ES, "no-message",
						"No existe un mensaje para su localidad"));
				em.persist(new GHAMessage(LanguageEnum.EN, "no-message",
						"There is no message for your location"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN001",
						"Inicio de sesión exitoso"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN002",
						"Clave incorrecta"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN003",
						"Debe suministrar nombre de usuario y contraseña"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN004",
						"Usuario deshabilitado"));
				em.persist(new GHAMessage(LanguageEnum.ES, "type-not-null",
						"Debe indicar el tipo"));
				em.persist(new GHAMessage(LanguageEnum.ES, "sub-type-not-null",
						"Debe indicar el sub tipo"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"external-provider-not-null",
						"Debe indicar el proveedor"));
				em.persist(new GHAMessage(LanguageEnum.ES, "name-not-null",
						"Debe indicar el nombre"));
				em.persist(new GHAMessage(LanguageEnum.ES, "mobility-not-null",
						"Debe indicar la movilidad"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"base-role-not-null", "Debe indicar el rol"));
				em.persist(new GHAMessage(LanguageEnum.ES, "obu-not-null",
						"Debe indicar la organización"));
				em.persist(new GHAMessage(LanguageEnum.ES, "state-not-null",
						"Debe indicar el estado"));
				em.persist(new GHAMessage(LanguageEnum.ES, "gender-not-null",
						"Debe indicar el genero"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpi-not-null",
						"Debe indicar la institución"));
				em.persist(new GHAMessage(LanguageEnum.ES, "username-not-null",
						"Debe indicar el usuario"));
				em.persist(new GHAMessage(LanguageEnum.ES, "password-not-null",
						"Debe indicar la clave"));
				em.persist(new GHAMessage(LanguageEnum.ES, "code-not-null",
						"Debe indicar el código"));
				em.persist(new GHAMessage(LanguageEnum.ES, "serial-not-null",
						"Debe indicar el serial"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenance-provider-not-null",
						"Debe indicar el proveedor de mantenimiento"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"record-not-selected", "Debe seleccionar un registro"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"frecuency-not-null", "Debe indicar la frecuencia"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"time-period-not-null",
						"Debe indicar el periodo de tiempo"));
				em.persist(new GHAMessage(LanguageEnum.ES, "eia-save-success",
						"Guardado exitoso del Equipo"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiatype-save-success",
						"Guardado exitoso del Tipo de Equipo"));
				em.persist(new GHAMessage(LanguageEnum.ES, "user-save-success",
						"Guardado exitoso del Usuario"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"mplan-save-success",
						"Guardado exitoso del Plan de Mantenimiento"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"mprot-save-success",
						"Guardado exitoso del Protocolo de Mantenimiento"));
				em.persist(new GHAMessage(LanguageEnum.ES, "mact-save-success",
						"Guardado exitoso de la Actividad de Mantenimiento"));
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error Creating message test data", e1);
			}
		}
	}

	private void uiStrings() {
		String query = "SELECT t FROM UiString t WHERE t.code = 'name'";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating uistrings test data");
				em.persist(new UiString(LanguageEnum.ES, "name", "Nombre"));
				em.persist(new UiString(LanguageEnum.ES, "equipments",
						"Equipos"));
				em.persist(new UiString(LanguageEnum.ES, "password",
						"Clave de acceso"));
				em.persist(new UiString(LanguageEnum.ES, "close", "Cerrar"));
				em.persist(new UiString(LanguageEnum.ES, "add", "Agregar"));
				em.persist(new UiString(LanguageEnum.ES, "clean", "Limpiar"));
				em.persist(new UiString(LanguageEnum.ES, "search", "Buscar"));
				em.persist(new UiString(LanguageEnum.ES, "code", "Código"));
				em.persist(new UiString(LanguageEnum.ES, "model", "Modelo"));
				em.persist(new UiString(LanguageEnum.ES, "none", "Modelo"));
				em.persist(new UiString(LanguageEnum.ES, "manufacturer",
						"Fabricante"));
				em.persist(new UiString(LanguageEnum.ES, "mobility",
						"Movilidad"));
				em.persist(new UiString(LanguageEnum.ES, "eiatype",
						"Tipo de equipo"));
				em.persist(new UiString(LanguageEnum.ES, "subtype", "Sub tipo"));
				em.persist(new UiString(LanguageEnum.ES, "brand", "Marca"));
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO, "error Creating uistrings test data", e1);
			}
		}
	}

	private void modules() {
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
				Module moduleMainteancePlan = new Module("Planes de Mant.",
						ModulesCodes.MPLAN);
				em.persist(moduleMainteancePlan);
				Module moduleMaintenanceProtocol = new Module(
						"Protocolos de Mant.", ModulesCodes.MPROT);
				em.persist(moduleMaintenanceProtocol);
				Module maintenanceActivityModule = new Module(
						"Actividades de Mant.", ModulesCodes.MACT);
				em.persist(maintenanceActivityModule);
				// Screen
				Screen screenUsuer = new Screen(moduleUser, "Adm. de Usuarios",
						ScreenCodes.USER_ADM, "user");
				em.persist(screenUsuer);
				Screen screenEiaType = new Screen(moduleEiaType,
						"Tipos de equipo", ScreenCodes.EIATYPE_ADM, "eiatype");
				em.persist(screenEiaType);
				Screen screenEia = new Screen(moduleEia, "Equipos",
						ScreenCodes.EIA_ADM, "eia");
				em.persist(screenEia);
				Screen screenMaintenancePlan = new Screen(moduleMainteancePlan,
						"Planes de Mant.", ScreenCodes.MAINTENANCE_PLAN_ADM,
						"mplan");
				em.persist(screenMaintenancePlan);
				Screen screenMaintenanceProtocol = new Screen(
						moduleMaintenanceProtocol, "Protocolos de Mant.",
						ScreenCodes.MAINTENANCE_PROTOCOL_ADM, "mprot");
				em.persist(screenMaintenanceProtocol);

				Screen maintenanceActivityScreen = new Screen(
						maintenanceActivityModule, "Actividades de Mant.",
						ScreenCodes.MAINTENANCE_ACTIVITY_ADM, "mact");
				em.persist(maintenanceActivityScreen);

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
				View viewMaintenancePlanInfo = new View(screenMaintenancePlan,
						"Información", ViewCodes.MAINTENANCE_PLAN_ADM_INFO);
				em.persist(viewMaintenancePlanInfo);
				View viewMaintenanceProtocol = new View(
						screenMaintenanceProtocol, "Información",
						ViewCodes.MAINTENANCE_PROTOCOL_ADM_INFO);
				em.persist(viewMaintenanceProtocol);
				View viewProtocolActivity = new View(screenMaintenanceProtocol,
						"Actividades", ViewCodes.MAINTENANCE_PROTOCOL_ADM_ACT);
				em.persist(viewProtocolActivity);
				View maintenanceActivityView = new View(
						maintenanceActivityScreen, "Información",
						ViewCodes.MAINTENANCE_ACTIVITY_ADM_INFO);
				em.persist(maintenanceActivityView);
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
				function = new Function(viewMaintenancePlanInfo, "ver",
						FunctionsCodes.MAINTENANCE_PLAN_ADM_INFO_VIEW);
				em.persist(function);

				function = new Function(viewMaintenanceProtocol, "ver",
						FunctionsCodes.MAINTENANCE_PROTOCOL_ADM_INFO_VIEW);
				em.persist(function);

				function = new Function(viewProtocolActivity, "ver",
						FunctionsCodes.MAINTENANCE_PROTOCOL_ADM_ACT_VIEW);
				em.persist(function);

				function = new Function(maintenanceActivityView, "ver",
						FunctionsCodes.MAINTENANCE_ACTIVITY_ADM_VIEW);
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

	@EJB(name = "ess.FunctionService")
	FunctionServiceRemote functionService;

	private void testData() {
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
		maintenancePlanTestData();
		maintenanceProtocolTestData();
		// maintenanceActivityProtocolTestData();
		// MaintenancePlanMaintenanceProtocol();
		// eiaTypeMaintenancePlanTestData();
		// eiaMaintenancePlanificationTestData();
	}

	/**
	 * 
	 */
	private void eiaMaintenancePlanificationTestData() {
		// TODO Auto-generated method stub

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

	/**
	 * 
	 */
	private void maintenanceActivityProtocolTestData() {
		String query = "SELECT t from MaintenanceActivityMaintenanceProtocol t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: MaintenanceActivityMaintenanceProtocol");
				List<MaintenanceProtocol> protocols = em
						.createNamedQuery("MaintenanceProtocol.getAll",
								MaintenanceProtocol.class).getResultList();
				List<MaintenanceActivity> activities = em
						.createNamedQuery("MaintenanceActivity.getAll",
								MaintenanceActivity.class).getResultList();

				for (int i = 0; i < 4; ++i) {
					em.persist(new MaintenanceActivityMaintenanceProtocol(
							protocols.get(0), activities.get(i), i + 1));
					em.persist(new MaintenanceActivityMaintenanceProtocol(
							protocols.get(1), activities.get(i), i + 1));
				}

				em.persist(new MaintenanceActivityMaintenanceProtocol(protocols
						.get(0), activities.get(4), 5));
				em.persist(new MaintenanceActivityMaintenanceProtocol(protocols
						.get(1), activities.get(5), 5));

				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceActivity test data", e1);
			}
		}
	}

	private void MaintenancePlanMaintenanceProtocol() {
		String query = "SELECT t from MaintenancePlanMaintenanceProtocol t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: MaintenancePlanMaintenanceProtocol");
				List<MaintenanceProtocol> protocols = em
						.createNamedQuery("MaintenanceProtocol.getAll",
								MaintenanceProtocol.class).getResultList();
				List<MaintenancePlan> plans = em.createNamedQuery(
						"MaintenancePlan.getAll", MaintenancePlan.class)
						.getResultList();
				for (MaintenancePlan plan : plans) {
					int k = 1;
					for (MaintenanceProtocol protocol : protocols) {
						em.persist(new org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol(
								plan, protocol, k++));
					}
					em.flush();
				}
			} catch (Exception e1) {
				logger.log(
						Level.INFO,
						"error Creating MaintenancePlanMaintenanceProtocol test data",
						e1);
			}
		}
	}

	/**
	 * 
	 */
	private void maintenanceProtocolTestData() {
		String query = "SELECT t from MaintenanceProtocol t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: MaintenanceProtocol");
				String protocolNames[] = {
						"Protocolo para Impresoras de Tinta",
						"Protocolo para impresoras Laser" };
				String protocolDesc[] = {
						"Protocolo para el mantenimiento de impresoras de tinta",
						"Protocolo para el mantenimiento de impresoras laser" };
				for (int i = 0; i < 2; ++i) {
					MaintenanceProtocol protocol = new MaintenanceProtocol();
					protocol.setDescription(protocolDesc[i]);
					protocol.setName(protocolNames[i]);
					em.persist(protocol);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceProtocol test data", e1);
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
				for (int i = 0; i < 2; ++i) {
					MaintenancePlan entity = new MaintenancePlan();
					entity.setName(planName[i]);
					entity.setDescription(planDesc[i]);
					entity.setFrequency(planFrequency[i]);
					entity.setPot(planTimePeriod[i]);
					em.persist(entity);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenancePlan test data", e1);
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

	private void maintenanceActivityTestData() {
		String query = "SELECT t from MaintenanceActivity t WHERE t.id = 1";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("Creating test data: maintenance activity");
				String activityNames[] = { "Desconectar", "Abrir", "Limpiar",
						"Cerrar", "Conectar", "Reemplazar" };
				String activityDesc[] = {
						"Desconecte el equipo de la corriente eléctrica",
						"Quite los tornillos y levante la tapa del equipo",
						"Limpie cuidadosamente el interior del equipo, sin líquidos",
						"Cierre la tapa del equipo, y coloque los tornillos",
						"Conecte el equipo a la corriente eléctrica, y verifique su funcionamiento",
						"Reemplaze el toner del equipo" };
				for (int i = 0; i < 6; ++i) {
					MaintenanceActivity entity = new MaintenanceActivity();
					entity.setName(activityNames[i]);
					entity.setDescription(activityDesc[i]);
					em.persist(entity);
				}
				em.flush();
			} catch (Exception e1) {
				logger.log(Level.INFO,
						"error Creating MaintenanceActivity test data", e1);
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
				em.flush();
				logger.info("done creating test users");
			} catch (Exception e1) {
				logger.log(Level.INFO, "error test data ssouser");
			}
		}
	}

}