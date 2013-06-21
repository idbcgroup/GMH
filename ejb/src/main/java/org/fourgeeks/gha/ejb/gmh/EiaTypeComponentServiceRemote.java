/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

/**
 * @author emiliot
 *
 */

@Remote
public interface EiaTypeComponentServiceRemote {
	public void save(EiaTypeComponent eiaTypeComponent);
	public EiaTypeComponent find(long Id);
	public List<EiaTypeComponent> find(EiaType eiaType);
	public List<EiaTypeComponent> find(EiaType eiaType, int offset, int size);
	public void delete(long Id);
	public void update(EiaTypeComponent eiaTypeComponent);
	public List<EiaTypeComponent> getAll();
	public List<EiaTypeComponent> getAll(int offset, int size);
	public List<EiaTypeComponent> findByEiaTypeId(long Id);
	public String buildFilters(EiaType eiaType);
}
