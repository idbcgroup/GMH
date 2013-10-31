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
 * @author alacret, vivi.torresg
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
				em.persist(new GHAMessage(LanguageEnum.ES, "asset-id-not-null",
						"Dindicar el identificador del activo"));
				em.persist(new GHAMessage(LanguageEnum.ES, "unsaved-changes",
						"Existen cambios sin guardar, ¿Desea descartarlos?"));
				em.persist(new GHAMessage(LanguageEnum.EN, "info-tittle",
						"Information"));
				em.persist(new GHAMessage(LanguageEnum.ES, "no-message",
						"No existe un mensaje para su localidad"));
				em.persist(new GHAMessage(LanguageEnum.EN, "no-message",
						"There is no message for your location"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN001",
						"Inicio de sesión exitoso"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN002",
						"La clave de acceso es incorrecta"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN003",
						"Debe suministrar nombre de usuario y contraseña"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN004",
						"Usuario deshabilitado"));
				em.persist(new GHAMessage(LanguageEnum.ES, "LOGIN005",
						"Usuario no registrado en el sistema"));
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
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiatype-delete-success",
						"Eliminación exitosa del Tipo de Equipo"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiatypes-delete-success",
						"Eliminación exitosa de los Tipos de Equipo"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-save-success",
						"Guardado exitoso del material"));
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

				// eiatype service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiatype-delete-fail",
						"Falló la eliminación del EiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES, "eiatype-save-fail",
						"Guardado fallido del Tipo de Equipo"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiatype-update-fail",
						"Actualización fallida del Tipo de Equipo"));
				em.persist(new GHAMessage(LanguageEnum.ES, "eiatype-find-fail",
						"Búsqueda fallida de Tipos de Equipo"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiatype-findByMaintenancePlan-fail",
						"Búsqueda fallida del Tipo de Equipo por Plan de Mantenimeinto"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiatype-getAll-fail",
						"Búsqueda fallida de todos los Tipos de Equipo"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiatype-delete-fail",
						"Failed to delete Equipment type"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiatype-update-fail",
						"Failed to update Equipment type"));
				em.persist(new GHAMessage(LanguageEnum.EN, "eiatype-find-fail",
						"Failed to find Equipment Type"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiatype-findByMaintenancePlan-fail",
						"Failed to find Equipment type by Maintenance Plan"));
				em.persist(new GHAMessage(LanguageEnum.EN, "eiatype-save-fail",
						"Failed to save Equipment type"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiatype-getAll-fail",
						"Failed to get all Equipment types"));

				// function service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"function-getAll-fail",
						"Búsqueda fallida de todas las Funciones"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"function-getAll-fail", "Failed to get all Functions"));

				// instanceLogon service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"instanceLogon-delete-fail",
						"Fallo la eliminación del InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"instanceLogon-findByInstanceLogon-fail",
						"Búsqueda fallida de InstanceLogon por instanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"instanceLogon-find-fail",
						"Búsqueda fallida de InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"instanceLogon-getAll-fail",
						"Búsqueda fallida de todos los InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"instanceLogon-save-fail",
						"Guardado fallido de InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"instanceLogon-update-fail",
						"Actualización fallida de InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"instanceLogon-delete-fail",
						"Failed to delete InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"instanceLogon-findByInstanceLogon-fail",
						"Failed to find InstanceLogon by instanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"instanceLogon-find-fail",
						"Failed to find InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"instanceLogon-getAll-fail",
						"Failed to get all instanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"instanceLogon-save-fail",
						"Failed to save InstanceLogon"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"instanceLogon-update-fail",
						"Failed to update InstanceLogon"));

				// role service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "role-delete-fail",
						"Fallo la eliminación del Rol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"role-findByRoleBase-fail",
						"Búsqueda fallida de Rol por rol"));
				em.persist(new GHAMessage(LanguageEnum.ES, "role-find-fail",
						"Búsqueda fallida de Rol"));
				em.persist(new GHAMessage(LanguageEnum.ES, "role-getAll-fail",
						"Búsqueda fallida de todos los Rol"));
				em.persist(new GHAMessage(LanguageEnum.ES, "role-save-fail",
						"Guardado fallido de Rol"));
				em.persist(new GHAMessage(LanguageEnum.ES, "role-update-fail",
						"Actualización fallida de Rol"));
				em.persist(new GHAMessage(LanguageEnum.EN, "role-delete-fail",
						"Failed to delete Role"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"role-findByRoleBase-fail",
						"Failed to find Role by role"));
				em.persist(new GHAMessage(LanguageEnum.EN, "role-find-fail",
						"Failed to find Role"));
				em.persist(new GHAMessage(LanguageEnum.EN, "role-getAll-fail",
						"Failed to get all Role"));
				em.persist(new GHAMessage(LanguageEnum.EN, "role-save-fail",
						"Failed to save Role"));
				em.persist(new GHAMessage(LanguageEnum.EN, "role-update-fail",
						"Failed to update Role"));

				// ssoUser service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"ssoUser-delete-fail",
						"Fallo la eliminación del SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"ssoUser-findBySsoUser-fail",
						"Búsqueda fallida de SSOUser por ssoUser"));
				em.persist(new GHAMessage(LanguageEnum.ES, "ssoUser-find-fail",
						"Búsqueda fallida de SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"ssoUser-getAll-fail",
						"Búsqueda fallida de todos los SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.ES, "ssoUser-save-fail",
						"Guardado fallido de SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"ssoUser-update-fail",
						"Actualización fallida de SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"ssoUser-findByUserName-fail",
						"Búsqueda fallida de SSOUser por userName"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"ssoUser-delete-fail", "Failed to delete SSOUSer"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"ssoUser-findBySsoUser-fail",
						"Failed to find SSOUser by ssoUser"));
				em.persist(new GHAMessage(LanguageEnum.EN, "ssoUser-find-fail",
						"Failed to find SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"ssoUser-getAll-fail", "Failed to get all SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.EN, "ssoUser-save-fail",
						"Failed to save SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"ssoUser-update-fail", "Failed to update SSOUser"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"ssoUser-findByUserName-fail",
						"Failed to find SSOUser by userName"));

				// bpuFunction service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"bpuFunction-save-fail",
						"Guardado fallido del BpuFunction"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"bpuFunction-delete-fail",
						"Fallo la eliminación del BpuFunction"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"bpuFunction-getFunctionsByBpu-fail",
						"Búsqueda fallida de BpuFunction por bpu"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"bpuFunction-save-fail", "Failed to save BpuFunction"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"bpuFunction-delete-fail",
						"Failed to delete BpuFunction"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"bpuFunction-getFunctionsByBpu-fail",
						"Failed to get BpuFunction by bpu"));

				// workingArea service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"workingArea-delete-fail",
						"Fallo la eliminación del WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"workingArea-findByWorkingArea-fail",
						"Búsqueda fallida de WorkingArea por workingArea"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"workingArea-find-fail",
						"Búsqueda fallida de WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"workingArea-getAll-fail",
						"Búsqueda fallida de todos los WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"workingArea-save-fail",
						"Guardado fallido de WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"workingArea-update-fail",
						"Actualización fallida de WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"workingArea-delete-fail",
						"Failed to delete WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"workingArea-findByWorkingArea-fail",
						"Failed to find WorkingArea by workingArea"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"workingArea-find-fail", "Failed to find WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"workingArea-getAll-fail",
						"Failed to get all WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"workingArea-save-fail", "Failed to save WorkingArea"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"workingArea-update-fail",
						"Failed to update WorkingArea"));

				// bpu service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "bpu-delete-fail",
						"Fallo la eliminación del Bpu"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"bpu-findByBpu-fail", "Búsqueda fallida de Bpu por bpu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpu-find-fail",
						"Búsqueda fallida de Bpu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpu-getAll-fail",
						"Búsqueda fallida de todos los Bpu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpu-save-fail",
						"Guardado fallido de Bpu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpu-update-fail",
						"Actualización fallida de Bpu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpu-delete-fail",
						"Failed to delete Bpu"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"bpu-findByBpu-fail", "Failed to find Bpu by bpu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpu-find-fail",
						"Failed to find Bpu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpu-getAll-fail",
						"Failed to get all Bpu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpu-save-fail",
						"Failed to save Bpu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpu-update-fail",
						"Failed to update Bpu"));

				// facility service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"facility-delete-fail",
						"Fallo la eliminación del Facility"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"facility-findByFacility-fail",
						"Búsqueda fallida de Facility por facility"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"facility-find-fail", "Búsqueda fallida de Facility"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"facility-getAll-fail",
						"Búsqueda fallida de todos los Facility"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"facility-save-fail", "Guardado fallido de Facility"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"facility-update-fail",
						"Actualización fallida de Facility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"facility-delete-fail", "Failed to delete Facility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"facility-findByFacility-fail",
						"Failed to find Facility by facility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"facility-find-fail", "Failed to find Facility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"facility-getAll-fail", "Failed to get all Facility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"facility-save-fail", "Failed to save Facility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"facility-update-fail", "Failed to update Facility"));

				// obu service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "obu-delete-fail",
						"Fallo la eliminación del Obu"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"obu-findByObu-fail", "Búsqueda fallida de Obu por obu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "obu-find-fail",
						"Búsqueda fallida de Obu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "obu-getAll-fail",
						"Búsqueda fallida de todos los Obu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "obu-save-fail",
						"Guardado fallido de Obu"));
				em.persist(new GHAMessage(LanguageEnum.ES, "obu-update-fail",
						"Actualización fallida de Obu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "obu-delete-fail",
						"Failed to delete Obu"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"obu-findByObu-fail", "Failed to find Obu by obu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "obu-find-fail",
						"Failed to find Obu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "obu-getAll-fail",
						"Failed to get all Obu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "obu-save-fail",
						"Failed to save Obu"));
				em.persist(new GHAMessage(LanguageEnum.EN, "obu-update-fail",
						"Failed to update Obu"));

				// externalProvider service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"externalProvider-delete-fail",
						"Fallo la eliminación del ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"externalProvider-findByExternalProvider-fail",
						"Búsqueda fallida de ExternalProvider por externalProvider"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"externalProvider-find-fail",
						"Búsqueda fallida de ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"externalProvider-getAll-fail",
						"Búsqueda fallida de todos los ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"externalProvider-save-fail",
						"Guardado fallido de ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"externalProvider-update-fail",
						"Actualización fallida de ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"externalProvider-delete-fail",
						"Failed to delete ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"externalProvider-findByExternalProvider-fail",
						"Failed to find ExternalProvider by externalProvider"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"externalProvider-find-fail",
						"Failed to find ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"externalProvider-getAll-fail",
						"Failed to get all ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"externalProvider-save-fail",
						"Failed to save ExternalProvider"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"externalProvider-update-fail",
						"Failed to update ExternalProvider"));

				// materialCategory service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"materialCategory-delete-fail",
						"Fallo la eliminación del MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"materialCategory-findByMaterialCategory-fail",
						"Búsqueda fallida de MaterialCategory por materialCategory"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"materialCategory-find-fail",
						"Búsqueda fallida de MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"materialCategory-getAll-fail",
						"Búsqueda fallida de todos los MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"materialCategory-save-fail",
						"Guardado fallido de MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"materialCategory-update-fail",
						"Actualización fallida de MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"materialCategory-delete-fail",
						"Failed to delete MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"materialCategory-findByMaterialCategory-fail",
						"Failed to find MaterialCategory by materialCategory"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"materialCategory-find-fail",
						"Failed to find MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"materialCategory-getAll-fail",
						"Failed to get all MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"materialCategory-save-fail",
						"Failed to save MaterialCategory"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"materialCategory-update-fail",
						"Failed to update MaterialCategory"));

				// material service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-delete-fail",
						"Fallo la eliminación del Material"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-findByMaterial-fail",
						"Búsqueda fallida de Material por material"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-find-fail", "Búsqueda fallida de Material"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-findByType-fail",
						"Búsqueda fallida de Material por Tipo"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-findByBrand-fail",
						"Búsqueda fallida de Material por Brand"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-getAll-fail",
						"Búsqueda fallida de todos los Material"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-save-fail", "Guardado fallido de Material"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"material-update-fail",
						"Actualización fallida de Material"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-delete-fail", "Failed to delete Material"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-findByMaterial-fail",
						"Failed to find Material by material"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-find-fail", "Failed to find Material"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-findByType-fail",
						"Failed to find Material by Type"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-findByBrand-fail",
						"Falied to find Material by Brand"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-getAll-fail", "Failed to get all Material"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-save-fail", "Failed to save Material"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"material-update-fail", "Failed to update Material"));

				// brand service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "brand-delete-fail",
						"Fallo la eliminación del Brand"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"brand-findByBrand-fail",
						"Búsqueda fallida de Brand por brand"));
				em.persist(new GHAMessage(LanguageEnum.ES, "brand-find-fail",
						"Búsqueda fallida de Brand"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"brand-findByManufacturer-fail",
						"Búsqueda fallida de Brand por Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.ES, "brand-getAll-fail",
						"Búsqueda fallida de todos los Brand"));
				em.persist(new GHAMessage(LanguageEnum.ES, "brand-save-fail",
						"Guardado fallido de Brand"));
				em.persist(new GHAMessage(LanguageEnum.ES, "brand-update-fail",
						"Actualización fallida de Brand"));
				em.persist(new GHAMessage(LanguageEnum.EN, "brand-delete-fail",
						"Failed to delete Brand"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"brand-findByBrand-fail",
						"Failed to find Brand by brand"));
				em.persist(new GHAMessage(LanguageEnum.EN, "brand-find-fail",
						"Failed to find Brand"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"brand-findByManufacturer-fail",
						"Failed to find Brand by Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.EN, "brand-getAll-fail",
						"Failed to get all Brand"));
				em.persist(new GHAMessage(LanguageEnum.EN, "brand-save-fail",
						"Failed to save Brand"));
				em.persist(new GHAMessage(LanguageEnum.EN, "brand-update-fail",
						"Failed to update Brand"));

				// buildingLocation service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"buildingLocation-delete-fail",
						"Fallo la eliminación del BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"buildingLocation-findByBuildingLocation-fail",
						"Búsqueda fallida de BuildingLocation por buildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"buildingLocation-find-fail",
						"Búsqueda fallida de BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"buildingLocation-getAll-fail",
						"Búsqueda fallida de todos los BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"buildingLocation-save-fail",
						"Guardado fallido de BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"buildingLocation-update-fail",
						"Actualización fallida de BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"buildingLocation-delete-fail",
						"Failed to delete BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"buildingLocation-findByBuildingLocation-fail",
						"Failed to find BuildingLocation by buildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"buildingLocation-find-fail",
						"Failed to find BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"buildingLocation-getAll-fail",
						"Failed to get all BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"buildingLocation-save-fail",
						"Failed to save BuildingLocation"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"buildingLocation-update-fail",
						"Failed to update BuildingLocation"));

				// eiaComponent service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaComponent-delete-fail",
						"Fallo la eliminación del EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaComponent-findByParentEia-fail",
						"Búsqueda fallida de EiaComponent por parentEia"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaComponent-find-fail",
						"Búsqueda fallida de EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaComponent-getAll-fail",
						"Búsqueda fallida de todos los EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaComponent-save-fail",
						"Guardado fallido de EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaComponent-update-fail",
						"Actualización fallida de EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaComponent-delete-fail",
						"Failed to delete EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaComponent-findByParentEia-fail",
						"Failed to find EiaComponent by parentEia"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaComponent-find-fail", "Failed to find EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaComponent-getAll-fail",
						"Failed to get all EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaComponent-save-fail", "Failed to save EiaComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaComponent-update-fail",
						"Failed to update EiaComponent"));

				// eiaPicture service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaPicture-delete-fail",
						"Fallo la eliminación del EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaPicture-findByEia-fail",
						"Búsqueda fallida de EiaPicture por eia"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaPicture-find-fail",
						"Búsqueda fallida de EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaPicture-getAll-fail",
						"Búsqueda fallida de todos los EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaPicture-save-fail",
						"Guardado fallido de EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaPicture-update-fail",
						"Actualización fallida de EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaPicture-delete-fail", "Failed to delete EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaPicture-findByEia-fail",
						"Failed to find EiaPicture by eia"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaPicture-find-fail", "Failed to find EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaPicture-getAll-fail",
						"Failed to get all EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaPicture-save-fail", "Failed to save EiaPicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaPicture-update-fail", "Failed to update EiaPicture"));

				// eia service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "eia-delete-fail",
						"Fallo la eliminación del Eia"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eia-findByEia-fail", "Búsqueda fallida de Eia por eia"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eia-findByEiaType-fail",
						"Búsqueda fallida de Eia por eiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES, "eia-find-fail",
						"Búsqueda fallida de Eia"));
				em.persist(new GHAMessage(LanguageEnum.ES, "eia-getAll-fail",
						"Búsqueda fallida de todos los Eia"));
				em.persist(new GHAMessage(LanguageEnum.ES, "eia-save-fail",
						"Guardado fallido de Eia"));
				em.persist(new GHAMessage(LanguageEnum.ES, "eia-update-fail",
						"Actualización fallida de Eia"));
				em.persist(new GHAMessage(LanguageEnum.EN, "eia-delete-fail",
						"Failed to delete Eia"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eia-findByEia-fail", "Failed to find Eia by eia"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eia-findByEiaType-fail",
						"Failed to find Eia by eiaType"));
				em.persist(new GHAMessage(LanguageEnum.EN, "eia-find-fail",
						"Failed to find Eia"));
				em.persist(new GHAMessage(LanguageEnum.EN, "eia-getAll-fail",
						"Failed to get all Eia"));
				em.persist(new GHAMessage(LanguageEnum.EN, "eia-save-fail",
						"Failed to save Eia"));
				em.persist(new GHAMessage(LanguageEnum.EN, "eia-update-fail",
						"Failed to update Eia"));

				// eiaTypeComponent service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeComponent-delete-fail",
						"Fallo la eliminación del EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeComponent-findByParentEiaType-fail",
						"Búsqueda fallida de EiaTypeComponent por parentEiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeComponent-find-fail",
						"Búsqueda fallida de EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeComponent-getAll-fail",
						"Búsqueda fallida de todos los EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeComponent-save-fail",
						"Guardado fallido de EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeComponent-update-fail",
						"Actualización fallida de EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeComponent-delete-fail",
						"Failed to delete EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeComponent-findByParentEiaType-fail",
						"Failed to find EiaTypeComponent by parentEiaType"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeComponent-find-fail",
						"Failed to find EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeComponent-getAll-fail",
						"Failed to get all EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeComponent-save-fail",
						"Failed to save EiaTypeComponent"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeComponent-update-fail",
						"Failed to update EiaTypeComponent"));

				// eiaTypeMaintenancePlan service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeMaintenancePlan-delete-fail",
						"Fallo la eliminación del EiaTypeMaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeMaintenancePlan-findByEiaType-fail",
						"Búsqueda fallida de EiaTypeMaintenancePlan por eiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeMaintenancePlan-findByMaintenancePlan-fail",
						"Búsqueda fallida de EiaTypeMaintenancePlan po maintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeMaintenancePlan-save-fail",
						"Guardado fallido de EiaTypeMaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeMaintenancePlan-delete-fail",
						"Failed to delete EiaTypeMaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeMaintenancePlan-findByEiaType-fail",
						"Failed to find EiaTypeMaintenancePlan by eiaType"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeMaintenancePlan-findByMaintenancePlan-fail",
						"Failed to find EiaTypeMaintenancePlan by maintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeMaintenancePlan-save-fail",
						"Failed to save EiaTypeMaintenancePlan"));

				// eiaTypeMaterial service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeMaterial-delete-fail",
						"Fallo la eliminación del EiaTypeMaterial"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeMaterial-findByEiaType-fail",
						"Búsqueda fallida de EiaTypeMaterial por eiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeMaterial-save-fail",
						"Guardado fallido de EiaTypeMaterial"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeMaterial-delete-fail",
						"Failed to delete EiaTypeMaterial"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeMaterial-findByEiaType-fail",
						"Failed to find EiaTypeMaterial by eiaType"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeMaterial-save-fail",
						"Failed to save EiaTypeMaterial"));

				// eiaTypePicture service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypePicture-delete-fail",
						"Fallo la eliminación del EiaTypePicture"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypePicture-findByEiaType-fail",
						"Búsqueda fallida de EiaTypePicture por eiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypePicture-find-fail",
						"Búsqueda fallida de EiaTypePicture"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypePicture-save-fail",
						"Guardado fallido de EiaTypePicture"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypePicture-update-fail",
						"Actualización fallida de EiaTypePicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypePicture-delete-fail",
						"Failed to delete EiaTypePicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypePicture-findByEiaType-fail",
						"Failed to find EiaTypePicture by eiaType"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypePicture-find-fail",
						"Failed to find EiaTypePicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypePicture-save-fail",
						"Failed to save EiaTypePicture"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypePicture-update-fail",
						"Failed to update EiaTypePicture"));

				// eiaTypeUtility service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeUtility-delete-fail",
						"Fallo la eliminación del EiaTypeUtility"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeUtility-findByEiaType-fail",
						"Búsqueda fallida de EiaTypeUtility por eiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"eiaTypeUtility-save-fail",
						"Guardado fallido de EiaTypeUtility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeUtility-delete-fail",
						"Failed to delete EiaTypeUtility"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeUtility-findByEiaType-fail",
						"Failed to find EiaTypeUtility by eiaType"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"eiaTypeUtility-save-fail",
						"Failed to save EiaTypeUtility"));

				// maintenanceActivityMaintenanceProtocol service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivityMaintenanceProtocol-delete-fail",
						"Fallo la eliminación del MaintenanceActivityMaintenanceProtocol"));
				em.persist(new GHAMessage(
						LanguageEnum.ES,
						"maintenanceActivityMaintenanceProtocol-findByMaintenanceActivity-fail",
						"Búsqueda fallida de MaintenanceActivityMaintenanceProtocol por actividad de mantenimiento"));
				em.persist(new GHAMessage(
						LanguageEnum.ES,
						"maintenanceActivityMaintenanceProtocol-findByMaintenanceProtocol-fail",
						"Búsqueda fallida de MaintenanceActivityMaintenanceProtocol por protocolo de mantenimiento"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivityMaintenanceProtocol-save-fail",
						"Guardado fallido de MaintenanceActivityMaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivityMaintenanceProtocol-delete-fail",
						"Failed to delete MaintenanceActivityMaintenanceProtocol"));
				em.persist(new GHAMessage(
						LanguageEnum.EN,
						"maintenanceActivityMaintenanceProtocol-findByMaintenanceActivity-fail",
						"Failed to find MaintenanceActivityMaintenanceProtocol by maintenance activity"));
				em.persist(new GHAMessage(
						LanguageEnum.EN,
						"maintenanceActivityMaintenanceProtocol-findByMaintenanceProtocol-fail",
						"Failed to find MaintenanceActivityMaintenanceProtocol by maintenance protocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivityMaintenanceProtocol-save-fail",
						"Failed to save MaintenanceActivityMaintenanceProtocol"));

				// maintenanceActivity service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-delete-fail",
						"Fallo la eliminación del MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-findByMaintenanceProtocol-fail",
						"Búsqueda fallida de MaintenanceActivity por protocolo de mantenimiento"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-findByServiceResource-fail",
						"Búsqueda fallida de MaintenanceActivity por serviceResource"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-find-fail",
						"Búsqueda fallida de MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-findByMaintenanceActivity-fail",
						"Búsqueda fallida de MaintenanceActivity por maintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-getAll-fail",
						"Búsqueda fallida de todos los MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-save-fail",
						"Guardado fallido de MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceActivity-update-fail",
						"Actualización fallida de MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-delete-fail",
						"Failed to delete MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-findByMaintenanceProtocol-fail",
						"Failed to find MaintenanceActivity by maintenance protocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-findByServiceResource-fail",
						"Failed to find MaintenanceActivity by serviceResource"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-find-fail",
						"Failed to find MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-findByMaintenanceActivity-fail",
						"Failed to find MaintenanceActivity by maintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-getAll-fail",
						"Failed to get all MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-save-fail",
						"Failed to save MaintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceActivity-update-fail",
						"Failed to update MaintenanceActivity"));

				// maintenancePlanMaintenanceProtocol service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlanMaintenanceProtocol-delete-fail",
						"Fallo la eliminación del MaintenancePlanMaintenanceProtocol"));
				em.persist(new GHAMessage(
						LanguageEnum.ES,
						"maintenancePlanMaintenanceProtocol-findByMaintenancePlan-fail",
						"Búsqueda fallida de MaintenancePlanMaintenanceProtocol por plan de mantenimiento"));
				em.persist(new GHAMessage(
						LanguageEnum.ES,
						"maintenancePlanMaintenanceProtocol-findByMaintenanceProtocol-fail",
						"Búsqueda fallida de MaintenancePlanMaintenanceProtocol por protocolo de mantenimiento"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlanMaintenanceProtocol-save-fail",
						"Guardado fallido de MaintenancePlanMaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlanMaintenanceProtocol-delete-fail",
						"Failed to delete MaintenancePlanMaintenanceProtocol"));
				em.persist(new GHAMessage(
						LanguageEnum.EN,
						"maintenancePlanMaintenanceProtocol-findByMaintenancePlan-fail",
						"Failed to find MaintenancePlanMaintenanceProtocol by maintenance plan"));
				em.persist(new GHAMessage(
						LanguageEnum.EN,
						"maintenancePlanMaintenanceProtocol-findByMaintenanceProtocol-fail",
						"Failed to find MaintenancePlanMaintenanceProtocol by maintenance protocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlanMaintenanceProtocol-save-fail",
						"Failed to save MaintenancePlanMaintenanceProtocol"));

				// maintenancePlan service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlan-delete-fail",
						"Fallo la eliminación del MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlan-findByEiaType-fail",
						"Búsqueda fallida de MaintenancePlan por eiaType"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlan-findByMaintenancePlan-fail",
						"Búsqueda fallida de MaintenancePlan por maintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlan-find-fail",
						"Búsqueda fallida de MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlan-getAll-fail",
						"Búsqueda fallida de todos los MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlan-save-fail",
						"Guardado fallido de MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenancePlan-update-fail",
						"Actualización fallida de MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlan-delete-fail",
						"Failed to delete MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlan-findByEiaType-fail",
						"Failed to find MaintenancePlan by eiaType"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlan-findByMaintenancePlan-fail",
						"Failed to find MaintenancePlan by maintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlan-find-fail",
						"Failed to find MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlan-getAll-fail",
						"Failed to get all MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlan-save-fail",
						"Failed to save MaintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenancePlan-update-fail",
						"Failed to update MaintenancePlan"));

				// maintenanceProtocol service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceProtocol-delete-fail",
						"Fallo la eliminación del MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceProtocol-findByMaintenancePlan-fail",
						"Búsqueda fallida de MaintenanceProtocol por plan de mantenimiento"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceProtocol-findByMaintenanceProtocol-fail",
						"Búsqueda fallida de MaintenanceProtocol por maintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceProtocol-find-fail",
						"Búsqueda fallida de MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceProtocol-getAll-fail",
						"Búsqueda fallida de todos los MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceProtocol-save-fail",
						"Guardado fallido de MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceProtocol-update-fail",
						"Actualización fallida de MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceProtocol-delete-fail",
						"Failed to delete MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceProtocol-findByMaintenancePlan-fail",
						"Failed to find MaintenanceProtocol by maintenancePlan"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceProtocol-findByMaintenanceProtocol-fail",
						"Failed to find MaintenanceProtocol by maintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceProtocol-find-fail",
						"Failed to find MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceProtocol-getAll-fail",
						"Failed to get all MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceProtocol-save-fail",
						"Failed to save MaintenanceProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceProtocol-update-fail",
						"Failed to update MaintenanceProtocol"));

				// maintenanceSubProtocol service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceSubProtocol-delete-fail",
						"Fallo la eliminación del MaintenanceSubProtocol"));
				em.persist(new GHAMessage(
						LanguageEnum.ES,
						"maintenanceSubProtocol-findByMaintenanceActivity-fail",
						"Búsqueda fallida de MaintenanceSubProtocol por maintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceSubProtocol-find-fail",
						"Búsqueda fallida de MaintenanceSubProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceSubProtocol-getAll-fail",
						"Búsqueda fallida de todos los MaintenanceSubProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceSubProtocol-save-fail",
						"Guardado fallido de MaintenanceSubProtocol"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"maintenanceSubProtocol-update-fail",
						"Actualización fallida de MaintenanceSubProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceSubProtocol-delete-fail",
						"Failed to delete MaintenanceSubProtocol"));
				em.persist(new GHAMessage(
						LanguageEnum.EN,
						"maintenanceSubProtocol-findByMaintenanceActivity-fail",
						"Failed to find MaintenanceSubProtocol by maintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceSubProtocol-find-fail",
						"Failed to find MaintenanceSubProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceSubProtocol-getAll-fail",
						"Failed to get all MaintenanceSubProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceSubProtocol-save-fail",
						"Failed to save MaintenanceSubProtocol"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"maintenanceSubProtocol-update-fail",
						"Failed to update MaintenanceSubProtocol"));

				// manufacturer service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"manufacturer-delete-fail",
						"Fallo la eliminación del Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"manufacturer-findByManufacturer-fail",
						"Búsqueda fallida de Manufacturer por manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"manufacturer-find-fail",
						"Búsqueda fallida de Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"manufacturer-getAll-fail",
						"Búsqueda fallida de todos los Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"manufacturer-save-fail",
						"Guardado fallido de Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"manufacturer-update-fail",
						"Actualización fallida de Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"manufacturer-delete-fail",
						"Failed to delete Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"manufacturer-findByManufacturer-fail",
						"Failed to find Manufacturer by manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"manufacturer-find-fail", "Failed to find Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"manufacturer-getAll-fail",
						"Failed to get all Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"manufacturer-save-fail", "Failed to save Manufacturer"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"manufacturer-update-fail",
						"Failed to update Manufacturer"));

				// serviceResource service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"serviceResource-delete-fail",
						"Fallo la eliminación del ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"serviceResource-findByMaintenanceActivity-fail",
						"Búsqueda fallida de ServiceResource por maintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"serviceResource-find-fail",
						"Búsqueda fallida de ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"serviceResource-getAll-fail",
						"Búsqueda fallida de todos los ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"serviceResource-save-fail",
						"Guardado fallido de ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"serviceResource-update-fail",
						"Actualización fallida de ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"serviceResource-delete-fail",
						"Failed to delete ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"serviceResource-findByMaintenanceActivity-fail",
						"Failed to find ServiceResource by maintenanceActivity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"serviceResource-find-fail",
						"Failed to find ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"serviceResource-getAll-fail",
						"Failed to get all ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"serviceResource-save-fail",
						"Failed to save ServiceResource"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"serviceResource-update-fail",
						"Failed to update ServiceResource"));

				// bpa service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "bpa-delete-fail",
						"Fallo la eliminación del Bpa"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"bpa-findByBpa-fail", "Búsqueda fallida de Bpa por bpa"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpa-find-fail",
						"Búsqueda fallida de Bpa"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpa-getAll-fail",
						"Búsqueda fallida de todos los Bpa"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpa-save-fail",
						"Guardado fallido de Bpa"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpa-update-fail",
						"Actualización fallida de Bpa"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpa-delete-fail",
						"Failed to delete Bpa"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"bpa-findByBpa-fail", "Failed to find Bpa by bpa"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpa-find-fail",
						"Failed to find Bpa"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpa-getAll-fail",
						"Failed to get all Bpa"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpa-save-fail",
						"Failed to save Bpa"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpa-update-fail",
						"Failed to update Bpa"));

				// bpi service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "bpi-delete-fail",
						"Fallo la eliminación del Bpi"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"bpi-findByBpi-fail", "Búsqueda fallida de Bpi por bpi"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpi-find-fail",
						"Búsqueda fallida de Bpi"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpi-getAll-fail",
						"Búsqueda fallida de todos los Bpi"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpi-save-fail",
						"Guardado fallido de Bpi"));
				em.persist(new GHAMessage(LanguageEnum.ES, "bpi-update-fail",
						"Actualización fallida de Bpi"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpi-delete-fail",
						"Failed to delete Bpi"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"bpi-findByBpi-fail", "Failed to find Bpi by bpi"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpi-find-fail",
						"Failed to find Bpi"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpi-getAll-fail",
						"Failed to get all Bpi"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpi-save-fail",
						"Failed to save Bpi"));
				em.persist(new GHAMessage(LanguageEnum.EN, "bpi-update-fail",
						"Failed to update Bpi"));

				// citizen service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"citizen-delete-fail",
						"Fallo la eliminación del Citizen"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"citizen-findByCitizen-fail",
						"Búsqueda fallida de Citizen por citizen"));
				em.persist(new GHAMessage(LanguageEnum.ES, "citizen-find-fail",
						"Búsqueda fallida de Citizen"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"citizen-getAll-fail",
						"Búsqueda fallida de todos los Citizen"));
				em.persist(new GHAMessage(LanguageEnum.ES, "citizen-save-fail",
						"Guardado fallido de Citizen"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"citizen-update-fail",
						"Actualización fallida de Citizen"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"citizen-delete-fail", "Failed to delete Citizen"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"citizen-findByCitizen-fail",
						"Failed to find Citizen by citizen"));
				em.persist(new GHAMessage(LanguageEnum.EN, "citizen-find-fail",
						"Failed to find Citizen"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"citizen-getAll-fail", "Failed to get all Citizen"));
				em.persist(new GHAMessage(LanguageEnum.EN, "citizen-save-fail",
						"Failed to save Citizen"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"citizen-update-fail", "Failed to update Citizen"));

				// institution service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"institution-delete-fail",
						"Fallo la eliminación del Institution"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"institution-findByInstitution-fail",
						"Búsqueda fallida de Institution por institution"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"institution-find-fail",
						"Búsqueda fallida de Institution"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"institution-getAll-fail",
						"Búsqueda fallida de todos los Institution"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"institution-save-fail",
						"Guardado fallido de Institution"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"institution-update-fail",
						"Actualización fallida de Institution"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"institution-delete-fail",
						"Failed to delete Institution"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"institution-findByInstitution-fail",
						"Failed to find Institution by institution"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"institution-find-fail", "Failed to find Institution"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"institution-getAll-fail",
						"Failed to get all Institution"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"institution-save-fail", "Failed to save Institution"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"institution-update-fail",
						"Failed to update Institution"));

				// legalEntity service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"legalEntity-delete-fail",
						"Fallo la eliminación del LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"legalEntity-findByLegalEntity-fail",
						"Búsqueda fallida de LegalEntity por legalEntity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"legalEntity-find-fail",
						"Búsqueda fallida de LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"legalEntity-getAll-fail",
						"Búsqueda fallida de todos los LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"legalEntity-save-fail",
						"Guardado fallido de LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"legalEntity-update-fail",
						"Actualización fallida de LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"legalEntity-delete-fail",
						"Failed to delete LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"legalEntity-findByLegalEntity-fail",
						"Failed to find LegalEntity by legalEntity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"legalEntity-find-fail", "Failed to find LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"legalEntity-getAll-fail",
						"Failed to get all LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"legalEntity-save-fail", "Failed to save LegalEntity"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"legalEntity-update-fail",
						"Failed to update LegalEntity"));

				// systemInstance service messages
				em.persist(new GHAMessage(LanguageEnum.ES,
						"systemInstance-delete-fail",
						"Fallo la eliminación del SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"systemInstance-findBySystemInstance-fail",
						"Búsqueda fallida de SystemInstance por systemInstance"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"systemInstance-find-fail",
						"Búsqueda fallida de SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"systemInstance-getAll-fail",
						"Búsqueda fallida de todos los SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"systemInstance-save-fail",
						"Guardado fallido de SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.ES,
						"systemInstance-update-fail",
						"Actualización fallida de SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"systemInstance-delete-fail",
						"Failed to delete SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"systemInstance-findBySystemInstance-fail",
						"Failed to find SystemInstance by systemInstance"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"systemInstance-find-fail",
						"Failed to find SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"systemInstance-getAll-fail",
						"Failed to get all SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"systemInstance-save-fail",
						"Failed to save SystemInstance"));
				em.persist(new GHAMessage(LanguageEnum.EN,
						"systemInstance-update-fail",
						"Failed to update SystemInstance"));

				// message service messages
				em.persist(new GHAMessage(LanguageEnum.ES, "message-find-fail",
						"Búsqueda fallida de Message"));
				em.persist(new GHAMessage(LanguageEnum.EN, "message-find-fail",
						"Failed to find Message"));

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
				em.persist(new UiString(LanguageEnum.ES,
						"no-materials-to-show",
						"No hay materiales para mostrar"));
				em.persist(new UiString(LanguageEnum.ES, "save", "Guardar"));
				em.persist(new UiString(LanguageEnum.ES, "cancel", "Cancelar"));
				em.persist(new UiString(LanguageEnum.ES, "edit", "Editar"));
				em.persist(new UiString(LanguageEnum.ES, "materials-category",
						"Categorias de materiales"));
				em.persist(new UiString(LanguageEnum.ES, "new-material",
						"Nuevo material"));
				em.persist(new UiString(LanguageEnum.ES, "utility-services",
						"Servicios utilitarios"));
				em.persist(new UiString(LanguageEnum.ES, "external-code",
						"Codigo externo"));// TODO
											// acento
				em.persist(new UiString(LanguageEnum.ES, "materials",
						"Materiales"));
				em.persist(new UiString(LanguageEnum.ES, "name", "Nombre"));
				em.persist(new UiString(LanguageEnum.ES, "components",
						"Componentes"));
				em.persist(new UiString(LanguageEnum.ES, "information",
						"Información"));
				em.persist(new UiString(LanguageEnum.ES, "reports", "Reportes"));
				em.persist(new UiString(LanguageEnum.ES, "equipments",
						"Equipos"));
				em.persist(new UiString(LanguageEnum.ES, "password",
						"Clave de acceso"));
				em.persist(new UiString(LanguageEnum.ES, "close", "Cerrar"));
				em.persist(new UiString(LanguageEnum.ES, "add", "Agregar"));
				em.persist(new UiString(LanguageEnum.ES, "clean", "Limpiar"));
				em.persist(new UiString(LanguageEnum.ES, "search", "Buscar"));
				em.persist(new UiString(LanguageEnum.ES, "username-not-null",
						"Debe ingresar un usuario válido"));
				em.persist(new UiString(LanguageEnum.ES, "password-not-null",
						"Debe ingresar una contraseña válida"));
				em.persist(new UiString(LanguageEnum.ES, "code", "Código"));
				em.persist(new UiString(LanguageEnum.ES, "model", "Modelo"));
				em.persist(new UiString(LanguageEnum.ES, "email-invalid-field",
						"Debe ingresar un email válido"));
				em.persist(new UiString(LanguageEnum.ES, "none", "Modelo"));
				em.persist(new UiString(LanguageEnum.ES, "manufacturer",
						"Fabricante"));
				em.persist(new UiString(LanguageEnum.ES, "mobility",
						"Movilidad"));
				em.persist(new UiString(LanguageEnum.ES, "eiatype",
						"Tipo de equipo"));
				em.persist(new UiString(LanguageEnum.ES, "eiatypes",
						"Tipos de equipo"));
				em.persist(new UiString(LanguageEnum.ES, "subtype", "Sub tipo"));
				em.persist(new UiString(LanguageEnum.ES, "brand", "Marca"));
				em.persist(new UiString(LanguageEnum.ES, "new-eia",
						"Nuevo equipo"));
				em.persist(new UiString(LanguageEnum.ES, "new-eiatype",
						"Nuevo tipo de equipo"));
				em.persist(new UiString(LanguageEnum.ES, "search-results",
						"Resultados de la búsqueda"));
				em.persist(new UiString(LanguageEnum.ES, "description",
						"Descripción"));
				em.persist(new UiString(LanguageEnum.ES, "use", "Uso"));
				em.persist(new UiString(LanguageEnum.ES, "type", "Tipo"));
				em.persist(new UiString(LanguageEnum.ES, "users", "Usuarios"));
				em.persist(new UiString(LanguageEnum.ES, "user", "Usuario"));

				em.persist(new UiString(LanguageEnum.ES, "state", "Estado"));
				em.persist(new UiString(LanguageEnum.ES, "first-name",
						"Primer nombre"));
				em.persist(new UiString(LanguageEnum.ES, "last-name",
						"Apellido"));
				em.persist(new UiString(LanguageEnum.ES, "id-type",
						"Tipo de identificación"));// TODO
													// acento
				em.persist(new UiString(LanguageEnum.ES, "id-number",
						"Numero de identificación"));// TODO
														// acento
				em.persist(new UiString(LanguageEnum.ES, "eiatype-select-item",
						"Tipo de Equipo"));
				em.persist(new UiString(LanguageEnum.ES,
						"eiastate-select-item", "Estado"));
				em.persist(new UiString(LanguageEnum.ES, "serialNumber-item",
						"Serial"));
				em.persist(new UiString(LanguageEnum.ES,
						"fixedAssetIdentifier-item", "Id Activo Fijo"));
				em.persist(new UiString(LanguageEnum.ES,
						"workingarea-select-item", "Area de Trabajo"));
				em.persist(new UiString(LanguageEnum.ES,
						"facility-select-item", "Instalación"));
				em.persist(new UiString(LanguageEnum.ES, "obu-select-item",
						"Departamento Responsable"));
				em.persist(new UiString(LanguageEnum.ES, "role-select-item",
						"Rol Responsable"));
				em.persist(new UiString(LanguageEnum.ES, "form-errors",
						"Errores en el formulario, por favor complete los campos correctamente"));
				em.persist(new UiString(LanguageEnum.ES, "unsaved-changes",
						"¿Descartar los cambios?"));
				em.persist(new UiString(LanguageEnum.ES, "search-component",
						"Buscar un componente para un tipo de equipo"));
				em.persist(new UiString(LanguageEnum.ES, "search-material",
						"Buscar un material"));
				em.persist(new UiString(LanguageEnum.ES, "record-not-selected",
						"Debe seleccionar un registro"));
				em.persist(new UiString(LanguageEnum.ES,
						"search-utility-material",
						"Buscar un material utilitario"));
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
		// materialTestData();
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
				Bpu admin = em.find(Bpu.class, 1L);
				Bpu gha = em.find(Bpu.class, 3L);

				for (Function function : all) {
					em.persist(new BpuFunction(admin, function));
					if (function.getCode().matches(
							"^EIATYPE-ADM-(EQUI|COMP)-(VIEW|EDIT)$")) {
						// usuario base solo eiatype
						em.persist(new BpuFunction(gha, function));
					}
				}
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
		String query = "SELECT t from MaterialCategory t WHERE t.id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test data : material");
				for (int j = 0; j < 3; j++) {
					MaterialCategory next = new MaterialCategory("mat-00" + j,
							"material-00" + j, MaterialTypeEnum.values()[j % 3]);
					// next.setMaterialCategory(em.find(MaterialCategory.class,
					// (long) (j + 1)));
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
				em.persist(new SSOUser(em.find(Bpu.class, 3L), "gha", "gha",
						UserLogonStatusEnum.STAYIN));
				em.flush();
				logger.info("done creating test users");
			} catch (Exception e1) {
				logger.log(Level.INFO, "error test data ssouser");
			}
		}
	}

}