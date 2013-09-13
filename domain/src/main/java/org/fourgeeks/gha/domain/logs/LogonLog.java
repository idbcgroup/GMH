/**
 * 
 */
package org.fourgeeks.gha.domain.logs;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author emiliot
 *
 */

@Entity
@Table(name = "LogonLog", schema = "GHALog")
public class LogonLog extends GHALog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	/**
	 * 
	 */
	public LogonLog() {
		// TODO Auto-generated constructor stub
	}
	
}
