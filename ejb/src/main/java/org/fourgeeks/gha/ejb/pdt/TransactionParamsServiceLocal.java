package org.fourgeeks.gha.ejb.pdt;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.TransactionParams;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author naramirez
 */
@Local
public interface TransactionParamsServiceLocal {

	/**
	 * @param code
	 * @return a HashMap with the PDTProcessor jndiName and other parameters
	 * @throws GHAEJBException
	 */
	public TransactionParams find(String code) throws GHAEJBException;
}
