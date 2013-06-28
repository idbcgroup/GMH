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
	/**
	 * @param eiaTypeComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent) throws EJBException;
	/**
	 * @param Id
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent find(long Id) throws EJBException;
	/**
	 * @param eiaType
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> find(EiaType eiaType) throws EJBException;
	/**
	 * @param eiaType
	 * @param offset
	 * @param size
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> find(EiaType eiaType, int offset, int size) throws EJBException;
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;
	/**
	 * @param eiaTypeComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent) throws EJBException;
	/**
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> getAll() throws EJBException;
	/**
	 * @param offset
	 * @param size
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> getAll(int offset, int size) throws EJBException;
	/**
	 * @param Id
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> findByEiaTypeId(long Id) throws EJBException;
	/**
	 * @param eiaType
	 * @return
	 */
	public String buildFilters(EiaType eiaType);
}
