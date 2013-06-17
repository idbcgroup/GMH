/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 *
 */

@Remote
public interface ManufacturerServiceRemote {
	public void save(Manufacturer manufacturer);
	public Manufacturer find(long Id);
	public void delete(long Id);
	
	public List<Manufacturer> getAll();
	public List<Manufacturer> find(Manufacturer manufacturer);
}
