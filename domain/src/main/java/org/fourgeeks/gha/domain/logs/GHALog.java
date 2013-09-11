/**
 * 
 */
package org.fourgeeks.gha.domain.logs;

import java.sql.Date;

import javax.persistence.MappedSuperclass;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@MappedSuperclass
public abstract class GHALog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Date timeStamp;
	protected String message;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
