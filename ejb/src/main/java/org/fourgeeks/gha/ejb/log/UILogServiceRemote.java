package org.fourgeeks.gha.ejb.log;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.logs.UILog;

/**
 * @author alacret
 * 
 */
@Remote
public interface UILogServiceRemote {

	/**
	 * @param log
	 */
	public void log(UILog log);

	/**
	 * @return a list of all the logs
	 * @throws GHAEJBException
	 */
	public List<UILog> getAll() throws GHAEJBException;

}