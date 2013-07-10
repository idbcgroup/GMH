/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 *
 */

@Remote
public interface ManufacturerServiceRemote {
	public void delete(long Id) throws EJBException;
	public Manufacturer find(long Id) throws EJBException;
	public List<Manufacturer> find(Manufacturer manufacturer) throws EJBException;
	
	public List<Manufacturer> getAll() throws EJBException;
	public Manufacturer save(Manufacturer manufacturer) throws EJBException;
	public Manufacturer update(Manufacturer manufacturer) throws EJBException;
}
