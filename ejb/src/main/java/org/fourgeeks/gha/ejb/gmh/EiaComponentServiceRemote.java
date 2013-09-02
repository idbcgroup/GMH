/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;

/**
 * @author emiliot
 *
 */
@Remote
public interface EiaComponentServiceRemote {
	public void delete(long Id) throws EJBException;
	public EiaComponent find(long Id) throws EJBException;
	public List<EiaComponent> findByParentEia(Eia eia) throws EJBException;
	public List<EiaComponent> getAll() throws EJBException;
	public List<EiaComponent> getAll(int offset, int size) throws EJBException;
	public EiaComponent save(EiaComponent eiaComponent) throws EJBException;
	public EiaComponent update(EiaComponent eiaComponent) throws EJBException;
}
