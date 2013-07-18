/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;

/**
 * @author alacret
 * 
 */

@Remote
public interface BuildingLocationServiceRemote {
	/**
	 * 
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws EJBException
	 */
	public List<BuildingLocation> find(BuildingLocation brand)
			throws EJBException;

	/**
	 * 
	 * @param Id
	 * @return
	 * @throws EJBException
	 */
	public BuildingLocation find(long Id) throws EJBException;

	/**
	 * 
	 * @return
	 * @throws EJBException
	 */
	public List<BuildingLocation> getAll() throws EJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws EJBException
	 */
	public BuildingLocation save(BuildingLocation brand) throws EJBException;

	/**
	 * 
	 * @param brand
	 * @return
	 * @throws EJBException
	 */
	public BuildingLocation update(BuildingLocation brand) throws EJBException;
}
