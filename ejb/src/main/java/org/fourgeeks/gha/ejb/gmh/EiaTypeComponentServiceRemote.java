/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

/**
 * @author emiliot
 *
 */

@Remote
public interface EiaTypeComponentServiceRemote {
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent) throws EJBException;
	public EiaTypeComponent find(long Id) throws EJBException;
	public List<EiaTypeComponent> find(EiaType eiaType);
	public List<EiaTypeComponent> find(EiaType eiaType, int offset, int size);
	public void delete(long Id) throws EJBException;
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent) throws EJBException;
	public List<EiaTypeComponent> getAll() throws EJBException;
	public List<EiaTypeComponent> getAll(int offset, int size) throws EJBException;
	public List<EiaTypeComponent> findByEiaTypeId(long Id) throws EJBException;
	public String buildFilters(EiaType eiaType);
}
