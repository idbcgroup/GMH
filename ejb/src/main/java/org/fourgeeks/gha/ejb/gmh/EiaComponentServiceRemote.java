/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;

/**
 * @author emiliot
 *
 */
public interface EiaComponentServiceRemote {
	public void delete(long Id) throws EJBException;
	public List<EiaComponent> find(Eia eia);
	public List<EiaComponent> find(Eia eia, int offset, int size);
	public EiaComponent find(long Id) throws EJBException;
	public List<EiaComponent> findByEiaId(long Id) throws EJBException;
	public List<EiaComponent> getAll() throws EJBException;
	public List<EiaComponent> getAll(int offset, int size) throws EJBException;
	public EiaComponent save(EiaComponent eiaComponent) throws EJBException;
	public EiaComponent update(EiaComponent eiaComponent) throws EJBException;
}
