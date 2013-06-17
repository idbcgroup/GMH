/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.gmh.Brand;

/**
 * @author emiliot
 *
 */

@Remote
public interface BrandServiceRemote {
	public void save(Brand brand);
	public Brand find(long Id);
	public void delete(long Id);
	public List<Brand> getAll();
	public List<Brand>	find(Brand brand);
}
