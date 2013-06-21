/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;

/**
 * @author emiliot
 *
 */
public interface EiaComponentServiceRemote {
	public void save(EiaComponent eiaComponent);
	public EiaComponent find(long Id);
	public List<EiaComponent> find(Eia eia);
	public List<EiaComponent> find(Eia eia, int offset, int size);
	public void delete(long Id);
	public void update(EiaComponent eiaComponent);
	public List<EiaComponent> getAll();
	public List<EiaComponent> getAll(int offset, int size);
	public List<EiaComponent> findByEiaId(long Id);
}
