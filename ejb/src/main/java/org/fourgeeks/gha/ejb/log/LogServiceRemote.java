package org.fourgeeks.gha.ejb.log;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.logs.LogonLog;

/**
 * @author alacret
 * 
 */
@Remote
public interface LogServiceRemote {

	/**
	 * @param log
	 */
	public void log(LogonLog log);
}