package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.enu.EiaReportOrderByEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

@Remote
public interface EiaReportsServiceRemote {
	/**
	 * Devuelve todos los Eia que cumplan las condiciones dadas por los
	 * parametros
	 * 
	 * @param facilityIds
	 *            Lista de IDs de facilidades
	 * @param workAreaIds
	 *            Lista de IDs de areas de trabajo
	 * @param eiaState
	 *            Uno o ningun estado de equipo
	 * @param orderBy
	 *            Como se van a ordenar los equipos
	 * 
	 * @return Lista de equipos que cumplan con la condicion. Puede devolver una
	 *         lista vacia
	 * @throws GHAEJBException
	 */
	public List<Eia> findAllEias(List<Long> facilityIds, List<Long> workAreaIds,
			EiaStateEnum eiaState, EiaReportOrderByEnum orderBy) throws GHAEJBException;

	/**
	 * Devuelve los Eia cuyo ID sea alguno de los ID pasados por parametro
	 * 
	 * @param eiaIds
	 *            lista con los ID de los equipos que se desea buscar
	 * @param orderBy
	 *            Como se van a ordenar los equipos
	 * 
	 * @return Lista de equipos que cumplan con la condicion. Puede devolver una
	 *         lista vacia
	 * @throws GHAEJBException
	 */
	public List<Eia> findEias(List<Long> eiaIds, EiaReportOrderByEnum orderBy)
			throws GHAEJBException;

	/**
	 * Devuelve los Eia que pertenescan a un EiaType y cumplan las condiciones
	 * dadas por los parametros
	 * 
	 * @param eiaTypeCode
	 *            ID (codigos del tipo de equipo
	 * @param facilityIds
	 *            Lista de IDs de facilidades
	 * @param workAreaIds
	 *            Lista de IDs de areas de trabajo
	 * @param eiaState
	 *            Uno o ningun estado de equipo
	 * @param orderBy
	 *            Como se van a ordenar los equipos
	 * 
	 * @return Lista de equipos que pertenescan el eiaType y cumplan las
	 *         condiciones
	 * @throws GHAEJBException
	 */
	public List<Eia> findEiasByEiaType(String eiaTypeCode, List<Long> facilityIds,
			List<Long> workAreaIds, EiaStateEnum eiaState, EiaReportOrderByEnum orderBy)
			throws GHAEJBException;

	/**
	 * Devuelve todos los Eia que cumplan las condiciones dadas por los
	 * parametros
	 * 
	 * @param eiaTypeCodes
	 *            Lista de IDs (codigos) de los tipos de equipos
	 * @param facilityIds
	 *            Lista de IDs de facilidades
	 * @param workAreaIds
	 *            Lista de IDs de areas de trabajo
	 * @param eiaState
	 *            Uno o ningun estado de equipo
	 * 
	 * @return Lista de equipos que cumplan con la condicion. Puede devolver una
	 *         lista vacia
	 * @throws GHAEJBException
	 */
	public List<Eia> findEiasByEiaTypes(List<String> eiaTypeIds, List<Long> facilityIds,
			List<Long> workAreaIds, EiaStateEnum eiaState) throws GHAEJBException;

	/**
	 * Devuelve los EiaType cuyos IDs (codigos) sean los pasados por parametro
	 * 
	 * @param eiaTypeIds
	 *            Lista con los IDs (codigos) de los EiaType. Puede ser nulo
	 * 
	 * @return Lista de EiaType. Devuelve todos los EiaType si eiaTypeIds es
	 *         nulo
	 * @throws GHAEJBException
	 */
	public List<EiaType> findEiaTypes(List<String> eiaTypeIds) throws GHAEJBException;
}
