package org.fourgeeks.gha.ejb.log;

import javax.ejb.Local;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.UILog;

/**
 * @author alacret
 * 
 */
@Local
public interface UILogServiceLocal {
	/**
	 * @param log
	 */
	public void log(UILog log) throws GHAEJBException;

	/**
	 * @param log
	 *            the log to be erased
	 * @throws GHAEJBException
	 */
	public void delete(UILog log) throws GHAEJBException;

}