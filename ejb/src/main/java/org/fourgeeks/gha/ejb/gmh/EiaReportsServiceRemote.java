package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

@Remote
public interface EiaReportsServiceRemote {
	/**
	 * Devuelve todos los EIA que cumplan las condiciones dadas por los
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
	 * @return Lista de equipos que cumplan con la condicion. Puede devolver una
	 *         lista vacia
	 * @throws GHAEJBException
	 */
	public List<Eia> findEiasByEiaTypes(List<String> eiaTypeIds, List<Long> facilityIds,
			List<Long> workAreaIds, EiaStateEnum eiaState) throws GHAEJBException;

	/**
	 * Devuelve todos los EIA que cumplan las condiciones dadas por los
	 * parametros
	 * 
	 * @param facilityIds
	 *            Lista de IDs de facilidades
	 * @param workAreaIds
	 *            Lista de IDs de areas de trabajo
	 * @param eiaState
	 *            Uno o ningun estado de equipo
	 * @param orderByUbicEiaType
	 *            ¿Ordenar por Ubicacion y eiaType (<code>true</code>) o por
	 *            eiaType y Ubicacion ( <code>false</code>)?
	 * @return Lista de equipos que cumplan con la condicion. Puede devolver una
	 *         lista vacia
	 * @throws GHAEJBException
	 */
	public List<Eia> findAllEias(List<Long> facilityIds, List<Long> workAreaIds,
			EiaStateEnum eiaState, boolean orderByUbicEiaType) throws GHAEJBException;

	/**
	 * Devuelve los EIA cuyo ID sea alguno de los ID pasados por parametro
	 * 
	 * @param eiaIds
	 *            lista con los ID de los equipos que se desea buscar
	 * @param orderByUbicEiaType
	 *            ¿Ordenar por Ubicacion y eiaType (<code>true</code>) o por
	 *            eiaType y Ubicacion ( <code>false</code>)?
	 * @return Lista de equipos que cumplan con la condicion. Puede devolver una
	 *         lista vacia
	 * @throws GHAEJBException
	 */
	public List<Eia> findEias(List<Long> eiaIds, boolean orderByUbicEiaType) throws GHAEJBException;

	/**
	 * 
	 * @param eiaTypeIds
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaType> findEiaTypes(List<String> eiaTypeIds) throws GHAEJBException;
}
