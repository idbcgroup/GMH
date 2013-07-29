/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

/**
 * @author emiliot
 *
 */

@Remote
public interface EiaTypeComponentServiceRemote {
	/**
	 * @param Id
	 * @throws EJBException
	 */
	public void delete(long Id) throws EJBException;
	/**
	 * @param Id
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent find(long Id) throws EJBException;
	/**
	 * @param Id
	 * @return
	 * @throws EJBException
	 */
	public List<EiaTypeComponent> findByParentEiaTypeId(long Id) throws EJBException;
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
	 * @param eiaTypeComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent) throws EJBException;
	/**
	 * @param eiaTypeComponent
	 * @return
	 * @throws EJBException
	 */
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent) throws EJBException;
}
