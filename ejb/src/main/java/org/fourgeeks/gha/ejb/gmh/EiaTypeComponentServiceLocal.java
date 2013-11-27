/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

/**
 * @author emiliot
 * 
 */

@Local
public interface EiaTypeComponentServiceLocal {

	/**
	 * @param eiaType
	 * @return
	 * @throws GHAEJBException
	 */
	public List<EiaTypeComponent> findByParentEiaType(EiaType eiaType)
			throws GHAEJBException;
}