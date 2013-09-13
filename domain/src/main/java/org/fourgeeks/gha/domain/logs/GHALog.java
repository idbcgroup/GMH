/**
 * 
 */
package org.fourgeeks.gha.domain.logs;

import java.sql.Timestamp;

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
	
	protected Timestamp timestamp;
	protected String message;
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
