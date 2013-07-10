/**
 * 
 */
package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.List;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 *
 */

@RemoteServiceRelativePath("eiaComponent")
public interface GWTEiaComponentService extends RemoteService{
	public void delete(long Id) throws EJBException;
	public List<EiaComponent> find(Eia eia) throws EJBException;
	public List<EiaComponent> find(Eia eia, int offset, int size)throws EJBException;
	public EiaComponent find(long Id) throws EJBException;
	public List<EiaComponent> findByEiaId(long Id) throws EJBException;
	public List<EiaComponent> getAll() throws EJBException;
	public List<EiaComponent> getAll(int offset, int size) throws EJBException;
	public EiaComponent save(EiaComponent eiaComponent) throws EJBException;
	public EiaComponent update(EiaComponent eiaComponent) throws EJBException;
}
