/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import java.sql.Date;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class UILog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date timeStamp;
	private String message;
	/**
	 * 
	 */
	public UILog() {
		// TODO Auto-generated constructor stub
	}
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
