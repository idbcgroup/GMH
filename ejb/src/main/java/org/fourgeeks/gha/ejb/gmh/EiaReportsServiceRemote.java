package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;

@Remote
public interface EiaReportsServiceRemote {
	/**
	 * Devuelve los equipos cuyo ID sea alguno de los ID pasados por parametros
	 * 
	 * @param eiaIds
	 *            lista con los ID de los equipos que se desea buscar
	 * @return lista de equipos Eia
	 */
	public List<Eia> findByEiaIds(List<Long> eiaIds) throws GHAEJBException;;
}
