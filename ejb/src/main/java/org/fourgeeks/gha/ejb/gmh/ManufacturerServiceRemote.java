/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 *
 */

@Remote
public interface ManufacturerServiceRemote {
	public void delete(long Id) throws GHAEJBException;
	public Manufacturer find(long Id) throws GHAEJBException;
	public List<Manufacturer> find(Manufacturer manufacturer) throws GHAEJBException;
	
	public List<Manufacturer> getAll() throws GHAEJBException;
	public Manufacturer save(Manufacturer manufacturer) throws GHAEJBException;
	public Manufacturer update(Manufacturer manufacturer) throws GHAEJBException;
}
