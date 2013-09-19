/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;

/**
 * @author emiliot
 *
 */
@Remote
public interface EiaComponentServiceRemote {
	public void delete(long Id) throws GHAEJBException;
	public EiaComponent find(long Id) throws GHAEJBException;
	public List<EiaComponent> findByParentEia(Eia eia) throws GHAEJBException;
	public List<EiaComponent> getAll() throws GHAEJBException;
	public List<EiaComponent> getAll(int offset, int size) throws GHAEJBException;
	public EiaComponent save(EiaComponent eiaComponent) throws GHAEJBException;
	public EiaComponent update(EiaComponent eiaComponent) throws GHAEJBException;
}
