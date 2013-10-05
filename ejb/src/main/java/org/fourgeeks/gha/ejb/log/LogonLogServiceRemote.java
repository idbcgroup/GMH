package org.fourgeeks.gha.ejb.log;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.logs.LogonLog;

/**
 * @author alacret
 * 
 */
@Remote
public interface LogonLogServiceRemote {

	/**
	 * @param log
	 */
	public void log(LogonLog log);

	/**
	 * 
	 * @param bpu
	 * @return a list of LogOnLog by Bpu
	 */
	public List<LogonLog> getLogsByBpu(Bpu bpu);
}